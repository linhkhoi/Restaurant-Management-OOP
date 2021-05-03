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
public class ThucUong extends MonAn{
    private String hSX;

    public ThucUong(int id, String tenMon, BigDecimal giaMon,String hsx) {
        super(id,tenMon, giaMon);
        this.hSX = hsx;
    }
    
    public ThucUong(int id, String tenMon, BigDecimal giaMon) {
        super(id,tenMon, giaMon);
    }
    
    public ThucUong() {
    }

    
    
    @Override
    public void nhap(Scanner sc)  {
        System.out.println("============");
        sc.nextLine();
        System.out.print("\nNhap ten mon: ");
        this.setTenMon(sc.nextLine());
        System.out.print("Nhap gia mon: ");
        this.setGiaMon(sc.nextBigDecimal()); 
        System.out.print("Nhap hang san xuat: ");
        sc.nextLine();
        this.hSX = sc.nextLine();
        
    }
    
    @Override
    public void hienThi() {
        System.out.println("------------------------------");
        System.out.println("Ma thuc uong: "+this.getIdMonAn());
        System.out.println("Ten thuc uong: "+this.getTenMon());
        System.out.println("Gia thuc uong: "+ this.getGiaMon()+" VND");
        System.out.println("Hang san xuat: "+this.hSX);
    }
    
    
    public String gethSX() {
        return hSX;
    }

    public void sethSX(String hSX) {
        this.hSX = hSX;
    }

    

    
    
}
