package com.multirdbsetup.datatransfer.secondaryRepo;

import com.multirdbsetup.datatransfer.secondaryDataSourceEntity.ApplicationDetail_secondary;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mukulgarg
 * @date 06/11/23
 */

@Repository
@PersistenceContext(unitName = "secondaryEntityManager")
public interface ApplicationDetailSecondaryRepo extends JpaRepository<ApplicationDetail_secondary, String> {
	
	List<ApplicationDetail_secondary> findAllByCreatedDateAfter(Date createdDate);
	
	ApplicationDetail_secondary findAllByStudentId(Long id);
}
