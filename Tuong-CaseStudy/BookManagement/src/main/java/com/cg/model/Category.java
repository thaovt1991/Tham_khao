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
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên danh mục không được trống")
    @Pattern(regexp = "^[\\pL .,0-9()_:-]{2,50}$", message = "Tên danh mục phải chứa từ 2-50 ký tự và không có ký tự đặc biệt")
    @Column(unique = true)
    private String name;

    private String slug;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<CategoryGroup> categoryGroups;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

}
