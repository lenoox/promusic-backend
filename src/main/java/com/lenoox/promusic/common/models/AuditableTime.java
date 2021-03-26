package com.lenoox.promusic.common.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Getter()
@Setter()
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableTime {

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    protected Instant createdDate;
    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP")
    protected Instant lastModifiedDate;
}
