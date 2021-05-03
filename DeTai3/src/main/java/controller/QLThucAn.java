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
import model.ThucAn;

/**
 *
 * @author Admin
 */
public class QLThucAn implements IQuanLy<ThucAn>{

    @Override
    public void them(ThucAn mon, Connection conn) {
        String sqlAn = "insert into thuc_an (ten_thuc_an, gia_thuc_an, an_chay) values ( ?, ?, ?)";
        try (PreparedStatement pstm = conn.prepareStatement(sqlAn)) {
            pstm.setString(1, mon.getTenMon());
            pstm.setBigDecimal(2, mon.getGiaMon());
            pstm.setBoolean(3, mon.isAnChay());
            pstm.execute();
        }catch(SQLException se){
           System.err.println(se.getMessage());
        }
    }

    @Override
    public void capNhat(Scanner sc, Connection conn) {
        try {
            String sqlTenAn = "update thuc_an set ten_thuc_an=? where id_thuc_an=?";
            String sqlGiaAn = "update thuc_an set gia_thuc_an=? where id_thuc_an=?";
            String sqlAnChay = "update thuc_an set an_chay=? where id_thuc_an=?";
            this.traCuu(conn).forEach(p->p.hienThi());
            System.out.println("\nNhap ma thuc an can cap nhat: ");
            int chon= sc.nextInt();
            PreparedStatement stmt = conn.prepareStatement("SELECT id_thuc_an FROM thuc_an WHERE id_thuc_an=?");
            stmt.setInt(1, chon);
            ResultSet rs = stmt.executeQuery();
            char c ;
            if(rs.next()){
                do{
                        System.out.println("Ban muon sua thong tin nao");
                        System.out.println("1.Ten mon an");
                        System.out.println("2.gia mon an");
                        System.out.println("3.an chay");
                        System.out.println("Chon: ");
                        int chon1 = sc.nextInt();
                        sc.nextLine();
                        switch(chon1){
                            case 1:
                                System.out.print("Nhap lai ten mon an:");
                                String tens = sc.nextLine();
                                stmt = conn.prepareStatement(sqlTenAn);
                                stmt.setString(1, tens);
                                stmt.setInt(2, chon);
                                stmt.executeUpdate();
                                stmt.close();
                                break;
                            case 2:
                                System.out.print("Nhap lai gia:");
                                BigDecimal gia = sc.nextBigDecimal();
                                stmt = conn.prepareStatement(sqlGiaAn);
                                stmt.setBigDecimal(1, gia);
                                stmt.setInt(2, chon);
                                stmt.executeUpdate();
                                stmt.close();
                                break;
                            case 3:
                                System.out.print("Mon co an chay duoc khong(1.duoc, 2.khong):");
                                int scS = sc.nextInt();
                                stmt = conn.prepareStatement(sqlAnChay);
                                stmt.setBoolean(1, scS==1);
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
                System.out.println("Ma thuc an sai");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void xoa(String id, Connection conn) {
        String sqlXoaAn = "DELETE FROM `detai3`.`thuc_an` WHERE (`id_thuc_an` = ?);";
        try (PreparedStatement stmt = conn.prepareStatement(sqlXoaAn)) {
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<ThucAn> traCuu(String kw, Connection conn) {
        List<ThucAn> ds = new ArrayList<>();
        try {
            String sqlAn ="select * from thuc_an where ten_thuc_an like ?";
            PreparedStatement pstm = conn.prepareCall(sqlAn);
            pstm.setString(1, "%" + kw + "%");
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                do{
                    ThucAn mon = new ThucAn(rs.getInt("id_thuc_an"),rs.getString("ten_thuc_an"),
                            rs.getBigDecimal("gia_thuc_an"),rs.getBoolean("an_chay"));
                    
                    ds.add(mon);
                }while(rs.next());
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    }
    
    public List<ThucAn> traCuu(Connection conn) {
        List<ThucAn> ds = new ArrayList<>();
        try (Statement stm = conn.createStatement()) {
            ResultSet rs= stm.executeQuery("SELECT * from thuc_an");
            while(rs.next()){
                ThucAn thucAn = new ThucAn(rs.getInt("id_thuc_an"),
                        rs.getString("ten_thuc_an"),rs.getBigDecimal("gia_thuc_an")
                        , rs.getBoolean("an_chay"));
                ds.add(thucAn);
            }
        }catch(SQLException se){
            System.err.println(se.getMessage());
        }
        return ds;
    }
    
}
