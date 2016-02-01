package com.example.larin.aolin1_fueltrack;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by Larin on 2016/1/31.
 */
public class Entrylist {
    private String date;
    private String station;
    private String odometer;
    private String grade;
    private Float amount;
    private Float cost;
    private Float total_cost;
    public Entrylist(String date, String station, String odometer,
            String grade, Float amount, Float cost){
        this.date = date;
        this.station = station;
        this.odometer = odometer;
        this.amount = amount;
        this.grade = grade;
        this.cost = cost;
        this.total_cost = cost * amount/100;
    }
    public String getDate() {
        return date;
    }
    public String setDate() {
        return date;
    }
    public String getOdometer() {
        return odometer;
    }
    public String setOdometer() {
        return odometer;
    }
    public String getStation() {
        return station;
    }
    public String  setStation() {
        return station;
    }
    public String  getGrade() {
        return grade;
    }
    public String setGrade() {
        return grade;
    }

    public Float getAmount() {
        return amount;
    }
    public Float setAmount() {
        return amount;
    }

    public Float getCost() {
        return cost;
    }
    public Float setCost() {
        return cost;
    }

    public String toString(){
        DecimalFormat df = new DecimalFormat("#.00");
        return date.toString() + "  " +station + "   $"+ df.format(total_cost)+"\nOdometer: "+odometer+" km"+" Fuel Grade: "+grade+" \nFuel Amount: "+amount+" Unit Cost: "+cost;
    }

}
