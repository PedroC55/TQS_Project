package tqs.project.mailMoverPlatform.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("v1/orders")
public class OrdersController {
    @Autowired
    private OrderServiceImpl service;
    @Autowired
    private AcpServiceImpl acp_service;

    @PostMapping("/new/{clientName}/{acp_id}")
    public HashMap<String,Object> createOrder(@PathVariable String clientName, @PathVariable Long acp_id) {
        Optional<ACP> ret_acp = acp_service.getAcpById(acp_id);
        if (ret_acp.isPresent()) {
            ACP acp = ret_acp.get();
            Order _order = new Order(clientName, acp);
            Order order = service.addOrder(_order);
            HashMap<String,Object> ret = new HashMap<>();
            ret.put("id",order.getId());
            ret.put("clientName",order.getclientName());
            ret.put("status",order.getStatus());
            ret.put("storePickUpDate",order.getStorePickUpDate());
            ret.put("acpDeliveryDate",order.getAcpDeliveryDate());
            ret.put("clientPickUpDate",order.getClientPickUpDate());
            HashMap<String,Object> retACP = new HashMap<>();
            retACP.put("id",acp.getId());
            retACP.put("name",acp.getName());
            retACP.put("address",acp.getAddress());
            retACP.put("email",acp.getEmail());
            ret.put("acp",retACP);
            return ret;
        } else{
            HashMap<String,Object> ret = new HashMap<>();
            ret.put("error","acp doesn't exist");
            return ret;
        } 
    }

    @GetMapping("/all")
    public ArrayList<HashMap<String,Object>> getAllOrders() {
        List<Order> allOrders = service.getAllOrders();
        ArrayList<HashMap<String,Object>> ret_list = new ArrayList<>();

        for (Order order : allOrders){
            HashMap<String,Object> ret = new HashMap<>();
            ret.put("id",order.getId());
            ret.put("clientName",order.getclientName());
            ret.put("status",order.getStatus());
            ret.put("storePickUpDate",order.getStorePickUpDate());
            ret.put("acpDeliveryDate",order.getAcpDeliveryDate());
            ret.put("clientPickUpDate",order.getClientPickUpDate());
            HashMap<String,Object> retACP = new HashMap<>();
            retACP.put("id",order.getAcp().getId());
            retACP.put("name",order.getAcp().getName());
            retACP.put("address",order.getAcp().getAddress());
            retACP.put("email",order.getAcp().getEmail());
            ret.put("acp",retACP);
            ret_list.add(ret);
        }
        return ret_list;
    }
    
    @GetMapping("/byAcp/{acp_id}")
    public ArrayList<HashMap<String,Object>> getByAcpId(@PathVariable Long acp_id) {
        Optional<ACP> ret_acp = acp_service.getAcpById(acp_id);
        if (ret_acp.isPresent()) {
            ACP acp = ret_acp.get();
            List<Order> allOrders = service.getByAcp(acp);
            ArrayList<HashMap<String,Object>> ret_list = new ArrayList<>();
            for (Order order : allOrders){
                HashMap<String,Object> ret = new HashMap<>();
                ret.put("id",order.getId());
                ret.put("clientName",order.getclientName());
                ret.put("status",order.getStatus());
                ret.put("storePickUpDate",order.getStorePickUpDate());
                ret.put("acpDeliveryDate",order.getAcpDeliveryDate());
                ret.put("clientPickUpDate",order.getClientPickUpDate());
                HashMap<String,Object> retACP = new HashMap<>();
                retACP.put("id",order.getAcp().getId());
                retACP.put("name",order.getAcp().getName());
                retACP.put("address",order.getAcp().getAddress());
                retACP.put("email",order.getAcp().getEmail());
                ret.put("acp",retACP);
                ret_list.add(ret);
            }
            return ret_list;
        } else{
            ArrayList<HashMap<String,Object>> ret_list = new ArrayList<>();
            return ret_list;
        } 
    }
    
    @GetMapping("/byId/{id}")
    public HashMap<String,Object> getById(@PathVariable Long id) {
        Order order = service.getById(id);
        HashMap<String,Object> ret = new HashMap<>();
        if (order != null) {
            ret.put("id",order.getId());
            ret.put("clientName",order.getclientName());
            ret.put("status",order.getStatus());
            ret.put("storePickUpDate",order.getStorePickUpDate());
            ret.put("acpDeliveryDate",order.getAcpDeliveryDate());
            ret.put("clientPickUpDate",order.getClientPickUpDate());
            HashMap<String,Object> retACP = new HashMap<>();
            retACP.put("id",order.getAcp().getId());
            retACP.put("name",order.getAcp().getName());
            retACP.put("address",order.getAcp().getAddress());
            retACP.put("email",order.getAcp().getEmail());
            ret.put("acp",retACP);
        } else {
            ret.put("error", "doesn't exist");
        }
       
        return ret;
    }

    @GetMapping("/deleteAll")
    public void deleteAllOrders() {
        service.deleteAll();
    }
    
    @GetMapping("/storeToCourier/{id}/{ts}")
    public boolean changeState_STORE_to_COURIER(@PathVariable Long id,@PathVariable Long ts) {
        return service.changeState_STORE_to_COURIER(id, ts);
    }

    @GetMapping("/courierToAcp/{id}/{ts}")
    public boolean changeState_COURIER_to_ACPPOINT(@PathVariable Long id,@PathVariable Long ts) {
        return service.changeState_COURIER_to_ACPPOINT(id, ts);
    }

    @GetMapping("/acpToCollected/{id}/{ts}")
    public boolean changeState_ACPPOINT_to_COLLECTED(@PathVariable Long id,@PathVariable Long ts) {
        return service.changeState_ACPPOINT_to_COLLECTED(id, ts);
    }

    
}
