/**
 * 
 */
package org.iplantc.de.publish.sandbox;

/**
 * Configurable classloader for sandbox testing of jars
 * 
 * @author Mike Conway - DICE
 *
 */
public class SandboxClassloader extends ClassLoader {

	/**
	 * 
	 */
	public SandboxClassloader() {
	}

	/**
	 * @param classloader
	 */
	public SandboxClassloader(ClassLoader classloader) {
		super(classloader);
	}

}
