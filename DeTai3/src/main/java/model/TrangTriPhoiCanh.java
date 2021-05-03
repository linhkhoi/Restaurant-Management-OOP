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
public class TrangTriPhoiCanh extends DichVu{

    public TrangTriPhoiCanh(int id,String tenDV, BigDecimal giaDV) {
        super(id,tenDV, giaDV);
    }

    public TrangTriPhoiCanh() {
    }
    
    
    
    @Override
    public void hienThi() {
        System.out.println("===================");
        System.out.println("Ma dich vu trang tri: "+this.getMaDV());
        System.out.println("Ten dich vu: "+this.getTenDV());
        System.out.println("Gia dich vu: "+ this.getGiaDV());
    }

    @Override
    public void nhap(Scanner sc) {
        sc.nextLine();
        System.out.println("Nhap ten trang tri: ");
        this.setTenDV(sc.nextLine());
        System.out.println("Nhap gia trang tri phoi canh:");
        this.setGiaDV(sc.nextBigDecimal());
    }
    
}
