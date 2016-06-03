/**
 * 
 */
package org.iplantc.de.publish.mechanism.api.exception;

import org.irods.jargon.core.exception.JargonException;

/**
 * General exception in publication
 * 
 * @author Mike Conway - DICE
 *
 */
public class PublicationException extends JargonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1724154819199402955L;

	/**
	 * @param message
	 */
	public PublicationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PublicationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PublicationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public PublicationException(Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public PublicationException(String message, int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public PublicationException(String message, Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

}
