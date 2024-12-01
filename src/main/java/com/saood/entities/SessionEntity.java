package com.saood.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "session")
public class SessionEntity  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_id", nullable = false, unique = true, length = 50)
    private String sessionId;

    @Column(name = "logout_time", nullable = true)
    private String logoutTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_role", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    public SessionEntity(String registrationId, String logoutTime, UserEntity user) {
        this.sessionId = registrationId;
        this.logoutTime = logoutTime;
        this.user = user;
    }

    public SessionEntity() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
