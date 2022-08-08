//package org.lamisplus.modules.pharmacy.domain.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import java.time.LocalDateTime;
//import javax.persistence.Column;
//import javax.persistence.EntityListeners;
//import javax.persistence.MappedSuperclass;
//import org.lamisplus.modules.base.security.SecurityUtils;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//@MappedSuperclass
//@EntityListeners({AuditingEntityListener.class})
//public class Audit<U> {
//    @CreatedBy
//    @Column(
//            name = "created_by",
//            nullable = false,
//            updatable = false
//    )
//    @JsonIgnore
//    private String createdBy = (String)SecurityUtils.getCurrentUserLogin().orElse((null));
//    @CreatedDate
//    @Column(
//            name = "date_created",
//            nullable = false,
//            updatable = false
//    )
//    @JsonIgnore
//    private LocalDateTime dateCreated = LocalDateTime.now();
//    @LastModifiedBy
//    @Column(
//            name = "modified_by"
//    )
//    @JsonIgnore
//    private String modifiedBy = (String)SecurityUtils.getCurrentUserLogin().orElse((null));
//    @LastModifiedDate
//    @Column(
//            name = "date_modified"
//    )
//    @JsonIgnore
//    private LocalDateTime dateModified = LocalDateTime.now();
//
//    public Audit() {
//    }
//
//    public String getCreatedBy() {
//        return this.createdBy;
//    }
//
//    public LocalDateTime getDateCreated() {
//        return this.dateCreated;
//    }
//
//    public String getModifiedBy() {
//        return this.modifiedBy;
//    }
//
//    public LocalDateTime getDateModified() {
//        return this.dateModified;
//    }
//
//    @JsonIgnore
//    public void setCreatedBy(final String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    @JsonIgnore
//    public void setDateCreated(final LocalDateTime dateCreated) {
//        this.dateCreated = dateCreated;
//    }
//
//    @JsonIgnore
//    public void setModifiedBy(final String modifiedBy) {
//        this.modifiedBy = modifiedBy;
//    }
//
//    @JsonIgnore
//    public void setDateModified(final LocalDateTime dateModified) {
//        this.dateModified = dateModified;
//    }
//}
