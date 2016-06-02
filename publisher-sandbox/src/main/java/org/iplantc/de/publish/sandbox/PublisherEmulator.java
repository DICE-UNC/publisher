/**
 * 
 */
package org.iplantc.de.publish.sandbox;

import java.util.List;

import org.iplantc.de.publish.discovery.PublisherDiscoveryConfiguration;
import org.iplantc.de.publish.discovery.PublisherDiscoveryService;
import org.iplantc.de.publish.mechanism.api.PublisherPluginDescription;
import org.iplantc.de.publish.mechanism.api.exception.PublicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Black box host of publishing mechanisms for testing and validation purposes
 * 
 * @author Mike Conway - DICE
 *
 */
public class PublisherEmulator {

	private SandboxConfiguration sandboxConfiguration;
	private PublisherDiscoveryService publisherDiscoveryService;
	private List<PublisherPluginDescription> publisherPluginDescriptions;

	public static final Logger log = LoggerFactory
			.getLogger(PublisherEmulator.class);

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
			SandboxConfiguration sandboxConfiguration) {
		this.sandboxConfiguration = sandboxConfiguration;
	}

	/**
	 * Initialize publisher based on (required) sandbox configuration
	 * 
	 * @throws PublicationException
	 */
	public void init() throws PublicationException {
		log.info("init()");
		if (sandboxConfiguration == null) {
			throw new PublicationException(
					"init() cannot be called, no provided sandboxConfiguration");
		}

		if (sandboxConfiguration.getJarFilePluginDir() == null
				|| sandboxConfiguration.getJarFilePluginDir().isEmpty()) {
			throw new PublicationException(
					"no jar file plugin directory specified");
		}

		PublisherDiscoveryConfiguration config = new PublisherDiscoveryConfiguration();
		config.setJarFilePluginDir(sandboxConfiguration.getJarFilePluginDir());
		publisherDiscoveryService = new PublisherDiscoveryService();
		publisherDiscoveryService.setPublisherDiscoveryConfiguration(config);
		log.info("initializing plugin discovery service to load plugins");
		publisherDiscoveryService.init();
		this.publisherPluginDescriptions = publisherDiscoveryService
				.listPublisherDescriptions();
		log.info("found plugins:{}", publisherPluginDescriptions);

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
