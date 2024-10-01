package com.multirdbsetup.datatransfer.secondaryDataSourceEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "APPLICATIONDETAILS", schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PersistenceContext(unitName = "secondaryEntityManager")
public class ApplicationDetail_secondary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENTID", nullable = false)
	@PrimaryKeyJoinColumn
	private Long studentId;
	
	@Lob
	@Column(name = "FILEDATA", nullable = false, columnDefinition = "nvarchar(MAX)")
	private String fileData;
	
	@Column(name = "CREATEDDATE", nullable = false)
	private Date createdDate;
	
	@Column(name = "ISACTIVATE", length = 10)
	private String isActivate;
	
	@Column(name = "PROJECTCOST", precision = 18, scale = 2, nullable = false)
	private BigDecimal projectCost;
	
	@Column(name = "FILEID")
	private Long fileId;
	
	public interface StudentIdStep {
		FileDataStep withStudentId(Long studentId);
	}
	
	public interface FileDataStep {
		CreatedDateStep withFileData(String fileData);
	}
	
	public interface CreatedDateStep {
		IsActivateStep withCreatedDate(Date createdDate);
	}
	
	public interface IsActivateStep {
		ProjectCostStep withIsActivate(String isActivate);
	}
	
	public interface ProjectCostStep {
		FileIdStep withProjectCost(BigDecimal projectCost);
	}
	
	public interface FileIdStep {
		BuildStep withFileId(Long fileId);
	}
	
	public interface BuildStep {
		ApplicationDetail_secondary build();
	}
	
	public static class Builder
		implements StudentIdStep, FileDataStep, CreatedDateStep, IsActivateStep, ProjectCostStep, FileIdStep,
		BuildStep {
		private Long studentId;
		private String fileData;
		private Date createdDate;
		private String isActivate;
		private BigDecimal projectCost;
		private Long fileId;
		
		private Builder() {
		}
		
		public static StudentIdStep applicationDetail_secondary() {
			return new Builder();
		}
		
		@Override
		public FileDataStep withStudentId(Long studentId) {
			this.studentId = studentId;
			return this;
		}
		
		@Override
		public CreatedDateStep withFileData(String fileData) {
			this.fileData = fileData;
			return this;
		}
		
		@Override
		public IsActivateStep withCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
			return this;
		}
		
		@Override
		public ProjectCostStep withIsActivate(String isActivate) {
			this.isActivate = isActivate;
			return this;
		}
		
		@Override
		public FileIdStep withProjectCost(BigDecimal projectCost) {
			this.projectCost = projectCost;
			return this;
		}
		
		@Override
		public BuildStep withFileId(Long fileId) {
			this.fileId = fileId;
			return this;
		}
		
		@Override
		public ApplicationDetail_secondary build() {
			return new ApplicationDetail_secondary(
				this.studentId,
				this.fileData,
				this.createdDate,
				this.isActivate,
				this.projectCost,
				this.fileId
			);
		}
	}
}
