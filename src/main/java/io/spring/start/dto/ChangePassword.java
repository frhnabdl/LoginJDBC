package io.spring.start.dto;

import org.springframework.stereotype.Service;

@Service
public class ChangePassword {
    private String newPassword;
    private String oldPassword;
    
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getOldPassword() {
        return oldPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
