package com.entity;

/*
 * #%L
 * Gateway
 * %%
 * Copyright (C) 2015 Powered by Sergey
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aautushk on 9/12/2015.
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {
    @Column(name = "created_by_user", nullable = false)
    @CreatedBy
    private String createdByUser;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "modified_by_user", nullable = false)
    @LastModifiedBy
    private String modifiedByUser;

    @Column(name = "modified_at", nullable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;

    @Column(name = "version", nullable = false)
    @javax.persistence.Version
    private Long version;

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
