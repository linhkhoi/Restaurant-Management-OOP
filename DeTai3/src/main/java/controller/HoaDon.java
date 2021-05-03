/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ThoiDiem;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class HoaDon {
    
    public void nhapHD(Scanner sc, Connection conn) throws SQLException, ParseException{
        QLSanh qlS = new QLSanh();
        try {
            int id = DBGetId.getLastIdHD(conn);
            //nhap ten buoi tiec
            sc.nextLine();
            System.out.print("Nhap ten buoi tiec: ");
            String tenTiec = sc.nextLine();
            
            //nhap ngay to chuc
            System.out.print("Nhap ngay to chuc: ");
            String date = sc.next();
            Date ngayTC = Format.nhapNgay(date);
            
            //nhap sanh
            System.out.println("Ban muon tim sanh thue theo: ");
            System.out.println("1. Ten");
            System.out.println("2. So ban");
            System.out.print("Chon: ");
            int choose = sc.nextInt();
            if(choose == 1){
                qlS.traCuu(conn).forEach(p->p.hienThi());
            }else{
                System.out.print("\nNhap so ban can tim de thue: ");
                int soBan = sc.nextInt();
                qlS.traCuu(soBan, conn).forEach(p->p.hienThi());
            }
            
            //nhap id sanh de lay ten sanh
            System.out.print("Nhap ma sanh de them vao hoa don: ");
            String idSanh = Format.nhapIdSanh(sc);
            
            //lay ten sanh
            String tenSanh = null;
            String sqlTen ="SELECT ten_sanh from sanh_cuoi where id_sanh=?";
            PreparedStatement pstm = conn.prepareStatement(sqlTen);
            pstm.setString(1,idSanh);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                tenSanh = rs.getString("ten_sanh");
            }
            
            //lay gia cua sanh theo thoi diem
            ThoiDiem thoiDiem = this.nhapTD(ngayTC, sc);
            BigDecimal gia = thoiDiem.layGiaSanh(idSanh, conn);
            
            //nhap cac hoa don
            try {
                String sqlThemSanh ="INSERT INTO `detai3`.`hoa_don` "
                        + "(`id_hoa_don`, `id_sanh`, `ten_buoi_tiec`, `ten_sanh`, "
                        + "`thoi_diem`, `gia_sanh`, `ngay_thue`) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?);";
                pstm = conn.prepareStatement(sqlThemSanh);
                java.sql.Date ngayToChuc = new java.sql.Date(ngayTC.getTime());
                pstm.setInt(1, id);
                pstm.setString(2, idSanh);
                pstm.setString(3, tenTiec);
                pstm.setString(4, tenSanh);
                pstm.setString(5, thoiDiem.toString());
                pstm.setBigDecimal(6, gia);
                pstm.setDate(7, ngayToChuc);
                pstm.execute();
                
            }catch (SQLException se) {
                System.err.println(se.getMessage());
            }
        }catch(SQLException se){
                System.err.println(se.getMessage());
        }
    }
    
    public void tongBill(Connection conn,int id){
        try {
            CallableStatement cstm = conn.prepareCall("{call sumBill(?)}");
            cstm.setInt(1, id);
            cstm.execute();
        } catch (SQLException se) {
            System.err.println(se.getMessage());
        }
    }
    
    
    /**
     *
     * @param conn
     * @throws SQLException
     */
    
   
    
    private ThoiDiem nhapTD(Date ngayTC, Scanner sc){
        ThoiDiem thoiDiem;
        Calendar cal = Calendar.getInstance();
        cal.setTime(ngayTC);
        int dOfW = cal.get(Calendar.DAY_OF_WEEK);
        System.out.print("Nhap thoi diem(1.sang, 2.chieu, 3.toi) : ");
        int td = sc.nextInt();
        switch(td){
                case 1:
                    if(dOfW<7&&dOfW>1) {
                        thoiDiem = ThoiDiem.SANG_NGAY_THUONG;
                    }else{
                        thoiDiem = ThoiDiem.SANG_CUOI_TUAN;
                    }
                    break;
                case 2:
                    if(dOfW<7&&dOfW>1) {
                        thoiDiem = ThoiDiem.CHIEU_NGAY_THUONG;
                    }else{
                        thoiDiem = ThoiDiem.CHIEU_CUOI_TUAN;
                    }
                    break;
                case 3:
                default:
                    if(dOfW<7&&dOfW>1) {
                        thoiDiem = ThoiDiem.TOI_NGAY_THUONG;
                    }else{
                        thoiDiem = ThoiDiem.TOI_CUOI_TUAN;
                    }
                    break;
            } 
        return thoiDiem;
    }
}

