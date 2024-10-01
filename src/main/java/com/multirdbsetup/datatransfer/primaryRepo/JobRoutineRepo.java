package com.multirdbsetup.datatransfer.primaryRepo;

import com.multirdbsetup.datatransfer.primaryDataSourceEntity.JobRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author mukulgarg
 * @date 26/12/23
 */

@Repository
public interface JobRoutineRepo extends JpaRepository<JobRoutine, String> {
	
	@Query(value = "select * from JOB_ROUTINE where JOB_TYPE = :jobType", nativeQuery = true)
	JobRoutine findByJobType(@Param("jobType") String jobType);
}
