package com.multirdbsetup.datatransfer.thirdRepo;

import com.multirdbsetup.datatransfer.thirdDataSourceEntity.Application_secondary;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mukulgarg
 * @date 08/11/23
 */

@Repository
@PersistenceContext(unitName = "thirdEntityManager")
public interface ApplicationSecondaryRepo extends JpaRepository<Application_secondary, String> {
	
	List<Application_secondary> findAllByIdAfter(Date date);
	Application_secondary findFirstByCurrentStatusDetails (String currentStatusDetails);
	Application_secondary findById(Long id);
}
