package com.example.foodsaviour;

public class DonationsHelperClass {

    String food, amount, area;

    public DonationsHelperClass() {
    }

    public DonationsHelperClass(String food, String amount, String area) {
        this.food = food;
        this.amount = amount;
        this.area = area;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
