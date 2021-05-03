/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.TrangTriPhoiCanh;

/**
 *
 * @author Admin
 */
public class QLTrangTri implements IQuanLy<TrangTriPhoiCanh>{

    @Override
    public void them(TrangTriPhoiCanh t, Connection conn) {
        String sqlTT = "insert into trang_tri_phoi_canh (ten_phoi_canh, gia_phoi_canh) values (?, ?)";
        try (PreparedStatement pstm = conn.prepareStatement(sqlTT)) {
            pstm.setString(1, t.getTenDV());
            pstm.setBigDecimal(2, t.getGiaDV());
            pstm.execute();
            pstm.close();
            System.out.println("Them thanh cong!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void capNhat(Scanner sc, Connection conn) {
        try {
            String sqlTT = "update trang_tri_phoi_canh set ten_phoi_canh=? where id_trang_tri_phoi_canh=?";
            String sqlGiaTT = "update trang_tri_phoi_canh set gia_phoi_canh=? where id_trang_tri_phoi_canh=?";
            System.out.print("\nNhap ma trang tri can cap nhat: ");
            int chon= sc.nextInt();
            PreparedStatement stmt = conn.prepareStatement("SELECT id_trang_tri_phoi_canh "
                    + "FROM trang_tri_phoi_canh "
                    + "WHERE id_trang_tri_phoi_canh=?");
            stmt.setInt(1, chon);
            ResultSet rs = stmt.executeQuery();
            char c ;
            if(rs.next()){
                do{
                    System.out.println("Ban muon sua thong tin nao");
                    System.out.println("1.Ten trang tri");
                    System.out.println("2.gia trang tri");
                    System.out.println("Chon: ");
                    int chon1 = sc.nextInt();
                    switch(chon1){
                        case 1:
                            System.out.print("Nhap lai ten trang tri: ");
                            String tens = sc.nextLine();
                            stmt = conn.prepareStatement(sqlTT);
                            stmt.setString(1, tens);
                            stmt.setInt(2, chon);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 2:
                        default:    
                            System.out.print("Nhap lai gia: ");
                            BigDecimal gia = sc.nextBigDecimal();
                            stmt = conn.prepareStatement(sqlGiaTT);
                            stmt.setBigDecimal(1, gia);
                            stmt.setInt(2, chon);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                    }
                    System.out.print("Ban co muon cap nhat them khong(Y/N): ");
                    c = sc.next().charAt(0);
                }while(c=='y'||c=='Y');
            }else{
                System.out.println("Ban nhap sai ma");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void xoa(String id, Connection conn) {
        String sqlXoaTT = "delete from trang_tri_phoi_canh where id_trang_tri_phoi_canh=?";
        try(PreparedStatement stmt = conn.prepareStatement(sqlXoaTT)) {
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<TrangTriPhoiCanh> traCuu(String kw, Connection conn) {
        List<TrangTriPhoiCanh> ds = new ArrayList<>();
        try {
            String sqlKara ="select * from trang_tri_phoi_canh where ten_phoi_canh like ?";
            PreparedStatement pstm = conn.prepareCall(sqlKara);
            pstm.setString(1, "%" + kw + "%");
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                do{
                     TrangTriPhoiCanh dv = new TrangTriPhoiCanh(rs.getInt("id_trang_tri_phoi_canh"),
                            rs.getString("ten_phoi_canh"),
                    rs.getBigDecimal("gia_phoi_canh"));
                    
                    ds.add(dv);
                }while(rs.next());
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    }
    
    //Overload
    //lay toan bo tu data
    public List<TrangTriPhoiCanh> traCuu( Connection conn) {
        List<TrangTriPhoiCanh> ds = new ArrayList<>();
        String sqlTT ="select * from trang_tri_phoi_canh";
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sqlTT);
            while(rs.next()){
                TrangTriPhoiCanh phoiCanh = new TrangTriPhoiCanh(rs.getInt("id_trang_tri_phoi_canh"),
                            rs.getString("ten_phoi_canh"),
                    rs.getBigDecimal("gia_phoi_canh"));
                ds.add(phoiCanh);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    }
    
}
