package com.example.funflavour;

public class Flavors {
    int lemon;



    int fruitBeer;
    int strawberry;
    int orange;
    int jeera;
    int cola;
    int blueberry;
    int grapes;
    int litchi;
    int apple;


    String Date;

    public Flavors(String Date, int lemon, int fruitBeer, int strawberry, int orange, int jeera, int cola, int blueberry, int grapes, int litchi, int apple) {
        this.lemon = lemon;
        this.fruitBeer = fruitBeer;
        this.strawberry = strawberry;
        this.orange = orange;
        this.jeera = jeera;
        this.cola = cola;
        this.blueberry = blueberry;
        this.grapes = grapes;
        this.litchi = litchi;
        this.apple = apple;
        this.Date = Date;
    }
    public int getLemon() {
        return lemon;
    }

    public int getFruitBeer() {
        return fruitBeer;
    }

    public int getStrawberry() {
        return strawberry;
    }

    public int getOrange() {
        return orange;
    }

    public int getJeera() {
        return jeera;
    }

    public int getCola() {
        return cola;
    }

    public int getBlueberry() {
        return blueberry;
    }

    public int getGrapes() {
        return grapes;
    }

    public int getLitchi() {
        return litchi;
    }

    public int getApple() {
        return apple;
    }

    public String getDate() {
        return Date;
    }
}
