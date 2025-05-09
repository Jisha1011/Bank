package com.bank.accounts.entity;

import com.bank.accounts.audit.AuditAwareImpl;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class BaseEntity {
    @Column(updatable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDate updatedAt;

    @Column(insertable = false)
    @LastModifiedBy
    private String updatedBy;
}
