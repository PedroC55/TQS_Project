package tqs.project.mailMoverPlatform.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tqs.project.mailMoverPlatform.entities.Order;

@Repository
public interface OrdersRepository extends MongoRepository<Order,String> {
    Optional<Order> findById(String id);
    List<Order> findAll();
    Order findByTrackingNumber(String tracking_number);
    List<Order> findByAcpId(String acpId);
   
}
