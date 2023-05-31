package tqs.project.mailMoverPlatform.repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.project.mailMoverPlatform.entities.ACP;

@Repository
public interface AcpRepository extends JpaRepository<ACP,Long>{
    Optional<ACP> findById(Long id);
    List<ACP> findAll();
    Optional<ACP> findByEmail(String email);
}
