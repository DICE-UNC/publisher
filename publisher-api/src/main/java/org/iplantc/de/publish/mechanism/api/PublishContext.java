/**
 * 
 */
package org.iplantc.de.publish.mechanism.api;

import java.util.Map;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;

/**
 * Provides callbacks and context from DE publish service to the underlying
 * foreground mechanism. This allows the preValidate and synchronous parts of
 * the publish methods the ability to manipulate iRODS or call DE based services
 * that are selectively exposed.
 * 
 * @author Mike Conway - DICE
 *
 */
public interface PublishContext {

	/**
	 * @return the account to use when connecting to iRODS.
	 */
	IRODSAccount getIrodsAccount();

	/**
	 * @return the iRODS access object factory.
	 */
	IRODSAccessObjectFactory getAccessObjectFactory();

	/**
	 * @param irodsPath
	 *            an absolute path to a data object or collection in iRODS.
	 * @return the metadata associated with {@code irodsPath}.
	 */
	Map<String, String> getPathMetadata(String irodsPath);

	JobSubmissionResult submitJob(JobSubmission jobSubmission);
}
