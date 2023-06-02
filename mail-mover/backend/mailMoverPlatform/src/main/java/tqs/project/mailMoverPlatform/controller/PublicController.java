package tqs.project.mailMoverPlatform.controller;

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
import tqs.project.mailMoverPlatform.services.AcpServiceImpl;
import tqs.project.mailMoverPlatform.services.OrderServiceImpl;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("v1/mailMover")
public class PublicController {
    @Autowired
    private OrderServiceImpl service;
    @Autowired
    private AcpServiceImpl acp_service;

    @CrossOrigin(origins="*")
    @PostMapping("/new/{clientName}/{acp_id}")
    public HashMap<String,Object> createOrder(@PathVariable String clientName, @PathVariable Long acp_id) {
        Optional<ACP> ret_acp_public = acp_service.getAcpById(acp_id);
        if (ret_acp_public.isPresent()) {
            ACP acp = ret_acp_public.get();
            Order _order_public = new Order(clientName, acp);
            Order order = service.addOrder(_order_public);
            HashMap<String,Object> ret_public = new HashMap<>();
            ret_public.put("id",order.getId());
            ret_public.put("clientName",order.getclientName());
            ret_public.put("status",order.getStatus());
            ret_public.put("storePickUpDate",order.getStorePickUpDate());
            ret_public.put("acpDeliveryDate",order.getAcpDeliveryDate());
            ret_public.put("clientPickUpDate",order.getClientPickUpDate());
            HashMap<String,Object> retACP_public = new HashMap<>();
            retACP_public.put("id",acp.getId());
            retACP_public.put("name",acp.getName());
            retACP_public.put("address",acp.getAddress());
            retACP_public.put("email",acp.getEmail());
            ret_public.put("acp",retACP_public);
            return ret_public;
        } else{
            HashMap<String,Object> ret_public = new HashMap<>();
            ret_public.put("error","acp doesn't exist");
            return ret_public;
        } 
    }

    @CrossOrigin(origins="*")
    @GetMapping("/byAcp/{acp_id}")
    public ArrayList<HashMap<String,Object>> getByAcpId(@PathVariable Long acp_id) {
        Optional<ACP> ret_acp_public = acp_service.getAcpById(acp_id);
        if (ret_acp_public.isPresent()) {
            ACP acp = ret_acp_public.get();
            List<Order> allOrders = service.getByAcp(acp);
            ArrayList<HashMap<String,Object>> ret_list_public = new ArrayList<>();
            for (Order order : allOrders){
                HashMap<String,Object> ret = create_hash_order_public(order);
                ret_list_public.add(ret);
            }
            return ret_list_public;
        } else{
            ArrayList<HashMap<String,Object>> ret_list_public = new ArrayList<>();
            return ret_list_public;
        } 
    }

    @GetMapping("/byId/{id}")
    public HashMap<String,Object> getById(@PathVariable Long id) {
        Order order = service.getById(id);
        HashMap<String,Object> ret_public = new HashMap<>();
        if (order != null) {
            ret_public = create_hash_order_public(order);
        } else {
            ret_public.put("error", "doesn't exist");
        }
       
        return ret_public;
    }
    @GetMapping("/storeToCourier/{id}/{ts}")
    public boolean changeState_STORE_to_COURIER_PUBLIC(@PathVariable Long id,@PathVariable Long ts) {
        return service.changeState_STORE_to_COURIER(id, ts);
    }
    @CrossOrigin(origins="*")
    @GetMapping("/all")
    public ArrayList<HashMap<String,String>> getAllAcps() {
        List<ACP> allAcps_public = acp_service.getAllAcps();
        ArrayList<HashMap<String,String>> ret_list_public = new ArrayList<>();

        for (ACP acp : allAcps_public){
            HashMap<String,String> acpInfo = new HashMap<>();
            acpInfo.put("id",acp.getId().toString());
            acpInfo.put("name",acp.getName());
            acpInfo.put("address",acp.getAddress());
            acpInfo.put("email",acp.getEmail());
            ret_list_public.add(acpInfo);
        }

        return ret_list_public;
    }

    public HashMap<String,Object> create_hash_order_public(Order order){
        HashMap<String,Object> ret_public = new HashMap<>();
        ret_public.put("id",order.getId());
        ret_public.put("clientName",order.getclientName());
        ret_public.put("status",order.getStatus());
        ret_public.put("storePickUpDate",order.getStorePickUpDate());
        ret_public.put("acpDeliveryDate",order.getAcpDeliveryDate());
        ret_public.put("clientPickUpDate",order.getClientPickUpDate());
        HashMap<String,Object> retACP_public = new HashMap<>();
        retACP_public.put("id",order.getAcp().getId());
        retACP_public.put("name",order.getAcp().getName());
        retACP_public.put("address",order.getAcp().getAddress());
        retACP_public.put("email",order.getAcp().getEmail());
        ret_public.put("acp",retACP_public);
        return ret_public;
    }

}
