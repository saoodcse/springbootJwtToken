package com.saood.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_at",nullable = false, updatable = false, length = 255)
    private LocalDateTime createdAt;

    @Column(name = "created_by",nullable = true, updatable = false, length = 255)
    private String createdBy;

    @Column(name = "updated_at",nullable = true, updatable = false, length = 255)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by",nullable = true, updatable = false, length = 255)
    private String updatedBy;


    // Getters and setters
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    // PrePersist - Executed before inserting a new entity
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // PreUpdate - Executed before updating an existing entity
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

