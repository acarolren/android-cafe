package com.example.cafe.homemade;

import java.io.Serializable;

public class HomemadeExp implements Serializable {
    private Integer id;
    private String coffeebean;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setCoffeebean(String coffeebean) {
        this.coffeebean = coffeebean;
    }

    public Integer getId() {
        return id;
    }

    public String getCoffeebean() {
        return coffeebean;
    }


    @Override
    public String toString(){
        return coffeebean;
    }

}