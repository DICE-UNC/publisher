/**
 * 
 */
package org.iplantc.de.publish.sandbox;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

}
