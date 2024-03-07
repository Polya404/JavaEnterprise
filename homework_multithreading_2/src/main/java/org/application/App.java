package org.application;

public class App {
    public static void main(String[] args) {
        Bar bar = new Bar(2,3);
        bar.acceptOrder();
        bar.preparationDrinks();
        bar.delivery();
    }
}
