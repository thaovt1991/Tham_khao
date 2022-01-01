package com.cg.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bill_details")
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên sản phẩm không được trống")
    @Column(name = "product_name", nullable= false)
    private String productName;

    @Digits(integer = 12, fraction = 2)
    @Column(name = "price", nullable= false)
    private BigDecimal price = BigDecimal.valueOf(0);

    private int quantity;

    @Digits(integer = 3, fraction = 0)
    @Min(value = 0, message = "Giảm giá không được âm")
    @Max(value = 100, message = "Giảm giá không được quá 100%")
    @Column(name = "percentage_discount", nullable= false)
    private BigDecimal percentageDiscount = BigDecimal.valueOf(0);

    @Digits(integer = 12, fraction = 2)
    @Min(value = 0, message = "Giảm giá không được âm")
    @Column(name = "discount_amount", nullable= false)
    private BigDecimal discountAmount = BigDecimal.valueOf(0);

    @Digits(integer = 12, fraction = 2)
    @Column(name = "price_to_pay", nullable= false)
    private BigDecimal priceToPay = BigDecimal.valueOf(0);

    @OneToMany(mappedBy = "billDetail", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;
}
