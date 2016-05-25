/**
 * 
 */
package org.iplantc.de.publish.mechanism.api;

/**
 * Describes a publish mechanism, this is a plugin that can format and publish
 * collection data sorted as an AIP in the DE/iRODS environment as a DIP in an
 * arbitrary target repository. The mechanism is a simple api that is developed
 * underneath this interface, and can surface a preValidate foreground method
 * that can do sanity checks and pre-processing, and a sych|asynch publish
 * action that actually creates the DIP in the target repository.
 * 
 * @author Mike Conway - DICE, Dennis Roberts, CyVerse
 *
 */
public interface PublishMechanism {

	/**
	 * Do a pre-validate that may check for all necessary requirements, and may
	 * also pre-process the data before publishing. This is necessary as the
	 * actual publish phase may be run asynchronously as an app. Having an
	 * initial foreground step thus gives a hook for a publish mechanism that
	 * needs to itneract with iRODS or DE.
	 * <p/>
	 * Note that, depending on the mechanism, the publish action can also run in
	 * the foreground, and this may be suitable for light-weight publishing
	 * workflows.
	 * <p/>
	 * Note that the publish action should trap any checked or unchecked
	 * exception and internally convert it into a <code>PublishResult</code> so
	 * that the publish mechanism can handle exception and validation logic.
	 * 
	 * @param publishActionDescriptor
	 *            {@link PublishActionDescriptor} that provides the necessary
	 *            context for the mechanism to find and do the publishing.
	 * @param publishContext
	 *            {@link PublishContext} with callbacks and hooks to call DE and
	 *            iRODS services
	 * @return {@link PublishResult} That represents the status and any error or
	 *         validation information
	 */
	public PublishResult preValidate(
			final PublishActionDescriptor publishActionDescriptor,
			final PublishContext publishContext);

	/**
	 * This method will be called after success in the <code>preValidate</code>
	 * and represents the actual publish action. This may be done synchronously,
	 * or the result can reflect an asynchronous submission of a publication
	 * action. Asynchronous actions are handled through the DE App abstraction,
	 * and results are processed using the notification and error handling logic
	 * as it exists. Publish mechansims can also handle exceptions and any
	 * required clean-up witin this publish mechanism.
	 * 
	 * @param publishActionDescriptor
	 *            {@link PublishDescriptor} with the necessary context for an
	 *            individual publicastion action
	 * @param publishContext
	 *            {@link PublishContext} with callbacks and hooks to call DE and
	 *            iRODS services
	 * @return {@link PublishResult} with the result of this publish action. For
	 *         synchronous actions, this may be the failure or completion
	 *         success, for an asynchyronous action, it would represent the
	 *         success or failure in enquing the the publish action.
	 */
	public PublishResult publish(
			final PublishActionDescriptor publishActionDescriptor,
			final PublishContext publishContext);

}
