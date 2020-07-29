package com.sap.mervyn.methodreference.predicate;

public class Apple {
    private String color;
    private int weight;
    private int id;

    public Apple() {
    }

    public Apple(int id, String color, int weight) {
        this.id = id;
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", id=" + id +
                '}';
    }
}
