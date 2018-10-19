package com.example.funflavour;
import java.util.*;


/*
In recycler view the variables declared should be identical to the fields in firestore document
 */

public class Record {


    long high_vol;
    long low_vol;
    long total_amount;
    long mango;
    Date time;

    public Record(){

    }
    public Record(long high_vol, long low_vol, long total_amount, long mango, Date time) {
        this.high_vol = high_vol;
        this.low_vol = low_vol;
        this.total_amount = total_amount;
        this.mango = mango;
        this.time = time;
    }

    public long getHigh_vol() {
        return high_vol;
    }

    public void setHigh_vol(long high_vol) {
        this.high_vol = high_vol;
    }

    public long getLow_vol() {
        return low_vol;
    }

    public void setLow_vol(long low_vol) {
        this.low_vol = low_vol;
    }

    public long getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(long total_amount) {
        this.total_amount = total_amount;
    }

    public long getMango() {
        return mango;
    }

    public void setMango(long mango) {
        this.mango = mango;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
