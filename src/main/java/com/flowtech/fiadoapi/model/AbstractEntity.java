package com.flowtech.fiadoapi.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

    private OffsetDateTime deletedAt;

    private OffsetDateTime createdAt;

    public void delete(){
        this.deletedAt = OffsetDateTime.now();
    }

    public void create(){
        this.createdAt = OffsetDateTime.now();
    }

}