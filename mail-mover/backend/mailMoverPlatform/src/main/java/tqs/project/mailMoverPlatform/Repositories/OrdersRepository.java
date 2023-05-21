package tqs.project.mailMoverPlatform.repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order,Long> {
    Optional<Order> findById(Long id);
    List<Order> findAll();
    Order findByTrackingNumber(String tracking_number);
    List<Order> findByAcp(ACP acp);
   
}
