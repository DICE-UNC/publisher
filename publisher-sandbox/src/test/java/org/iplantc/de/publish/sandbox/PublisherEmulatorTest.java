/**
 * 
 */
package org.iplantc.de.publish.sandbox;

import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.iplantc.de.publish.mechanism.api.PublisherPluginDescription;
import org.iplantc.de.publish.mechanism.api.exception.PublicationException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Mike Conway - DICE
 *
 */
public class PublisherEmulatorTest {

	private static Properties testingProperties;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PublisherTestingProperties publisherTestingProperties = new PublisherTestingProperties();
		testingProperties = publisherTestingProperties.getTestProperties();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testInitWithConfig() throws PublicationException {
		SandboxConfiguration sandboxConfiguration = new SandboxConfiguration();
		sandboxConfiguration
				.setJarFilePluginDir(testingProperties
						.getProperty(PublisherTestingProperties.TEST_PUBLISHER_DIR_PROPERTY));
		PublisherEmulator emulator = new PublisherEmulator();
		emulator.setSandboxConfiguration(sandboxConfiguration);
		emulator.init();

	}

	@Test
	public void testListPublicationDescriptions() throws PublicationException {
		SandboxConfiguration sandboxConfiguration = new SandboxConfiguration();
		sandboxConfiguration
				.setJarFilePluginDir(testingProperties
						.getProperty(PublisherTestingProperties.TEST_PUBLISHER_DIR_PROPERTY));
		PublisherEmulator emulator = new PublisherEmulator();
		emulator.setSandboxConfiguration(sandboxConfiguration);
		emulator.init();
		List<PublisherPluginDescription> descriptions = emulator
				.listLoadedPlugins();
		Assert.assertNotNull("null descriptions found:{}", descriptions);

	}

}
