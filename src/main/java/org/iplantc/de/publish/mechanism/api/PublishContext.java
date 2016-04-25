/**
 * 
 */
package org.iplantc.de.publish.mechanism.api;

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

	/*
	 * TODO: enumerate appropriate context and callback hooks
	 */

}
