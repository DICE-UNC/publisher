/**
 * 
 */
package org.iplantc.de.publish.sandbox;

/**
 * Configuration information for sandbox
 * 
 * @author Mike Conway - DICE
 *
 */
public class SandboxConfiguration {

	/**
	 * Directory to scan for plugin publisher jar files
	 */
	private String jarFilePluginDir = "";

	/**
	 * @return the jarFilePluginDir
	 */
	public String getJarFilePluginDir() {
		return jarFilePluginDir;
	}

	/**
	 * @param jarFilePluginDir
	 *            the jarFilePluginDir to set
	 */
	public void setJarFilePluginDir(String jarFilePluginDir) {
		this.jarFilePluginDir = jarFilePluginDir;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SandboxConfiguration [");
		if (jarFilePluginDir != null) {
			builder.append("jarFilePluginDir=").append(jarFilePluginDir);
		}
		builder.append("]");
		return builder.toString();
	}

}
