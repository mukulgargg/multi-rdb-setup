package com.multirdbsetup.datatransfer.primaryRepo;


import com.multirdbsetup.datatransfer.primaryDataSourceEntity.Application_primary;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mukulgarg
 * @date 08/11/23
 */

@Repository(value = "ApplicationPrimaryRepo")
@PersistenceContext(unitName = "primaryEntityManager")
public interface ApplicationPrimaryRepo extends JpaRepository<Application_primary, Long> {
}
