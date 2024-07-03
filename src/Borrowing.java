
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class Borrowing {
    private String masach;
    private String mathanhvien;
    private LocalDate ngaymuon;
    private LocalDate ngaytra;
    
    public Borrowing (String masach,String mathanhvien, LocalDate ngaymuon, LocalDate ngaytra){
        this.masach= masach;
        this.mathanhvien= mathanhvien;
        this.ngaymuon=ngaymuon;
        this.ngaytra= ngaytra;
    }


    public String getMasach() {
        return masach;
    }

    public String getMathanhvien() {
        return mathanhvien;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public void setMathanhvien(String mathanhvien) {
        this.mathanhvien = mathanhvien;
    }


    public LocalDate getNgaymuon() {
        return ngaymuon;
    }

    public LocalDate getNgaytra() {
        return ngaytra;
    }
   

    public void setNgaytra(LocalDate ngaytra) {
        this.ngaytra = ngaytra;
    }


    public void setNgaymuon(LocalDate ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    @Override
    public String toString() {
        return "Mathanhvien: "+ mathanhvien+",MaSach: "+masach+",Ngaymuon: "+ngaymuon+",NgayTra: "+ngaytra;
    }
    
    
}
