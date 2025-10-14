package practice;

public class Car {
    private String color;
    private int year;

    public String getColor(){
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year){
        this.year = year;
    }

    public void carStatus(){
        System.out.println("Car Status: Running");
    }

}

