package tqs.project.mailMoverPlatform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.LoginInfo;
import tqs.project.mailMoverPlatform.services.AcpServiceImpl;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("v1/acp")
public class AcpController {
    
    @Autowired
    private AcpServiceImpl service;


    @PostMapping("/new")
    public ResponseEntity<ACP> createACP(@RequestBody ACP newAcp) {
        ACP ret_acp = service.addACP(newAcp);
        if (ret_acp == null) {
            return new ResponseEntity<ACP>(ret_acp, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<ACP>(ret_acp, HttpStatus.CREATED);
        }
    }

    @GetMapping("/all")
    public ArrayList<HashMap<String,String>> getAllAcps() {
        List<ACP> allAcps = service.getAllAcps();
        ArrayList<HashMap<String,String>> ret_list = new ArrayList<>();

        for (ACP acp : allAcps){
            HashMap<String,String> acpInfo = new HashMap<>();
            acpInfo.put("id",acp.getId().toString());
            acpInfo.put("name",acp.getName());
            acpInfo.put("address",acp.getAddress());
            acpInfo.put("email",acp.getEmail());
            ret_list.add(acpInfo);
        }

        return ret_list;
    }

    @GetMapping("{id}")
    public HashMap<String,String> getAcpById(@PathVariable Long id) {
        Optional<ACP> ret_acp = service.getAcpById(id);
        if (ret_acp.isPresent()) {
            ACP acp = ret_acp.get();
            HashMap<String,String> returnDic = new HashMap<>();
            returnDic.put("id",acp.getId().toString());
            returnDic.put("name",acp.getName());
            returnDic.put("address",acp.getAddress());
            returnDic.put("email",acp.getEmail());
            return returnDic;
        } else{
            return null;
        } 
    }

    @PostMapping("/login")
    public Boolean login(@RequestBody LoginInfo loginInfo) {
        Boolean auth = service.performLogin(loginInfo.getEmail(), loginInfo.getPassword());
        return auth;
    }
}
