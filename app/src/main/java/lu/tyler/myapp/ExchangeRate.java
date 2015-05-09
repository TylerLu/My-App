package lu.tyler.myapp;

import java.util.Date;

public class ExchangeRate {
    float value;
    Date date;

    public float getValue(){
        return value;
    }

    public void setValue(float value){
        this.value = value;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
