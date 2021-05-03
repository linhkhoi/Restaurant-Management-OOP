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
public class ThucAn extends MonAn{
    private boolean anChay;
    
    public ThucAn() {
    }
    
    
    
    public ThucAn(int id, String tenMon, BigDecimal giaMon,boolean anChay) {
        super(id, tenMon, giaMon);
        this.anChay=anChay;
    }

    public ThucAn(int id, String tenMon, BigDecimal giaMon) {
        super(id, tenMon, giaMon);
    }

    @Override
    public void nhap(Scanner sc) {
        System.out.println("============");
        sc.nextLine();
        System.out.print("\nNhap ten mon: ");
        this.setTenMon(sc.nextLine());
        System.out.print("Nhap gia mon: ");
        this.setGiaMon(sc.nextBigDecimal()); 
        int chon;
        System.out.print("Mon nay an chay duoc khong(1 - co, 2 - khong): ");
        chon= sc.nextInt();
        this.anChay = chon==1;
    }
    
    @Override
    public void hienThi() {
        System.out.println("------------------------------");
        System.out.println("Ma thuc an: "+this.getIdMonAn());
        System.out.println("Ten thuc an: "+this.getTenMon());
        System.out.println("Gia thuc an: "+ this.getGiaMon()+" VND");
        if(isAnChay()) {
            System.out.println("Mon nay an chay duoc");
        } else {
            System.out.println("Mon nay khong an chay duoc");
        }
    }

    
    

    public boolean isAnChay() {
        return anChay;
    }

    public void setAnChay(boolean anChay) {
        this.anChay = anChay;
    }

    
    
    
    
}
