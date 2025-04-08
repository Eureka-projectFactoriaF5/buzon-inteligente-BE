package com.f5.buzon_inteligente_BE.model;

public class User {
 
    public Long userId;
    public String userDni;
    public String userName;
    public String userEmail;
    public String userPasword;

    public Long credentialId;
   
  public Long lockerId;
    
       public User(Long userId, String userDni, String userName, String userEmail, String userPasword, Long credentialId,
            Long lockerId) {
        this.userId = userId;
        this.userDni = userDni;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPasword = userPasword;
        this.credentialId = credentialId;
        this.lockerId = lockerId;
    }
    
    public Long getUserId(){
        return userId;
    }
    public String getUserDni(){
        return userDni;
    }
    public String getUserName(){
        return userName;
    }
    public String getUserEmail(){
        return userEmail;
    }
    public String getUserPasword(){
        return userPasword;
    }
  public Long getCredentialId() {
        return credentialId;
    }
public Long getLockerId() {
        return lockerId;
    }

}
