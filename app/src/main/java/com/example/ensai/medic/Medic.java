package com.example.ensai.medic;

/**
 * Created by ensai on 18/05/17.
 */

public class Medic {
    String name;
    int idMedic;
    String codeCIS;
    //int quantity;
    public Medic(int idMedic,String codeCIS,String name){
        super();
        this.setName(name);
        this.setIdMedic(idMedic);
        this.setCodeCIS(codeCIS);
      //  this.setQuantity(quantity);
    }

    public void setCodeCIS(String codeCIS) {
        this.codeCIS=codeCIS;
    }
    public String getCodeCIS() {
        return codeCIS;
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
