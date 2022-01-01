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
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Pattern(regexp = "^[\\pL .,0-9()_:-]{2,50}$", message = "Tên sản phẩm phải chứa từ 2-50 ký tự và không có ký tự đặc biệt")
    @NotBlank(message = "Tên sản phẩm không được trống")
    private String name;

    private String slug;

    @Digits(integer = 12, fraction = 2)
    @Column(name = "price", nullable= false)
    private BigDecimal price = BigDecimal.valueOf(0);

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
    @Column(name = "last_price", nullable= false)
    private BigDecimal lastPrice = BigDecimal.valueOf(0);

    private Integer rewardPoint = 0;

    private Integer views = 0;

    @Lob
    private String description;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ProductImage> productImages;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private BillDetail billDetail;

    @ManyToOne
    @JoinColumn(name = "category_group_id")
    private CategoryGroup categoryGroup;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;
}
