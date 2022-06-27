package org.lamisplus.modules.pharmacy.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeStringType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.lamisplus.modules.base.domain.entities.Audit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
        @TypeDef(name = "jsonb-node", typeClass = JsonNodeBinaryType.class),
        @TypeDef(name = "json-node", typeClass = JsonNodeStringType.class),
})
@Entity
@Table(name = "drug_dispense")
@EqualsAndHashCode(callSuper = false)
@Data
public class DrugDispense extends Audit {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String drugName;

    @Column(name = "uuid", updatable = false)
    @JsonIgnore
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
    @NotNull(message = "drugOrderId is mandatory")
    private Long drugOrderId;

    @JsonIgnore
    private Integer archived;

    @NotNull(message = "patientId is mandatory")
    private Long patientId;
    private Integer duration;
    private String durationUnit;
    private String type;

    @JsonIgnore
    private Long organisationUnitId;

    @OneToOne
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "drug_order_id", referencedColumnName = "id", updatable = false, insertable = false)
    private DrugOrder drugOrderByDrugOrderId;

    @Type(type = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "other_details", columnDefinition = "jsonb")
    private Object otherDetails;

    @PrePersist
    private void setFields(){
        if(StringUtils.isBlank(uuid)){
            uuid = UUID.randomUUID().toString();
        }
        archived = 0;
    }
}
