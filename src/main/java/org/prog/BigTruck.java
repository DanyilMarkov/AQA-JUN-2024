package org.prog;

public class BigTruck extends Truck {

    @Override
    public void turn(String direction) {
        System.out.println("Long car turns " + direction);
    }
    public void stopAt() { System.out.println(" Truck stop somewhere");}
}
