package tqs.project.mailMoverPlatform.Repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tqs.project.mailMoverPlatform.Entities.ACP;

@Repository
public interface AcpRepository  extends MongoRepository<ACP,String>{
    Optional<ACP> findById(String id);
    List<ACP> findAll();
}
