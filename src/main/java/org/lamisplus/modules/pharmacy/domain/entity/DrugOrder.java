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
@Table(name = "drug_order")
@Data
@EqualsAndHashCode(callSuper = false)
public class DrugOrder extends Audit {
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
    private String dosageStrength;
    private String comments;
    private String orderedBy;

    //TODO: change to integer
    private String duration;
    private Long patientId;
    private Long visitId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    private String durationUnit;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime dateTimePrescribed;

    private String brand;
    private Integer dosageFrequency;
    private String type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime encounterDateTime;

    @JsonIgnore
    private Integer archived;

    @JsonIgnore
    private Long organisationUnitId;

    @OneToOne(mappedBy = "drugOrderByDrugOrderId")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DrugDispense drugDispensesById;

    @Type(type = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "other_details", columnDefinition = "jsonb")
    private Object otherDetails;

    @Transient
    private Integer status;

    @Transient
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime dateTimeDispensed;

    @PrePersist
    private void setFields(){
        if(StringUtils.isBlank(uuid)){
            uuid = UUID.randomUUID().toString();
        }
        archived = 0;
    }
}
