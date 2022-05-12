package org.lamisplus.modules.pharmacy.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.lamisplus.modules.base.security.SecurityUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "drug_dispense")
@EqualsAndHashCode
@Data
public class DrugDispense extends JsonBEntity {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String drugName;
    private String uuid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime dateTimeDispensed;
    private String comment;
    private String brand;
    private Long quantity;
    private String unit;
    private String dispensedBy;
    private Date startDate;
    private String dosageStrength;
    private String dosageStrengthUnit;
    private Integer dosageFrequency;

    @Basic
    @Column(name = "drug_order_id")
    private Long drugOrderId;

    @JsonIgnore
    private Integer archived;

    private Long patientId;
    private Integer duration;
    private String durationUnit;
    private String type;

    @CreatedBy
    @Basic
    @Column(name = "created_by", updatable = false)
    @JsonIgnore
    private String createdBy = SecurityUtils.getCurrentUserLogin().orElse("");

    @Basic
    @Column(name = "date_created")
    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime dateCreated = LocalDateTime.now();

    @LastModifiedBy
    @Basic
    @Column(name = "modified_by")
    @JsonIgnore
    private String modifiedBy = SecurityUtils.getCurrentUserLogin().orElse("");

    @Basic
    @Column(name = "date_modified")
    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime dateModified = LocalDateTime.now();

    @JsonIgnore
    private Long organisationUnitId;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "drug_order_id", referencedColumnName = "id", updatable = false, insertable = false)
    private DrugOrder drugOrderByDrugOrderId;

    @Type(type = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "other_details", columnDefinition = "jsonb")
    private Object otherDetails;
}
