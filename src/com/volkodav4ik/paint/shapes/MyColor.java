package com.volkodav4ik.paint.shapes;

public enum MyColor {
    BLACK("#000"), RED("#F00"), BLUE("#00F"), GREEN("#0F0"), VIOLET("#640066");

    private String color;

    MyColor(String color) {
        this.color = color;
    }

    public String toHex() {
        return color;
    }
}
