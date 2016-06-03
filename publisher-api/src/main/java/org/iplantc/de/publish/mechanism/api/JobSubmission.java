package org.iplantc.de.publish.mechanism.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a job submission to be sent to the Discovery Environment for
 * asynchronous data publication.
 */
public class JobSubmission {

	public static final int MAX_JOB_NAME_LENGTH = 255;
	public static final int MAX_APP_ID_LENGTH = 255;

	/**
	 * The job name. This field should contain a brief (
	 * {@code MAX_JOB_NAME_LENGTH} characters maximum) description of the job.
	 * Duplicate job names are permitted, but it is helpful for the job name to
	 * be unique.
	 */
	private final String name;

	/**
	 * @return the job name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * The job description. This field contains a longer description of the job.
	 */
	private final String description;

	/**
	 * @return the job description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * The ID of the Discovery Environment app that is used to determine how the
	 * job will be executed.
	 */
	private final String appId;

	/**
	 * @return the app ID.
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * The path to the directory in iRODS where log files will be placed after
	 * the publishing job is complete.
	 */
	private final String outputDir;

	/**
	 * @return the path to the output directory.
	 */
	public String getOutputDir() {
		return outputDir;
	}

	/**
	 * If set to {@code true}, the Discovery Environment will automatically
	 * create a subdirectory of the selected output directory, with a name that
	 * is generated from the job name and the job submission time.
	 */
	private final boolean createOutputSubdir;

	/**
	 * @return true if a subdirectory should be created for output files.
	 */
	public boolean shouldCreateOutputSubdir() {
		return createOutputSubdir;
	}

	/**
	 * If set to {@code true}, the Discovery Environment will archive log files
	 * from the job execution as job output files.
	 */
	private final boolean archiveLogs;

	/**
	 * @return true if log files should be archived.
	 */
	public boolean shouldArchiveLogs() {
		return archiveLogs;
	}

	/**
	 * If set to {@code true}, the Discovery Environment will notify the user
	 * when the job status changes and will send an email when the job is
	 * complete.
	 */
	private final boolean notify;

	/**
	 * @return true if the user should be notified of job status changes.
	 */
	public boolean shouldNotify() {
		return notify;
	}

	/**
	 * A map of parameter IDs to parameter values. This map determines which
	 * values are passed to the command line when the job is executed.
	 */
	private final Map<String, String> parameterValues;

	/**
	 * @param builder
	 *            the job submission builder.
	 */
	private JobSubmission(JobSubmissionBuilder builder) {
		this.name = builder.name;
		this.description = builder.description;
		this.appId = builder.appId;
		this.outputDir = builder.outputDir;
		this.createOutputSubdir = builder.createOutputSubdir;
		this.archiveLogs = builder.archiveLogs;
		this.notify = builder.notify;
		this.parameterValues = builder.parameterValues;
	}

	/**
	 * A builder class that can be used to build a new job submission.
	 */
	public static class JobSubmissionBuilder {
		private String name;
		private String description;
		private String appId;
		private String outputDir;
		private boolean createOutputSubdir = true;
		private boolean archiveLogs = true;
		private boolean notify = true;
		private Map<String, String> parameterValues = new HashMap<String, String>();

		/**
		 * Sets the name of the job, which has a maximum length of
		 * {@code MAX_JOB_NAME_LENGTH} characters. It is helpful, but not
		 * absolutely necessary, for the job name to be unique. This field is
		 * required.
		 *
		 * @param name
		 *            the job name.
		 * @return the job submission builder.
		 */
		public JobSubmissionBuilder setName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Sets the job description. This field is optional.
		 *
		 * @param description
		 *            the job description.
		 * @return the job submission builder.
		 */
		public JobSubmissionBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		/**
		 * Sets the ID of the app to use when a job is submitted. This field is
		 * required and has a maximum length of {@code MAX_APP_ID_LENGTH}
		 * characters.
		 *
		 * @param appId
		 *            the app ID.
		 * @return the job submission builder.
		 */
		public JobSubmissionBuilder setAppId(String appId) {
			this.appId = appId;
			return this;
		}

