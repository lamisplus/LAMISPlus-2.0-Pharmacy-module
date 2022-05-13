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
@Table(name = "drug_order")
@Data
@EqualsAndHashCode
public class DrugOrder extends JsonBEntity {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    private String prescriptionGroupId;

    @JsonIgnore
    private String uuid;
    private String drugName;

    //TODO: change to integer
    private String dosageStrengthUnit;
    //private String dosageUnit;
    private String comments;
    private String orderedBy;

    //TODO: change to integer
    private String duration;
    private Long patientId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    private String durationUnit;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime dateTimePrescribed;

    private String brand;
    private Integer dosageFrequency;
    private String type;

    @JsonIgnore
    private Integer archived;

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

    @OneToMany(mappedBy = "drugOrderByDrugOrderId")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<DrugDispense> drugDispensesById;

    @Type(type = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "other_details", columnDefinition = "jsonb")
    private Object otherDetails;

    @Transient
    private Integer status;
}
