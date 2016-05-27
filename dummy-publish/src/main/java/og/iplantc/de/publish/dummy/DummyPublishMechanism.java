/**
 * 
 */
package og.iplantc.de.publish.dummy;

import org.iplantc.de.publish.mechanism.api.PublishActionDescriptor;
import org.iplantc.de.publish.mechanism.api.PublishContext;
import org.iplantc.de.publish.mechanism.api.PublishMechanism;
import org.iplantc.de.publish.mechanism.api.PublishPhaseEnum;
import org.iplantc.de.publish.mechanism.api.PublishResult;
import org.iplantc.de.publish.mechanism.api.PublishStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple noop publish mechanism for testing and demonstration
 * 
 * @author Mike Conway - DICE
 *
 */
public class DummyPublishMechanism implements PublishMechanism {

	public static final Logger log = LoggerFactory
			.getLogger(DummyPublishMechanism.class);

	/**
	 * 
	 */
	public DummyPublishMechanism() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.iplantc.de.publish.mechanism.api.PublishMechanism#preValidate(org
	 * .iplantc.de.publish.mechanism.api.PublishActionDescriptor,
	 * org.iplantc.de.publish.mechanism.api.PublishContext)
	 */
	@Override
	public PublishResult preValidate(
			PublishActionDescriptor publishActionDescriptor,
			PublishContext publishContext) {

		log.info("preValidate()");
		log.info("publishActionDescriptor:{}", publishActionDescriptor);
		log.info("publishContext:{}", publishContext);
		PublishResult publishResult = new PublishResult();
		publishResult.setPublishStatus(PublishStatusEnum.SUBMITTED);
		publishResult.setResponseCode(PublishResult.PUBLISH_RESULT_NORMAL);
		publishResult.setResponseMessage("preValidate successful");
		publishResult.setPublishPhase(PublishPhaseEnum.PRE_VALIDATE);
		return publishResult;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.iplantc.de.publish.mechanism.api.PublishMechanism#publish(org.iplantc
	 * .de.publish.mechanism.api.PublishActionDescriptor,
	 * org.iplantc.de.publish.mechanism.api.PublishContext)
	 */
	@Override
	public PublishResult publish(
			PublishActionDescriptor publishActionDescriptor,
			PublishContext publishContext) {

		log.info("publish()");
		log.info("publishActionDescriptor:{}", publishActionDescriptor);
		log.info("publishContext:{}", publishContext);
		PublishResult publishResult = new PublishResult();
		publishResult.setPublishStatus(PublishStatusEnum.SUBMITTED);
		publishResult.setResponseCode(PublishResult.PUBLISH_RESULT_NORMAL);
		publishResult.setResponseMessage("publish successful");
		publishResult.setPublishPhase(PublishPhaseEnum.PUBLISH);
		return publishResult;

	}

}