		/**
		 * Sets the path to the output directory in iRODS, which is where the
		 * log files and any job output files will be stored when they are
		 * archived. This field is required.
		 *
		 * @param outputDir
		 *            the path to the output directory.
		 * @return the job submission builder.
		 */
		public JobSubmissionBuilder setOutputDir(String outputDir) {
			this.outputDir = outputDir;
			return this;
		}

		/**
		 * Sets a flag indicating whether or not a subdirectory should be
		 * created underneath the selected output directory. If set to
		 * {@code true}, a new subdirectory with a named based on the job name
		 * and the job submission time will be created. This field defaults to
		 * {@code true} if not specified.
		 *
		 * @param create
		 *            {@code true} if a subdirectory should be created.
		 * @return the job submission builder.
		 */
		public JobSubmissionBuilder setCreateOutputSubdir(boolean create) {
			this.createOutputSubdir = create;
			return this;
		}

		/**
		 * Sets a flag indicating whether or not the log files that are
		 * generated during job execution should be archived in the job output
		 * directory (recommended). This field defaults to {@code true} if not
		 * specified.
		 *
		 * @param archive
		 *            {@code true} if the log files should be archived.
		 * @return the job submission builder.
		 */
		public JobSubmissionBuilder setArchiveLogs(boolean archive) {
			this.archiveLogs = archive;
			return this;
		}

		/**
		 * Sets a flag indicating whether or not the user should be notified
		 * when the job status changes (recommended). This field defaults to
		 * {@code true} if not specified.
		 *
		 * @param notify
		 *            {@code true} if the user should be notified.
		 * @return the job submission builder.
		 */
		public JobSubmissionBuilder setNotify(boolean notify) {
			this.notify = notify;
			return this;
		}

		/**
		 * Sets a property value for the job submission. The property ID is
		 * obtained from the app description. The property value is the value
		 * that should be used for that property when the job is executed.
		 *
		 * @param id
		 *            the property ID.
		 * @param value
		 *            the property value.
		 * @return the job submission builder.
		 */
		public JobSubmissionBuilder setParameterValue(String id, String value) {
			this.parameterValues.put(id, value);
			return this;
		}

		/**
		 * Buildes the job submission.
		 *
		 * @return the job submission.
		 */
		public JobSubmission build() {
			validateJobName();
			validateAppId();
			validateOutputDir();
			return new JobSubmission(this);
		}

		/**
		 * Validates the job name.
		 *
		 * @throws NullPointerException
		 *             if the job name is null.
		 * @throws IllegalArgumentException
		 *             if the job name is empty or too long.
		 */
		private void validateJobName() {
			if (name == null) {
				throw new NullPointerException("a job name is required");
			}
			if (name.length() == 0) {
				throw new IllegalArgumentException(
						"the job name may not be empty");
			}
			if (name.length() > MAX_JOB_NAME_LENGTH) {
				String msg = "the job name may not be more than "
						+ MAX_JOB_NAME_LENGTH + " characters long";
				throw new IllegalArgumentException(msg);
			}
		}

		/**
		 * Validates the app ID.
		 *
		 * @throws NullPointerException
		 *             if the app ID is null.
		 * @throws IllegalArgumentException
		 *             if the app ID is empty or too long.
		 */
		private void validateAppId() {
			if (appId == null) {
				throw new NullPointerException("an app ID is required");
			}
			if (appId.length() == 0) {
				throw new IllegalArgumentException(
						"the app ID may not be empty");
			}
			if (appId.length() > MAX_APP_ID_LENGTH) {
				String msg = "the app ID may not be more than "
						+ MAX_APP_ID_LENGTH + " characters long";
				throw new IllegalArgumentException(msg);
			}
		}

		/**
		 * Validates the output directory.
		 *
		 * @throws NullPointerException
		 *             if the output directory is null.
		 * @throws IllegalArgumentException
		 *             if the output directory is empty.
		 */
		private void validateOutputDir() {
			if (outputDir == null) {
				throw new NullPointerException(
						"the output directory may not be null");
			}
			if (outputDir.length() == 0) {
				throw new IllegalArgumentException(
						"the output directory may not be empty");
			}
		}
	}

	/**
	 * @return a {@link JobSubmissionBuilder} that can be used to build a new
	 *         job submission.
	 */
	public static JobSubmissionBuilder builder() {
		return new JobSubmissionBuilder();
	}
}
