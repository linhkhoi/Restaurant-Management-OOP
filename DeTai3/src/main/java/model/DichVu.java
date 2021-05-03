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
public abstract class DichVu implements NhapXuat{
    private int maDV;
    private String tenDV;
    private BigDecimal giaDV;
    

    public DichVu() {
    }
    
    
    
    public DichVu(int id,String tenDV, BigDecimal giaDV) {
        this.maDV = id;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
    }
    
    @Override
    public abstract void hienThi();
    @Override
    public abstract void nhap(Scanner sc);

    @Override
    public String toString() {
        return String.format("%2d. %-30s %,.0f VND\n", 
                this.getMaDV(),this.getTenDV(),
              this.getGiaDV());
    }
    
    
    
    public int getMaDV() {
        return maDV;
    }
    
    

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public BigDecimal getGiaDV() {
        return giaDV;
    }

    public void setGiaDV(BigDecimal giaDV) {
        this.giaDV = giaDV;
    }

    
    
}
