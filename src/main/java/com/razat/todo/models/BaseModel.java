package com.razat.todo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
@Getter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @CreatedDate
    protected Date createdAt;
    @LastModifiedDate
    protected Date lastModifedDate;
    @CreatedBy
    protected String createdBy;
    @LastModifiedBy
    protected String lastModifiedBy;

    protected BaseModel(BaseModel other){
        this.id = other.id;
        this.createdAt = other.createdAt;
        this.createdBy = other.createdBy;
        this.lastModifedDate = other.lastModifedDate;
        this.lastModifiedBy = other.lastModifiedBy;
    }

}
