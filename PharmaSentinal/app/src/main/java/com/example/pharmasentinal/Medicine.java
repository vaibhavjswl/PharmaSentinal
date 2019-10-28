package com.example.pharmasentinal;

import java.util.Comparator;
import java.util.Date;

public class Medicine implements Comparator<Medicine> {
    String medName;
    Date medExp;
    int medQty;

    Medicine(){

    }

    Medicine(Date d , String name , int quantity){
        this.medName = name ;
        this.medExp = d ;
        this.medQty = quantity ;
    }

    public String getMedName(){
        return this.medName ;
    }

    public Date getMedExp(){
        return this.medExp ;
    }

    public int getMedQty(){
        return  this.medQty ;
    }

    public String toString(){


        return ("Medicine Name :"+  this.medName + "\nQuantity :" + this.medQty + " \n"+
                "Expiry Date : " + this.medExp.getDate() +"-"+this.medExp.getMonth()
                +"-"+this.medExp.getYear()) ;
    }

    public int compare(Medicine a , Medicine b){

        if(b.medExp.getYear() - a.medExp.getYear() > 0)
            return -1 ;
        else {
            if (b.medExp.getMonth() - a.medExp.getMonth() > 0)
                return -1;

            else {
                if(b.medExp.getDate() - a.medExp.getDate() > 0)
                    return -1 ;

                else
                    return 0 ;
            }
        }
        }
    }

