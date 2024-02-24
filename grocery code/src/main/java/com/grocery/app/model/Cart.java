package com.grocery.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class Cart {

    @Id
    @UuidGenerator
    private String cartId;


    private String userId;

    @ElementCollection
    private Map<String,Integer> cartItems;


}
