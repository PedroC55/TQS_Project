package tqs.project.mailMoverPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.project.mailMoverPlatform.entities.Admin;
import tqs.project.mailMoverPlatform.entities.LoginInfo;
import tqs.project.mailMoverPlatform.services.AdminServiceImpl;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("v1/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl service;

    @PostMapping("/new")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin newAdmin) {
        Admin ret_admin = service.addAdmin(newAdmin);
        if (ret_admin == null) {
            return new ResponseEntity<Admin>(ret_admin, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<Admin>(ret_admin, HttpStatus.CREATED);
        }
    }
    
    @PostMapping("/login")
    public Boolean login(@RequestBody LoginInfo loginInfo) {
        boolean auth = service.performLogin(loginInfo.getEmail(), loginInfo.getPassword());
        return auth;
    }

}
