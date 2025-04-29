package com.f5.buzon_inteligente_BE.roles;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "role_id",  nullable = false)
    private Long roleId;

    @Column(name= "role_name", nullable= false, length = 50)
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
    
    public Role() {
    }
    
    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
