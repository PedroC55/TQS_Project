package tqs.project.mailMoverPlatform.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findById(Long id);
    List<Order> findAll();
    List<Order> findByAcp(ACP acp);
    Order findByAcpAndId(ACP acp, Long id);
}
