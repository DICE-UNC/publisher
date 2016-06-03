/**
 * 
 */
package org.iplantc.de.publish.sandbox;

import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.iplantc.de.publish.mechanism.api.PublishActionDescriptor;
import org.iplantc.de.publish.mechanism.api.PublishContext;
import org.iplantc.de.publish.mechanism.api.PublishResult;
import org.iplantc.de.publish.mechanism.api.PublisherPluginDescription;
import org.iplantc.de.publish.mechanism.api.exception.PublicationException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

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
		PublishContext publishContext = Mockito.mock(PublishContext.class);

		emulator.setPublishContext(publishContext);
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
		PublishContext publishContext = Mockito.mock(PublishContext.class);

		emulator.setPublishContext(publishContext);
		emulator.setSandboxConfiguration(sandboxConfiguration);
		emulator.init();
		List<PublisherPluginDescription> descriptions = emulator
				.listLoadedPlugins();
		Assert.assertNotNull("null descriptions found:{}", descriptions);

	}

	@Test
	public void testPublish() throws PublicationException {
		SandboxConfiguration sandboxConfiguration = new SandboxConfiguration();
		sandboxConfiguration
				.setJarFilePluginDir(testingProperties
						.getProperty(PublisherTestingProperties.TEST_PUBLISHER_DIR_PROPERTY));
		PublisherEmulator emulator = new PublisherEmulator();
		PublishContext publishContext = Mockito.mock(PublishContext.class);

		emulator.setPublishContext(publishContext);
		emulator.setSandboxConfiguration(sandboxConfiguration);
		emulator.init();
		PublishActionDescriptor descriptor = new PublishActionDescriptor();
		descriptor.setPublishSourceAbsolutePath("/a/path");
		PublishResult result = emulator.triggerPublicationLifecycle(
				PublisherTestingProperties.DUMMY_PUBLISHER, descriptor);
		Assert.assertNotNull("null result", result);

	}

}
