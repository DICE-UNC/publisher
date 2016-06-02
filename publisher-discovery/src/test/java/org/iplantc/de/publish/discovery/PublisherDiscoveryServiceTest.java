package org.iplantc.de.publish.discovery;

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
	public void testListPublicationDescriptions() throws PublicationException {
		PublisherDiscoveryConfiguration publisherDiscoveryConfiguration = new PublisherDiscoveryConfiguration();
		publisherDiscoveryConfiguration
				.setJarFilePluginDir(testingProperties
						.getProperty(PublisherTestingProperties.TEST_PUBLISHER_DIR_PROPERTY));
		PublisherDiscoveryService publisherDiscoveryService = new PublisherDiscoveryService();
		publisherDiscoveryService
				.setPublisherDiscoveryConfiguration(publisherDiscoveryConfiguration);
		publisherDiscoveryService.init();
		List<PublisherPluginDescription> descriptions = publisherDiscoveryService
				.listPublisherDescriptions();
		Assert.assertNotNull("null descriptions found:{}", descriptions);

	}

}
