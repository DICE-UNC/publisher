/**
 * 
 */
package org.iplantc.de.publish.mechanism.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes the result of a pre-publish or publish action. This can include
 * status information, and any validation messages, including an overall result
 * as well as the ability to pass validation information per parameter
 * 
 * @author Mike Conway - DICE
 *
 */
public class PublishResult {

	public static final int PUBLISH_RESULT_NORMAL = 0;

	/**
	 * Represents the phase of the publishing cycle that this response pertains
	 * to
	 */
	private PublishPhaseEnum publishPhase;
	/**
	 * Enum value that represents the status at this particular phase
	 */

	/**
	 * Represents an intermediate location for a DIP that may have been created
	 * during the pre-process phase. This allows a publish mechanism to publish
	 * from a location that is different than the original source. For example,
	 * the preprocess step may have created a new arrangement, or it may have
	 * created some sort of bag or archive file.
	 * <p/>
	 * The publish system will pass this intermediate DIP location between the
	 * result of the validate and the publication step.
	 */
	private String intermediateDipAbsolutePath = "";

	private PublishStatusEnum publishStatus;

	/**
	 * Numeric response code that may be particular to the publish mechanism
	 */
	private int responseCode = 0;

	/**
	 * Overall free text response for the publish result
	 */
	private String responseMessage = "";

	/**
	 * Sequential list of log or validation messages
	 */
	private List<String> messageLog = new ArrayList<String>();

	/**
	 * Optional stack trace information
	 */
	private String stackTrace = "";

	/**
	 * @return the publishPhase
	 */
	public PublishPhaseEnum getPublishPhase() {
		return publishPhase;
	}

	/**
	 * @param publishPhase
	 *            the publishPhase to set
	 */
	public void setPublishPhase(PublishPhaseEnum publishPhase) {
		this.publishPhase = publishPhase;
	}

	/**
	 * @return the publishStatus
	 */
	public PublishStatusEnum getPublishStatus() {
		return publishStatus;
	}

	/**
	 * @param publishStatus
	 *            the publishStatus to set
	 */
	public void setPublishStatus(PublishStatusEnum publishStatus) {
		this.publishStatus = publishStatus;
	}

	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage
	 *            the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * @return the messageLog
	 */
	public List<String> getMessageLog() {
		return messageLog;
	}

	/**
	 * @param messageLog
	 *            the messageLog to set
	 */
	public void setMessageLog(List<String> messageLog) {
		this.messageLog = messageLog;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("PublishResult [");
		if (publishPhase != null) {
			builder.append("publishPhase=").append(publishPhase).append(", ");
		}
		if (intermediateDipAbsolutePath != null) {
			builder.append("intermediateDipAbsolutePath=")
					.append(intermediateDipAbsolutePath).append(", ");
		}
		if (publishStatus != null) {
			builder.append("publishStatus=").append(publishStatus).append(", ");
		}
		builder.append("responseCode=").append(responseCode).append(", ");
		if (responseMessage != null) {
			builder.append("responseMessage=").append(responseMessage)
					.append(", ");
		}
		if (messageLog != null) {
			builder.append("messageLog=")
					.append(messageLog.subList(0,
							Math.min(messageLog.size(), maxLen))).append(", ");
		}
		if (stackTrace != null) {
			builder.append("stackTrace=").append(stackTrace);
		}
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the stackTrace
	 */
	public String getStackTrace() {
		return stackTrace;
	}

	/**
	 * @param stackTrace
	 *            the stackTrace to set
	 */
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 * @return the intermediateDipAbsolutePath
	 */
	public String getIntermediateDipAbsolutePath() {
		return intermediateDipAbsolutePath;
	}

	/**
	 * @param intermediateDipAbsolutePath
	 *            the intermediateDipAbsolutePath to set
	 */
	public void setIntermediateDipAbsolutePath(
			String intermediateDipAbsolutePath) {
		this.intermediateDipAbsolutePath = intermediateDipAbsolutePath;
	}

}
