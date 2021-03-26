package com.lenoox.promusic.common.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

@Getter()
@Setter()
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable extends AuditableTime {
    @CreatedBy
    @Column(name = "created_by")
    protected Long createdBy;
    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected Long lastModifiedBy;
}
