/**
 * 
 */
package org.iplantc.de.publish.sandbox;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

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

		log.info("scanning for plugin jars at:{}",
				sandboxConfiguration.getJarFilePluginDir());

	}

	public void initialize(String libDir) throws Exception {
		File dependencyDirectory = new File(libDir);
		File[] files = dependencyDirectory.listFiles();
		ArrayList<URL> urls = new ArrayList<URL>();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().endsWith(".jar")) {
				urls.add(files[i].toURL());
				// urls.add(files[i].toURI().toURL());
			}
		} /*
		 * classLoader = new JarFileClassLoader("Scheduler CL" +
		 * System.currentTimeMillis(), urls.toArray(new URL[urls.size()]),
		 * GFClassLoader.class.getClassLoader());
		 */
	}

}
