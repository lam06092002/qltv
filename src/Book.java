/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class Book {
    private String masach;
    private String ten;
    private String tacgia;
    private String theloai;
    private int soluong;
    
    public Book(String masach, String ten, String tacgia, String theloai, int soluong){
        this.masach= masach;
        this.ten= ten;
        this.tacgia= tacgia;
        this.theloai= theloai;
        this.soluong= soluong;
    }
    public String getmasach(){
        return masach;
    }
    public String getten(){
        return ten;
    }
    public String gettacgia(){
        return tacgia;
    }
    public String gettheloai(){
        return theloai;
    }
    public int getsoluong(){
        return soluong;
    }    
    public void setTen(String ten){
        this.ten=ten;
    }
    public void setTacgia(String tacgia){
        this.tacgia=tacgia;
    }
    public void setTheloai(String theloai){
        this.theloai=theloai;
    }
    public void setSoluong(int soluong){
        this.soluong=soluong;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    @Override
    public String toString() {
       return "masach: "+ masach+ ",ten: "+ ten+ ",tacgia: "+tacgia+",theloai: "+theloai + ",Soluong: "+soluong;
    }
    
}
