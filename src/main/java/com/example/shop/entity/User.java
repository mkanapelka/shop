package com.example.shop.entity;

import com.example.shop.entity.model.Role;
import com.example.shop.entity.parent.NameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "usr")
public class User extends NameEntity {


    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;

    @Email
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "fk_user_to_role"))
    private List<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Cart cart;
}
