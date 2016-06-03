package org.iplantc.de.publish.discovery;

import java.io.File;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.iplantc.de.publish.mechanism.api.PublisherPluginDescription;
import org.iplantc.de.publish.mechanism.api.exception.PublicationException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PublisherDiscoveryServiceTest {

	private static Properties testingProperties;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PublisherTestingProperties publisherTestingProperties = new PublisherTestingProperties();
		testingProperties = publisherTestingProperties.getTestProperties();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testInitWithConfig() throws PublicationException {
		PublisherDiscoveryConfiguration publisherDiscoveryConfiguration = new PublisherDiscoveryConfiguration();
		publisherDiscoveryConfiguration
				.setJarFilePluginDir(testingProperties
						.getProperty(PublisherTestingProperties.TEST_PUBLISHER_DIR_PROPERTY));
		PublisherDiscoveryService publisherDiscoveryService = new PublisherDiscoveryService();
		publisherDiscoveryService
				.setPublisherDiscoveryConfiguration(publisherDiscoveryConfiguration);
		publisherDiscoveryService.init();
		Assert.assertTrue(true);
		// no error on init means success
	}

	@Test
	public void testListPublicationDescriptions() throws Exception {
		File testDriver = PublisherTestingProperties
				.getClasspathResourceAsFile("/sample-jars/dummy-publish-1.0.0-SNAPSHOT.jar");
		PublisherDiscoveryConfiguration publisherDiscoveryConfiguration = new PublisherDiscoveryConfiguration();
		publisherDiscoveryConfiguration.setJarFilePluginDir(testDriver
				.getParent());
		// testingProperties
		// .getProperty(PublisherTestingProperties.TEST_PUBLISHER_DIR_PROPERTY));
		PublisherDiscoveryService publisherDiscoveryService = new PublisherDiscoveryService();
		publisherDiscoveryService
				.setPublisherDiscoveryConfiguration(publisherDiscoveryConfiguration);
		publisherDiscoveryService.init();
		List<PublisherPluginDescription> descriptions = publisherDiscoveryService
				.listPublisherDescriptions();
		Assert.assertNotNull("null descriptions found:{}", descriptions);
		String dummyName = "Dummy Logging";
		boolean found = false;

		for (PublisherPluginDescription description : descriptions) {
			if (!description.getPublisherName().equals(dummyName)) {
				continue;
			}

			Assert.assertFalse(description.getAuthor().isEmpty());
			Assert.assertFalse(description.getDescription().isEmpty());
			Assert.assertFalse(description.getPublisherName().isEmpty());
			Assert.assertFalse(description.getVersion().isEmpty());
			found = true;
		}

		Assert.assertTrue("didnt find test driver", found);

	}
}
