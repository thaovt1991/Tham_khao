package com.cg.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category_groups")
public class CategoryGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Pattern(regexp = "^[\\pL .,0-9()_:-]{2,50}$", message = "Tên nhóm danh mục phải chứa từ 2-50 ký tự và không có ký tự đặc biệt")
    @NotBlank(message = "Tên nhóm danh mục không được trống")
    private String name;

    private String slug;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "categoryGroup", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Product> products;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;
}
