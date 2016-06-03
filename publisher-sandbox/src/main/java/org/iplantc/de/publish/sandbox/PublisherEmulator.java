/**
 *
 */
package org.iplantc.de.publish.sandbox;

import java.util.List;

import org.iplantc.de.publish.discovery.PublisherDiscoveryConfiguration;
import org.iplantc.de.publish.discovery.PublisherDiscoveryService;
import org.iplantc.de.publish.mechanism.api.PublishActionDescriptor;
import org.iplantc.de.publish.mechanism.api.PublishContext;
import org.iplantc.de.publish.mechanism.api.PublishMechanism;
import org.iplantc.de.publish.mechanism.api.PublishResult;
import org.iplantc.de.publish.mechanism.api.PublisherPluginDescription;
import org.iplantc.de.publish.mechanism.api.exception.PublicationException;
import org.iplantc.de.publish.mechanism.api.exception.PublicationRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Black box host of publishing mechanisms for testing and validation purposes
 *
 * @author Mike Conway - DICE
 *
 */
public class PublisherEmulator {

	/**
	 * provided configuration to tune emulator
	 */
	private SandboxConfiguration sandboxConfiguration;
	/**
	 * service that will load plugin jars for publishers
	 */
	private PublisherDiscoveryService publisherDiscoveryService;
	private List<PublisherPluginDescription> publisherPluginDescriptions;
	private PublishContext publishContext;

	public static final Logger log = LoggerFactory
			.getLogger(PublisherEmulator.class);

	/**
	 * @return the publishContext
	 */
	public PublishContext getPublishContext() {
		return publishContext;
	}

	/**
	 * @param publishContext
	 *            the publishContext to set
	 */
	public void setPublishContext(final PublishContext publishContext) {
		this.publishContext = publishContext;
	}

	/**
	 * Default (no values) constructor, note that sandboxConfiguration must be
	 * provided, and init() must be called
	 */
	public PublisherEmulator() {

	}

	/**
	 * @return the sandboxConfiguration
	 */
	public SandboxConfiguration getSandboxConfiguration() {
		return sandboxConfiguration;
	}

	/**
	 * @param sandboxConfiguration
	 *            the sandboxConfiguration to set
	 */
	public void setSandboxConfiguration(
			final SandboxConfiguration sandboxConfiguration) {
		this.sandboxConfiguration = sandboxConfiguration;
	}

	public PublishResult triggerPublicationLifecycle(String mechanismName,
			PublishActionDescriptor publishActionDescriptor)
			throws PublicationException {

		log.info("triggerPublicationLifecycle()");
		if (publishActionDescriptor == null) {
			throw new IllegalArgumentException("null publishActionDescriptor");
		}
		if (mechanismName == null || mechanismName.isEmpty()) {
			throw new IllegalArgumentException("null mechanismName");
		}
		validateInit();

		log.info("looking for mechanism:{}", mechanismName);

		PublisherPluginDescription requestedPlugin = null;

		for (PublisherPluginDescription description : publisherPluginDescriptions) {
			if (description.getPublisherName().equals(mechanismName)) {
				requestedPlugin = description;
				break;
			}
		}

		if (requestedPlugin == null) {
			log.error("unable to find plugin with name:{}", mechanismName);
			throw new PublicationException("unknown plugin requested");
		}

		try {
			PublishMechanism publishMechanism = (PublishMechanism) requestedPlugin
					.getPublisherClass().newInstance();

			PublishResult result = publishMechanism.preValidate(
					publishActionDescriptor, publishContext);
			if (result.getResponseCode() != PublishResult.PUBLISH_RESULT_NORMAL) {
				log.warn("invalid response:{}", result);
				log.warn("for publication request:{}", publishActionDescriptor);
				return result;

			}

			log.info("passed pre-validate...now do actual publish...");

			// TODO: clarify this switching of publish 'target' if prevalidate
			// creates an intermediary

			if (!result.getIntermediateDipAbsolutePath().isEmpty()) {
				log.info("redirecting to intermediate DIP:{}",
						result.getIntermediateDipAbsolutePath());
				publishActionDescriptor
						.setPublishIntermediaryAbsolutePath(result
								.getIntermediateDipAbsolutePath());
			}

			result = publishMechanism.publish(publishActionDescriptor,
					publishContext);
			log.info("result of publication:{}", result);
			return result;

		} catch (InstantiationException | IllegalAccessException e) {
			log.error("cannot create plugin instance from class:{}",
					requestedPlugin);
			throw new PublicationRuntimeException(
					"cannot create plugin instance", e);
		}

	}

	/**
	 * Initialize publisher based on (required) sandbox configuration
	 *
	 * @throws PublicationException
	 */
	public void init() throws PublicationException {
		log.info("init()");
		validateInit();

		PublisherDiscoveryConfiguration config = new PublisherDiscoveryConfiguration();
		config.setJarFilePluginDir(sandboxConfiguration.getJarFilePluginDir());
		publisherDiscoveryService = new PublisherDiscoveryService();
		publisherDiscoveryService.setPublisherDiscoveryConfiguration(config);
		log.info("initializing plugin discovery service to load plugins");
		publisherDiscoveryService.init();
		publisherPluginDescriptions = publisherDiscoveryService
				.listPublisherDescriptions();
		log.info("found plugins:{}", publisherPluginDescriptions);

	}

	private void validateInit() throws PublicationException {
		if (sandboxConfiguration == null) {
			throw new PublicationException(
					"init() cannot be called, no provided sandboxConfiguration");
		}

		if (sandboxConfiguration.getJarFilePluginDir() == null
				|| sandboxConfiguration.getJarFilePluginDir().isEmpty()) {
			throw new PublicationException(
					"no jar file plugin directory specified");
		}

		if (publishContext == null) {
			log.error("cannot init, no publish context set");
			throw new PublicationException("cannot init, no publish context");
		}
	}

	public List<PublisherPluginDescription> listLoadedPlugins()
			throws PublicationException {
		log.info("listLoadedPlugins()");
		if (publisherPluginDescriptions == null) {
			log.error("init() has not been called, cannot list plugins");
			throw new PublicationException("init() has not been called");
		}

		return publisherDiscoveryService.listPublisherDescriptions();

	}

}
