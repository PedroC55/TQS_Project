package tqs.project.mailMoverPlatform.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.project.mailMoverPlatform.Entities.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order,String> {
    Order findByTrackingNumber(String tracking_number);
    List<Order> findByClientName(String client_name);
    List<Order> findByAcpId(String acpId);
}
