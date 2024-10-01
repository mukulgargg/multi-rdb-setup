package com.multirdbsetup.datatransfer.primaryRepo;

import com.multirdbsetup.datatransfer.primaryDataSourceEntity.ApplicationDetails_primary;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mukulgarg
 * @date 06/11/23
 */

@Repository(value = "vlpApplicationDetialPrimaryRepo")
@PersistenceContext(unitName = "primaryEntityManager")
public interface ApplicationDetailsPrimaryRepo extends JpaRepository<ApplicationDetails_primary, Long> {

}
