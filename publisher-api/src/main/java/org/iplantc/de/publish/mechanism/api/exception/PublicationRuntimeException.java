/**
 * 
 */
package org.iplantc.de.publish.mechanism.api.exception;

import org.irods.jargon.core.exception.JargonRuntimeException;

/**
 * General runtime exception in publication
 * 
 * @author Mike Conway - DICE
 *
 */
public class PublicationRuntimeException extends JargonRuntimeException {

	private static final long serialVersionUID = 8593510749760911201L;

	public PublicationRuntimeException() {
	}

	/**
	 * @param message
	 */
	public PublicationRuntimeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PublicationRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PublicationRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
