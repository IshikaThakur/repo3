package com.ecommerceApp.ecommerceApp.security;

import com.ecommerceApp.ecommerceApp.entities.Users;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String authority;
    private boolean isDeleted = false;
    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;

    public Role(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }
public Role(){

}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                ", isDeleted=" + isDeleted +
                ", users=" + users +
                '}';
    }
}