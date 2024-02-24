package com.grocery.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table (name = "item")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class Item {

    @Id
    @UuidGenerator
    private String itemId;

    private String itemName;
    private double itemPrice;

    //count of item available in grocery
    private int itemLevel;
    private String itemDesc;

}
