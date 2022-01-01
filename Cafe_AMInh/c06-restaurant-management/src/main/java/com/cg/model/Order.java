package com.cg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "desk_id", referencedColumnName = "id", nullable = false)
    private Desk desk;

    @JsonIgnore
    @OneToMany (targetEntity = OrderDetail.class, fetch = FetchType.EAGER)
    private Set<OrderDetail> orderDetails;
}
