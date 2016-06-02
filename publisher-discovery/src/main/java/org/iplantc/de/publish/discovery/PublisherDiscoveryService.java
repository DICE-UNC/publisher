/**
 * 
 */
package org.iplantc.de.publish.discovery;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.iplantc.de.publish.mechanism.api.PublisherPluginDescription;
import org.iplantc.de.publish.mechanism.api.annotations.PublicationDriver;
import org.iplantc.de.publish.mechanism.api.exception.PublicationException;
import org.iplantc.de.publish.mechanism.api.exception.PublicationRuntimeException;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.context.DefaultContextLoader;
import org.xeustechnologies.jcl.context.JclContext;

/**
 * Service to locate and surface provided publisher plug-ins. Handles all the
 * classloader and reflection tasks.
 * 
 * @author Mike Conway - DICE
 *
 */
public class PublisherDiscoveryService {

	private PublisherDiscoveryConfiguration publisherDiscoveryConfiguration;
	private JarClassLoader jcl;
	private List<URL> urls = new ArrayList<URL>();

	public static final Logger log = LoggerFactory
			.getLogger(PublisherDiscoveryService.class);

	public PublisherDiscoveryService() {
	}

	/**
	 * @return the publisherDiscoveryConfiguration
	 */
	public PublisherDiscoveryConfiguration getPublisherDiscoveryConfiguration() {
		return publisherDiscoveryConfiguration;
	}

	/**
	 * @param publisherDiscoveryConfiguration
	 *            the publisherDiscoveryConfiguration to set
	 */
	public void setPublisherDiscoveryConfiguration(
			PublisherDiscoveryConfiguration publisherDiscoveryConfiguration) {
		this.publisherDiscoveryConfiguration = publisherDiscoveryConfiguration;
	}

	public List<PublisherPluginDescription> listPublisherDescriptions() {
		log.info("listPublisherDescriptions()");
		Set<Class<?>> classes = listPublisherClasses();

		for (Class<?> clazz : classes) {
			log.info("processing class:{}", clazz);
			for (Annotation annotation : clazz.getDeclaredAnnotations()) {
				log.info("...annotation:{}", annotation.toString());
			}
		}

		return null;

	}

	/**
	 * Method returns the classes for all registered publishers. Good for
	 * debugging and testing purposes
	 * 
	 * @return <code>Set</code> of all of the annotated publisher classes
	 */
	private Set<Class<?>> listPublisherClasses() {

		/*
		 * see: https://github.com/ronmamo/reflections
		 */

		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder
				.setUrls(urls)
				.addClassLoader(JclContext.get())
				.addScanners(new SubTypesScanner(),
						new TypeAnnotationsScanner());
		Reflections reflections = new Reflections(configurationBuilder);

		Set<Class<?>> mechanisms = reflections.getTypesAnnotatedWith(
				PublicationDriver.class, true);
		log.info("mechanisms:{}", mechanisms);
		return mechanisms;
	}

	/**
	 * Initialize publisher based on (required) configuration
	 * 
	 * @throws PublicationException
	 */
	public void init() throws PublicationException {
		log.info("init()");
		if (publisherDiscoveryConfiguration == null) {
			throw new PublicationException(
					"init() cannot be called, no provided publisherDiscoveryConfiguration");
		}

		if (publisherDiscoveryConfiguration.getJarFilePluginDir() == null
				|| publisherDiscoveryConfiguration.getJarFilePluginDir()
						.isEmpty()) {
			throw new PublicationException(
					"no jar file plugin directory specified");
		}

		log.info("scanning for plugin jars at:{}",
				publisherDiscoveryConfiguration.getJarFilePluginDir());
		loadPublisherClasses(publisherDiscoveryConfiguration
				.getJarFilePluginDir());

	}

	private void loadPublisherClasses(String libDir) {

		/*
		 * See https://github.com/kamranzafar/JCL for usage of JCL
		 */

		File dependencyDirectory = new File(libDir);
		File[] files = dependencyDirectory.listFiles();
		ArrayList<URI> uris = new ArrayList<URI>();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().endsWith(".jar")) {
				log.info("adding jar:{} to candidates", files[i]);
				uris.add(files[i].toURI());
			}
		}

		log.info("creating jar class loader...");
		jcl = new JarClassLoader();

		for (URI uri : uris) {
			log.info("adding uri for jar:{}", uri);
			try {
				jcl.add(uri.toURL());
				urls.add(uri.toURL()); // testing outside jcl FIXME: decide!
			} catch (MalformedURLException e) {
				log.error("malformed url for jar file:{}", uri, e);
				throw new PublicationRuntimeException("error loading jar file",
						e);
			}
		}

		DefaultContextLoader context = new DefaultContextLoader(jcl);
		context.loadContext();

	}

}
