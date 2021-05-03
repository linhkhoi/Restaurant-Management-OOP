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
public abstract class MonAn implements NhapXuat{
    private int idMonAn;
    private String tenMon;
    private BigDecimal giaMon;

    public MonAn(int id, String tenMon, BigDecimal giaMon) {
        this.idMonAn = id;
        this.tenMon = tenMon;
        this.giaMon = giaMon;
    }
    

    public MonAn() {
    }

    @Override
    public String toString() {
        return String.format("%2d. %-30s %,.0f VND\n", 
                this.getIdMonAn(),this.getTenMon(),
              this.getGiaMon());
    }
    
    
    
    public int getIdMonAn() {
        return idMonAn;
    }
        
    
    
    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public BigDecimal getGiaMon() {
        return giaMon;
    }

    public void setGiaMon(BigDecimal giaMon) {
        this.giaMon = giaMon;
    }

    
    @Override
    public abstract void hienThi();
    @Override
    public abstract void nhap(Scanner sc);
}
