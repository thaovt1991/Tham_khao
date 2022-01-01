package com.cg.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Digits(integer = 12, fraction = 2)
    @Column(name = "amount_money", nullable= false)
    private BigDecimal amountMoney;

    @Digits(integer = 12, fraction = 2)
    @Column(name = "discount_amount", nullable= false)
    private BigDecimal discountAmount;

    @Digits(integer = 12, fraction = 2)
    @Column(name = "total_amount", nullable= false)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "bill", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<BillDetail> billDetails;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

}
