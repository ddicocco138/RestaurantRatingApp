package com.example.dicoccohw3;

public class restaurant {

    private int Rating;
    private String Name;
    private String Location;


    public int getRating() {
        return Rating;
    }
    public String getName() {return Name;}
    public String getLocation() {return Location;}

    public restaurant(String Name, String Location, int Rating)
    {
        this.Name = Name;
        this.Location = Location;
        this.Rating = Rating;

    }

    public void setLocation(String Location) {
        this.Location = Location;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public void setRating(int Rating) {
        this.Rating = Rating;
    }

}
