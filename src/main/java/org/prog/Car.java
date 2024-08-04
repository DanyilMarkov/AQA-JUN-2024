package org.prog;

public class Car implements ITransport {

    private String color;
    public int milage = 0;

    public void goTo(int distance) {
        milage += distance;
        goTo("somewhere");
    }
    public void goTo() {
        milage += 10;
        goTo("somewhere");
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Car car = (Car) obj;
        return milage == car.milage &&
                Objects.equals(color, car.color);
    }
    public int hashCode() {
        return Objects.hash(color, milage);
    }


    public void goTo(String destination) {
        goTo("current city", destination);
    }

    public void goTo(String from, String destination) {
        goTo(from, destination, "nowhere");
    }

    public void goTo(String from, String destination, String passingThrough) {
        System.out.println("Car is going from " + from + " to "
                + destination + " stopping at " + passingThrough);
    }

    public void turn(String direction) {
        System.out.println(color + " car turns " + direction);
    }

    public void setColor(String newColor) {
        if (newColor != null) {
            color = newColor;
        }
    }

    public String getColor() {
        return color;
    }
    public int getMilage() {
        return milage;
    }
}
