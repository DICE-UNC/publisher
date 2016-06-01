/**
 * 
 */
package org.iplantc.de.publish.sandbox;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Set;

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
 * Black box host of publishing mechanisms for testing and validation purposes
 * 
 * @author Mike Conway - DICE
 *
 */
public class PublisherEmulator {

	private SandboxConfiguration sandboxConfiguration;
	private JarClassLoader jcl;

	public static final Logger log = LoggerFactory
			.getLogger(PublisherEmulator.class);

	/**
	 * Default (no values) constructor, note that sandboxConfiguration must be
	 * provided, and init() must be called
	 */
	public PublisherEmulator() {

	}

	public Set<Class<?>> listPublishers() {

		/*
		 * see: https://github.com/ronmamo/reflections
		 */

		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.addClassLoader(JclContext.get()).addScanners(
				new SubTypesScanner(), new TypeAnnotationsScanner());
		Reflections reflections = new Reflections(configurationBuilder);

		Set<Class<?>> mechanisms = reflections
				.getTypesAnnotatedWith(PublicationDriver.class);
		log.info("mechanisms:{}", mechanisms);
		return mechanisms;
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

		log.info("scanning for plugin jars at:{}",
				sandboxConfiguration.getJarFilePluginDir());
		loadPublisherClasses(sandboxConfiguration.getJarFilePluginDir());

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
