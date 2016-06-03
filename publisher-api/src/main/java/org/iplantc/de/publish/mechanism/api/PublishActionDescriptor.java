package org.iplantc.de.publish.mechanism.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Describes metadata associated with a publish action
 * 
 * @author Mike Conway - DICE
 *
 */
public class PublishActionDescriptor {

	/**
	 * absolute path to the iRODS collection that is the 'source' of the
	 * publish. The target will be determined by the publish mechanism, but may
	 * be guided by add'l parameters unique to that publish mechanism. These
	 * parameters may be provided to a publish mechanism through multiple means,
	 * and this descriptor provides an arbitrary map of parameter settings that
	 * may be interpreted by the publish mechanism
	 */
	private String publishSourceAbsolutePath = "";

	/**
	 * This is the path of any intermediary collection created as part of the
	 * publish operation. This will be set by the publish mechanism itself as a
	 * sort of 're-direct'. It may be a collection, or a file, if the
	 * pre-validate creates a bundle of some type.
	 * <p/>
	 * The publish mechanism will call the pre-validate. If an intermediary is
	 * created, that will be passed back in the {@link PublishResult} and set
	 * subsequently set in the {@link PublishActionDescriptor} passed to the
	 * actual publish method. The publish method will honor this intermediary
	 * path.
	 */
	private String publishIntermediaryAbsolutePath = "";

	/**
	 * Map of free-form string parameters that may pass information to the
	 * publish mechanism to tune the publish behavior
	 */
	private Map<String, String> publishProperties = new HashMap<String, String>();

	public PublishActionDescriptor() {
	}

	/**
	 * @return the publishSourceAbsolutePath
	 */
	public String getPublishSourceAbsolutePath() {
		return publishSourceAbsolutePath;
	}

	/**
	 * @param publishSourceAbsolutePath
	 *            the publishSourceAbsolutePath to set
	 */
	public void setPublishSourceAbsolutePath(String publishSourceAbsolutePath) {
		this.publishSourceAbsolutePath = publishSourceAbsolutePath;
	}

	/**
	 * @return the publishProperties
	 */
	public Map<String, String> getPublishProperties() {
		return publishProperties;
	}

	/**
	 * @param publishProperties
	 *            the publishProperties to set
	 */
	public void setPublishProperties(Map<String, String> publishProperties) {
		this.publishProperties = publishProperties;
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
		builder.append("PublishActionDescriptor [");
		if (publishSourceAbsolutePath != null) {
			builder.append("publishSourceAbsolutePath=")
					.append(publishSourceAbsolutePath).append(", ");
		}
		if (publishIntermediaryAbsolutePath != null) {
			builder.append("publishIntermediaryAbsolutePath=")
					.append(publishIntermediaryAbsolutePath).append(", ");
		}
		if (publishProperties != null) {
			builder.append("publishProperties=").append(
					toString(publishProperties.entrySet(), maxLen));
		}
		builder.append("]");
		return builder.toString();
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext()
				&& i < maxLen; i++) {
			if (i > 0) {
				builder.append(", ");
			}
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the publishIntermediaryAbsolutePath
	 */
	public String getPublishIntermediaryAbsolutePath() {
		return publishIntermediaryAbsolutePath;
	}

	/**
	 * @param publishIntermediaryAbsolutePath
	 *            the publishIntermediaryAbsolutePath to set
	 */
	public void setPublishIntermediaryAbsolutePath(
			String publishIntermediaryAbsolutePath) {
		this.publishIntermediaryAbsolutePath = publishIntermediaryAbsolutePath;
	}

}
