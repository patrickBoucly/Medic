package com.example.ensai.medic;

/**
 * Created by ensai on 18/05/17.
 */

public class Medic {
    String name;
    int idMedic;
    //int quantity;
    public Medic(String name,int idMedic/*,int quantity*/){
        super();
        this.setName(name);
        this.setIdMedic(idMedic);
      //  this.setQuantity(quantity);
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getIdMedic(){
        return this.idMedic;
    }
    public void setIdMedic(int idMedic){
        this.idMedic=idMedic;
    }


/*
    public int getQuantity(){
        return this.quantity;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
*/
}
