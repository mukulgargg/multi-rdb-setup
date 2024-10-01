package com.multirdbsetup.datatransfer.primaryRepo;


import com.multirdbsetup.datatransfer.enums.JobType;
import com.multirdbsetup.datatransfer.primaryDataSourceEntity.JobSchedule;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mukulgarg
 * @date 08/11/23
 */

@Repository(value = "jobScheduleRepo")
@PersistenceContext(unitName = "primaryEntityManager")
public interface JobScheduleRepo extends JpaRepository<JobSchedule, Long> {

    JobSchedule findFirstByJobTypeOrderByLastExecutionCompleteDesc(JobType jobType);
}
