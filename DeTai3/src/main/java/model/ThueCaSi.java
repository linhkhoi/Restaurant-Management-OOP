/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class ThueCaSi extends DichVu{
    private String tenCaSi;
    private int soLuongBai;

    public ThueCaSi(int id,String tenDV, BigDecimal giaDV, String ten, int sl) {
        super(id,tenDV, giaDV);
        this.tenCaSi=ten;
        this.soLuongBai=sl;
    }
    
    public ThueCaSi(int id,String tenDV, BigDecimal giaDV) {
        super(id,tenDV, giaDV);
    }
    
    public ThueCaSi() {
    }
    
    
    
    @Override
    public void nhap(Scanner sc) {
        sc.nextLine();
        System.out.println("Nhap ten dich vu thue ca si");
        this.setTenDV(sc.nextLine());
        System.out.println("Nhap gia thue ca si:");
        this.setGiaDV(sc.nextBigDecimal());
        sc.nextLine();
        System.out.println("Nhap ten ca si: ");
        this.tenCaSi = sc.nextLine();
        System.out.println("Nhap so luong bai: ");
        this.soLuongBai = sc.nextInt();
    }

    @Override
    public void hienThi() {
        System.out.println("===================");
        System.out.println("Ma dich vu thue ca si: "+this.getMaDV());
        System.out.println("Ten dich vu: "+this.getTenDV());
        System.out.println("Gia dich vu: "+ this.getGiaDV());
        System.out.println("Ten ca si: "+ this.tenCaSi);
        System.out.println("So luong bai: "+this.soLuongBai);
    }

   

    public String getTenCaSi() {
        return tenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        this.tenCaSi = tenCaSi;
    }

    public int getSoLuongBai() {
        return soLuongBai;
    }

    public void setSoLuongBai(int soLuongBai) {
        this.soLuongBai = soLuongBai;
    }
    
}
