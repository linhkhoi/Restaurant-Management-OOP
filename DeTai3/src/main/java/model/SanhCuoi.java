/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class SanhCuoi implements NhapXuat{
    private String maSanh;
    private String tenSanh;
    private int viTri;
    private int sucChua;
    private EnumMap<ThoiDiem, BigDecimal> dsGia;

    public SanhCuoi(String maSanh, String tenSanh, int viTri, int sucChua) {
        this.dsGia = new EnumMap<>(ThoiDiem.class);
        this.maSanh = maSanh;
        this.tenSanh = tenSanh;
        this.viTri = viTri;
        this.sucChua = sucChua;
    }
    
    public SanhCuoi(){
        this.dsGia = new EnumMap<>(ThoiDiem.class);
    }
    
    @Override
    public void nhap(Scanner sc){
        System.out.println("==================");
        sc.nextLine();
        System.out.println("Nhap ten sanh: ");
        this.tenSanh = sc.nextLine();
        System.out.println("Nhap vi tri: ");
        this.viTri= sc.nextInt();
        System.out.println("Nhap suc chua: ");
        this.sucChua = sc.nextInt();
        for(ThoiDiem t : ThoiDiem.values()){
            System.out.print("\nNhap gia "+t.toString()+": ");
            BigDecimal gia = sc.nextBigDecimal();
            this.dsGia.put(t, gia);
        }
    }

    public EnumMap<ThoiDiem, BigDecimal> getDsGia() {
        return dsGia;
    }

    public void setDsGia(EnumMap<ThoiDiem, BigDecimal> enumMap) {
        this.dsGia = enumMap;
    }
    
    @Override
    public void hienThi() {
        System.out.println("======================");
        System.out.println("Ma sanh: "+this.maSanh);
        System.out.println("Ten sanh: "+this.tenSanh);
        System.out.println("Vi tri: tang "+ this.viTri);
        System.out.println("Suc chua: "+ this.sucChua+" ban");
        for(ThoiDiem t : ThoiDiem.values()){
            System.out.printf("Gia %s: %,.0f VND\n",t.toString(),this.dsGia.get(t));
        
        }
    }
    
  
    public String getTenSanh() {
        return tenSanh;
    }

    public String getMaSanh() {
        return maSanh;
    }
    

    public void setTenSanh(String tenSanh) {
        this.tenSanh = tenSanh;
    }

    public int getViTri() {
        return viTri;
    }

    public void setViTri(int viTri) {
        this.viTri = viTri;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }


    
    

}
