/**
 * 
 */
package org.iplantc.de.publish.discovery;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.utils.LocalFileUtils;
import org.irods.jargon.testutils.TestingUtilsException;

/**
 * Test properties and utils
 * 
 * @author Mike Conway - DICE
 *
 */
public class PublisherTestingProperties {

	public static final String TEST_PUBLISHER_DIR_PROPERTY = "driver.jar.dir";

	/**
	 * 
	 */
	public PublisherTestingProperties() {
	}

	/**
	 * Load the properties that control various tests from the
	 * testing.properties file on the code path
	 *
	 * @return <code>Properties</code> class with the test values
	 * @throws TestingUtilsException
	 */
	public Properties getTestProperties() throws TestingUtilsException {
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream in = loader.getResourceAsStream("testing.properties");
		Properties properties = new Properties();

		try {
			properties.load(in);
		} catch (IOException ioe) {
			throw new TestingUtilsException("error loading test properties",
					ioe);
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				// ignore
			}
		}
		return properties;
	}

	public static File getClasspathResourceAsFile(final String resourcePath)
			throws JargonException {

		if (resourcePath == null || resourcePath.isEmpty()) {
			throw new IllegalArgumentException("null or empty resourcePath");
		}
		// Load the directory as a resource
		URL resourceUrl = LocalFileUtils.class.getResource(resourcePath);

		if (resourceUrl == null) {
			throw new JargonException("null resource, cannot find file");
		}

		// Turn the resource into a File object
		try {
			File resourceFile = new File(resourceUrl.toURI());
			if (!resourceFile.exists()) {
				throw new JargonException("resource file does not exist");
			}
			return resourceFile;
		} catch (URISyntaxException e) {
			throw new JargonException("unable to create uri from file path");
		}
	}

}
