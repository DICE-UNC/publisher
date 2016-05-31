/**
 * 
 */
package org.iplantc.de.publish.mechanism.api.annotations;

import java.lang.annotation.Documented;

/**
 * Annotation that marks up a publish mechanism for discovery
 * 
 * @author Mike Conway - DICE
 *
 */
@Documented
public @interface PublicationDriver {
	/**
	 * Author of the publisher plugin
	 * 
	 * @return <code>String</code> with author
	 */
	String author();

	/**
	 * Context-dependent version string
	 * 
	 * @return <code>String</code> with version string
	 */
	String version();

	/**
	 * Display name of the publisher driver
	 * 
	 * @return <code>String</code> with the name of the driver
	 */
	String name();

	/**
	 * Description of the driver (for mouseovers or other user doc)
	 * 
	 * @return <code>String</code> with the description of the driver
	 */
	String description();

	/**
	 * Indicates if the publish action is asynchronous (through the app)
	 * 
	 * @return
	 */
	boolean isAsynch() default false;
}
