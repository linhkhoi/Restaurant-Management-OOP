/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Karaoke extends DichVu{
    private Time tG;

    public Karaoke(int id,String tenDV, BigDecimal giaDV, Time tg) {
        super(id,tenDV, giaDV);
        this.tG = tg;
    }

    public Karaoke() {
    }
    
    public Karaoke(int id,String tenDV, BigDecimal giaDV) {
        super(id,tenDV, giaDV);
    }

    @Override
    public void nhap(Scanner sc) {
        sc.nextLine();
        System.out.println("Nhap ten karaoke");
        this.setTenDV(sc.nextLine());
        System.out.println("Nhap gia karaoke");
        this.setGiaDV(sc.nextBigDecimal());
        int gio;
        System.out.println("Nhap so gio karaoke: ");
        gio= sc.nextInt();
        this.tG = new Time(gio, 0,0);
    }
    
    @Override
    public void hienThi() {
        System.out.println("======================");
        System.out.println("Ma dich vu karaoke: "+this.getMaDV());
        System.out.println("Ten dich vu karaoke: "+this.getTenDV());
        System.out.println("Gia dich vu karaoke: "+ this.getGiaDV());
        System.out.println("Thoi gian karaoke: "+ this.tG);
    }

    public Time gettG() {
        return tG;
    }

    public void settG(Time tG) {
        this.tG = tG;
    }
    
    
}
