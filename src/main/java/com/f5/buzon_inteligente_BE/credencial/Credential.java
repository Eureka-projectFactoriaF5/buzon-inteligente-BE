
package com.f5.buzon_inteligente_BE.credencial;
import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "credentials")
public class Credential implements Serializable { 
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name ="credential_id",nullable = false)
private Long credentialId;

@Column (name = "credential_code", nullable = false, length =  50 ,unique = true)
private String credentialCode;


public Credential() {
}
public Credential(String credentialCode) {
    this.credentialCode = credentialCode;
}
public Long getCredentialId() {
    return credentialId;
}
public String getCredentialCode() {
    return credentialCode;
}





}


