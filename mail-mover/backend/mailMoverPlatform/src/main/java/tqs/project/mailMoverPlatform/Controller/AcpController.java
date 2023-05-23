package tqs.project.mailMoverPlatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.LoginInfo;
import tqs.project.mailMoverPlatform.services.AcpServiceImpl;


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
            return new ResponseEntity<>(ret_acp, HttpStatus.CREATED);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ACP>> getAllAcps() {
        List<ACP> allAcps = service.getAllAcps();
        return new ResponseEntity<>(allAcps, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ACP> getAcpById(@PathVariable Long id) {
        Optional<ACP> ret_acp = service.getAcpById(id);
        if (ret_acp.isPresent()) return new ResponseEntity<>(ret_acp.get(), HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginInfo loginInfo) {
        boolean auth = service.performLogin(loginInfo.getEmail(), loginInfo.getPassword());
        if (auth) {
            return new ResponseEntity<Boolean>(auth, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<Boolean>(auth, HttpStatus.OK);
        }
    }
    

}
