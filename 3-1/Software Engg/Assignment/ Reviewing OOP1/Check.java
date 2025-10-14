import practice.Car;
import practice.Car1;


public class Check {
    public static void main(String[] args) {
        System.out.println("\nCar object set with setter");
        Car cc = new Car();
        cc.setColor("Red");
        cc.setYear(2023);
        System.out.println("Color "+cc.getColor());
        System.out.println("Year " +cc.getYear());
        cc.carStatus();

        System.out.println("\nCar1 object set with cons");
        Car1 cc1 = new Car1("Blue",2025);
        System.out.println("Color: "+cc1.getColor());
        System.out.println("Year: "+cc1.getYear());



    }
}
