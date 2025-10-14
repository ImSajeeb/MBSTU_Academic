package practice;

public class Car1 {
    private String color ;
    private int year ;

    public String getColor(){
        return  color;
    }
    public int getYear(){
        return year;
    }

    public void CarStatus(){
        System.out.println("Car Status: Running");
    }

    public Car1(String color,int year){
        this.color = color;
        this.year=year;
    }
}
