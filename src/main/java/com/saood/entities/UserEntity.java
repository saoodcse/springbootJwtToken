package com.saood.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_role", referencedColumnName = "id", nullable = false)
    private RoleEntity role;

    // Getters and setters

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public UserEntity() {
    }

    public UserEntity(Long id, RoleEntity role, String password, String username) {
        this.id = id;
        this.role = role;
        this.password = password;
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
