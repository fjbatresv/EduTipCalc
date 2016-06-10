package edu.galileo.android.tipcalc.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by javie on 1/06/2016.
 */
public class TipRecord {
    private double bill;
    private int tipPercentage;
    private Date timestamp;

    public TipRecord() {
    }

    public TipRecord(double bill, int tipPercentage, Date timestamp) {
        this.bill = bill;
        this.tipPercentage = tipPercentage;
        this.timestamp = timestamp;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getTip(){
        return bill*(tipPercentage/100d);
    }

    public String getDateFormatted(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd | HH:mm");
        return df.format(timestamp);
    }
}
