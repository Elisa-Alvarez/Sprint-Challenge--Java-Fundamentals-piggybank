package com.labdaschool.javapiggybank.model;


import javax.persistence.*;

@Entity
@Table(name="coins")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long coinid;
    private  String name;
    private String nameplural;
    private Double value;
    private  int quantity;

    public Currency(String name, String nameplural, Double value, int quantity) {
        this.name = name;
        this.nameplural = nameplural;
        this.value = value;
        this.quantity = quantity;
    }

    public Currency() {
    }

    public long getCoinid() {
        return coinid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameplural() {
        return nameplural;
    }

    public void setNameplural(String nameplural) {
        this.nameplural = nameplural;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "coinid=" + coinid +
                ", name='" + name + '\'' +
                ", nameplural='" + nameplural + '\'' +
                ", value=" + value +
                ", quantity=" + quantity +
                '}';
    }
}

