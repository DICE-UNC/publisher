/**
 * 
 */
package org.iplantc.de.publish.mechanism.api;

/**
 * Describes publisher plugins that can be discovered and utilized by the
 * publisher system
 * 
 * @author Mike Conway - DICE
 *
 */
public class PublisherPluginDescription {

	private Class<?> publisherClass;
	private String publisherName;
	private String description;
	private boolean asynch;
	private String author;
	private String version;

	/**
	 * 
	 */
	public PublisherPluginDescription() {
	}

	/**
	 * @return the publisherClass
	 */
	public Class<?> getPublisherClass() {
		return publisherClass;
	}

	/**
	 * @param publisherClass
	 *            the publisherClass to set
	 */
	public void setPublisherClass(Class<?> publisherClass) {
		this.publisherClass = publisherClass;
	}

	/**
	 * @return the publisherName
	 */
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * @param publisherName
	 *            the publisherName to set
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the asynch
	 */
	public boolean isAsynch() {
		return asynch;
	}

	/**
	 * @param asynch
	 *            the asynch to set
	 */
	public void setAsynch(boolean asynch) {
		this.asynch = asynch;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PublisherDescription [");
		if (publisherClass != null) {
			builder.append("publisherClass=").append(publisherClass)
					.append(", ");
		}
		if (publisherName != null) {
			builder.append("publisherName=").append(publisherName).append(", ");
		}
		if (description != null) {
			builder.append("description=").append(description).append(", ");
		}
		builder.append("asynch=").append(asynch).append(", ");
		if (author != null) {
			builder.append("author=").append(author).append(", ");
		}
		if (version != null) {
			builder.append("version=").append(version);
		}
		builder.append("]");
		return builder.toString();
	}

}
