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
import model.ThucUong;

/**
 *
 * @author Admin
 */
public class QLThucUong implements IQuanLy<ThucUong>{

    @Override
    public void them(ThucUong mon, Connection conn) {
        String sqlUong = "insert into thuc_uong (ten_thuc_uong, gia_thuc_uong, hsx) values ( ?, ?, ?)";
        try (PreparedStatement pstm = conn.prepareStatement(sqlUong)) {
            pstm.setString(1, mon.getTenMon());
            pstm.setBigDecimal(2, mon.getGiaMon());
            pstm.setString(3, mon.gethSX());
            pstm.execute();
        }catch(SQLException se){
            System.err.println(se.getMessage());
        }
    }

    @Override
    public void capNhat(Scanner sc, Connection conn) {
        try {
            String sqlTenUong = "update thuc_uong set ten_thuc_uong=? where id_thuc_uong=?";
            String sqlGiaUong = "update thuc_uong set gia_thuc_uong=? where id_thuc_uong=?";
            String sqlHSD = "update thuc_uong set hsx=? where id_thuc_uong=?";
            
            this.traCuu(conn).forEach(p->p.hienThi());
            System.out.println("\nNhap ma thuc uong can cap nhat: ");
            int chon= sc.nextInt();
            PreparedStatement stmt = conn.prepareStatement("SELECT id_thuc_uong "
                    + "FROM thuc_uong WHERE id_thuc_uong=?");
            stmt.setInt(1, chon);
            ResultSet rs = stmt.executeQuery();
            char c ;
            if(rs.next()){
                do{
                    System.out.println("Ban muon sua thong tin nao");
                    System.out.println("1.Ten mon uong");
                    System.out.println("2.gia mon uong");
                    System.out.println("3.hsd");
                    System.out.println("Chon: ");
                    int chon1 = sc.nextInt();
                    sc.nextLine();
                    switch(chon1){
                        case 1:
                            System.out.print("Nhap lai ten mon uong:");
                            String tens = sc.nextLine();
                            stmt = conn.prepareStatement(sqlTenUong);
                            stmt.setString(1, tens);
                            stmt.setInt(2, chon);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 2:
                            System.out.print("Nhap lai gia:");
                            BigDecimal gia = sc.nextBigDecimal();
                            stmt = conn.prepareStatement(sqlGiaUong);
                            stmt.setBigDecimal(1, gia);
                            stmt.setInt(2, chon);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 3:
                            System.out.print("Nhap hang san xuat: ");
                            sc.nextLine();
                            String hsx = sc.nextLine();
                            stmt = conn.prepareStatement(sqlHSD);
                            stmt.setString(1, hsx);
                            stmt.setInt(2, chon);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        default:
                            break;
                    }
                    System.out.print("Ban co muon sua them khong(Y/N):");
                    c = sc.next().charAt(0);
                }while(c=='y'||c=='Y');
            }else{
                System.out.println("Nhap sai ma");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void xoa(String id, Connection conn) {
        String sqlXoaUong = "DELETE FROM `detai3`.`thuc_uong` WHERE (`id_thuc_uong` = ?);";
        try (PreparedStatement stmt = conn.prepareStatement(sqlXoaUong)) {
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<ThucUong> traCuu(String kw, Connection conn) {
        List<ThucUong> ds = new ArrayList<>();
        try {
            String sqlAn ="select * from thuc_uong where ten_thuc_uong like ?";
            PreparedStatement pstm = conn.prepareCall(sqlAn);
            pstm.setString(1, "%" + kw + "%");
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                do{
                    ThucUong mon = new ThucUong(rs.getInt("id_thuc_uong"),rs.getString("ten_thuc_uong"),
                    rs.getBigDecimal("gia_thuc_uong"),rs.getString("hsx"));
                    
                    ds.add(mon);
                }while(rs.next());
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    }
    
    public List<ThucUong> traCuu( Connection conn) {
        List<ThucUong> ds = new ArrayList<>();
        try (Statement stm = conn.createStatement()) {
            ResultSet rs= stm.executeQuery("SELECT * from thuc_uong");
            while(rs.next()){
                ThucUong thucUong = new ThucUong(rs.getInt("id_thuc_uong"),
                        rs.getString("ten_thuc_uong"),rs.getBigDecimal("gia_thuc_uong")
                        , rs.getString("hsx"));
                ds.add(thucUong);
            }
        }catch(SQLException se){
            System.err.println(se.getMessage());
        }
        return ds;
    }
    
}
