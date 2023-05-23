package tqs.project.mailMoverPlatform.services;

import tqs.project.mailMoverPlatform.entities.Admin;

public interface AdminService {
    public Admin addAdmin(Admin admin);
    public boolean performLogin(String email, String password);
}
