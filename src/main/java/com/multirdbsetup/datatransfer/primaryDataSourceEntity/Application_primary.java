package com.multirdbsetup.datatransfer.primaryDataSourceEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author mukulgarg
 * @date 08/11/23
 */

@Entity
@Table(name = "APPLICATION_LIST")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application_primary implements Serializable {
	
	@Column(name = "ID", unique = true, nullable = false)
	@Id
	private Long id;
	
	@Column(name = "ISPROCESSED")
	private String isProcessed;
	
	@Column(name = "DOCUMENTLIST")
	private String documentList;
	
	@Column(name = "CURRENTSTATUSDETAILS")
	private String currentStatusDetails;
	
	@Column(name = "ISMOVED")
	private String isMoved;
	
	public interface IdStep {
		IsProcessedStep withId(Long id);
	}
	
	public interface IsProcessedStep {
		DocumentListStep withIsProcessed(String isProcessed);
	}
	
	public interface DocumentListStep {
		CurrentStatusDetailsStep withDocumentList(String documentList);
	}
	
	public interface CurrentStatusDetailsStep {
		IsMovedStep withCurrentStatusDetails(String currentStatusDetails);
	}
	
	public interface IsMovedStep {
		BuildStep withIsMoved(String isMoved);
	}
	
	public interface BuildStep {
		Application_primary build();
	}
	
	public static class Builder
		implements IdStep, IsProcessedStep, DocumentListStep, CurrentStatusDetailsStep, IsMovedStep, BuildStep {
		private Long id;
		private String isProcessed;
		private String documentList;
		private String currentStatusDetails;
		private String isMoved;
		
		private Builder() {
		}
		
		public static IdStep application_primary() {
			return new Builder();
		}
		
		@Override
		public IsProcessedStep withId(Long id) {
			this.id = id;
			return this;
		}
		
		@Override
		public DocumentListStep withIsProcessed(String isProcessed) {
			this.isProcessed = isProcessed;
			return this;
		}
		
		@Override
		public CurrentStatusDetailsStep withDocumentList(String documentList) {
			this.documentList = documentList;
			return this;
		}
		
		@Override
		public IsMovedStep withCurrentStatusDetails(String currentStatusDetails) {
			this.currentStatusDetails = currentStatusDetails;
			return this;
		}
		
		@Override
		public BuildStep withIsMoved(String isMoved) {
			this.isMoved = isMoved;
			return this;
		}
		
		@Override
		public Application_primary build() {
			return new Application_primary(
				this.id,
				this.isProcessed,
				this.documentList,
				this.currentStatusDetails,
				this.isMoved
			);
		}
	}
}

