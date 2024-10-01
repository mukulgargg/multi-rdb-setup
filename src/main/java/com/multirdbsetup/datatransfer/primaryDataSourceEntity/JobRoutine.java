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
 * @date 26/12/23
 */

@Entity
@Table(name = "JOB_ROUTINE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobRoutine implements Serializable {
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private String id;
	
	@Column(name = "JOB_TYPE", unique = true, nullable = false)
	private String jobType;
	
	@Column(name = "FLAG", nullable = false)
	private String flag;
	
	@Column(name = "CRON", nullable = false)
	private String cron;
}
