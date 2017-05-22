package com.example.ensai.medic;

/**
 * Created by ensai on 22/05/17.
 */

public class Code {
   private String cis;
    private String cip;
    public Code(String cis,String cip){

    }
    public void setCip(String cip){
        this.cip=cip;
    }
    public void setCis(String cis){
        this.cis=cis;
    }
    public String getCip(){
        return cip;
    }
    public String getCis(){
        return cis;
    }
}
