package com.multirdbsetup.datatransfer.primaryDataSourceEntity;

import com.multirdbsetup.datatransfer.enums.JobType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author mukulgarg
 * @date 08/11/23
 */

@Entity
@Table(name = "JOB_SCHEDULES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSchedule implements Serializable {
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "JOB_TYPE")
	@Enumerated(EnumType.STRING)
	private JobType jobType;
	
	@Column(name = "LAST_EXECUTION_COMPLETE")
	private Date lastExecutionComplete;
	
	@Column(name = "RECORDS_PROCESSED")
	private int recordsProcessed;
	
	@Column(name = "TIME_TAKEN")
	private Long timeTaken;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	public interface IdStep {
		JobTypeStep withId(Long id);
	}
	
	public interface JobTypeStep {
		LastExecutionCompleteStep withJobType(JobType jobType);
	}
	
	public interface LastExecutionCompleteStep {
		RecordsProcessedStep withLastExecutionComplete(Date lastExecutionComplete);
	}
	
	public interface RecordsProcessedStep {
		TimeTakenStep withRecordsProcessed(int recordsProcessed);
	}
	
	public interface TimeTakenStep {
		RemarksStep withTimeTaken(Long timeTaken);
	}
	
	public interface RemarksStep {
		BuildStep withRemarks(String remarks);
	}
	
	public interface BuildStep {
		JobSchedule build();
	}
	
	public static class Builder
		implements IdStep, JobTypeStep, LastExecutionCompleteStep, RecordsProcessedStep, TimeTakenStep, RemarksStep,
		BuildStep {
		private Long id;
		private JobType jobType;
		private Date lastExecutionComplete;
		private int recordsProcessed;
		private Long timeTaken;
		private String remarks;
		
		private Builder() {
		}
		
		public static IdStep jobSchedule() {
			return new Builder();
		}
		
		@Override
		public JobTypeStep withId(Long id) {
			this.id = id;
			return this;
		}
		
		@Override
		public LastExecutionCompleteStep withJobType(JobType jobType) {
			this.jobType = jobType;
			return this;
		}
		
		@Override
		public RecordsProcessedStep withLastExecutionComplete(Date lastExecutionComplete) {
			this.lastExecutionComplete = lastExecutionComplete;
			return this;
		}
		
		@Override
		public TimeTakenStep withRecordsProcessed(int recordsProcessed) {
			this.recordsProcessed = recordsProcessed;
			return this;
		}
		
		@Override
		public RemarksStep withTimeTaken(Long timeTaken) {
			this.timeTaken = timeTaken;
			return this;
		}
		
		@Override
		public BuildStep withRemarks(String remarks) {
			this.remarks = remarks;
			return this;
		}
		
		@Override
		public JobSchedule build() {
			return new JobSchedule(
				this.id,
				this.jobType,
				this.lastExecutionComplete,
				this.recordsProcessed,
				this.timeTaken,
				this.remarks
			);
		}
	}
}
