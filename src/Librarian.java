/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class Librarian {
    public String maso;
    public String ten;
    public String calam;
    
    public Librarian (String maso, String ten, String calam){
        this.maso=maso;
        this.ten= ten;
        this.calam = calam;
    }

    public String getTen() {
        return ten;
    }

    public String getMaso() {
        return maso;
    }

    public String getCalam() {
        return calam;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setCalam(String calam) {
        this.calam = calam;
    }

    @Override
    public String toString() {
        return "Mathuvien:"+ maso+",Ten: "+ten+",Calamviec: "+calam;
    }
    
   
 
}
