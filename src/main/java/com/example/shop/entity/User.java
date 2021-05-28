package com.example.shop.entity;


import com.example.shop.entity.parent.BaseEntity;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usr")
public class User extends BaseEntity {

    @Column(unique = true)
    private String name;
    private String firstName;
    private String lastName;

    @Email
    private String email;
    private String password;

    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_status", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> status;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Order> orders;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Cart cart;

    //    ----------------------------------------------------

//    public Set<SimpleGrantedAuthority> getAuthorities() {
//        return roles.stream()
//                .map(item -> new SimpleGrantedAuthority(item.name())).collect(Collectors.toSet());
//    }
}
