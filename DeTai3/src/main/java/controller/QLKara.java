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
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Karaoke;

/**
 *
 * @author Admin
 */
public class QLKara implements IQuanLy<Karaoke>{

    @Override
    public void them(Karaoke ka, Connection conn) {
        String sqlKa = "insert into karaoke (ten_karaoke, gia_karaoke, thoi_gian) values ( ?, ?, ?)";
        try (PreparedStatement pstm = conn.prepareStatement(sqlKa)) {
            pstm.setString(1, ka.getTenDV());
            pstm.setBigDecimal(2, ka.getGiaDV());
            pstm.setTime(3, ka.gettG());
            pstm.execute();
            System.out.println("Them thanh cong!");
            pstm.close();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

    @Override
    public void capNhat(Scanner sc, Connection conn) {
        try {
            String sqlTenKara = "update karaoke set ten_karaoke=? where id_karaoke=?";
            String sqlGiaKara = "update karaoke set gia_karaoke=? where id_karaoke=?";
            String sqlTG = "update karaoke set thoi_gian=? where id_karaoke=?";
            this.traCuu(conn).forEach(p->p.hienThi());
            System.out.print("Nhap ma karaoke can cap nhat: ");
            int chon= sc.nextInt();
            PreparedStatement stmt = conn.prepareStatement("SELECT id_karaoke FROM karaoke WHERE id_karaoke =?");
            stmt.setInt(1, chon);
            ResultSet rs = stmt.executeQuery();char c ;
            if(rs.next()){
                do{
                    System.out.println("Ban muon sua thong tin nao");
                    System.out.println("1.Ten karaoke");
                    System.out.println("2.gia karaoke");
                    System.out.println("3.thoi gian");
                    System.out.println("Chon: ");
                    int chon1 = sc.nextInt();
                    switch(chon1){
                        case 1:
                            System.out.print("Nhap lai ten karaoke: ");
                            String tens = sc.nextLine();
                            stmt = conn.prepareStatement(sqlTenKara);
                            stmt.setString(1, tens);
                            stmt.setInt(2, chon);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 2:
                            System.out.print("Nhap lai gia: ");
                            BigDecimal gia = sc.nextBigDecimal();
                            stmt = conn.prepareStatement(sqlGiaKara);
                            stmt.setBigDecimal(1, gia);
                            stmt.setInt(2, chon);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 3:
                        default:    
                            System.out.print("Nhap lai so gio karaoke: ");
                            int gio;
                            gio= sc.nextInt();
                            stmt = conn.prepareStatement(sqlTG);
                            Time tg = new Time(gio, 0, 0);
                            stmt.setTime(1, tg);
                            stmt.setInt(2, chon);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                    }
                    System.out.println("Ban co muon cap nhat them khong(Y/N):");
                    c = sc.next().charAt(0);
                }while(c=='y'||c=='Y');
            }else{
                System.out.println("Nhap ma sai");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void xoa(String id, Connection conn) {
        String sqlXoaKa = "delete from karaoke where id_karaoke=?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlXoaKa)) {
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Karaoke> traCuu(String kw, Connection conn) {
        List<Karaoke> ds = new ArrayList<>();
        try {
            String sqlKara ="select * from karaoke where ten_karaoke like ?";
            PreparedStatement pstm = conn.prepareCall(sqlKara);
            pstm.setString(1, "%" + kw + "%");
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                do{
                    Karaoke dv = new Karaoke(rs.getInt("id_karaoke"),rs.getString("ten_karaoke"),
                            rs.getBigDecimal("gia_karaoke"),rs.getTime("thoi_gian"));
                    
                    ds.add(dv);
                }while(rs.next());
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    }
    
    //Overloading
    public List<Karaoke> traCuu(Connection conn) {
        List<Karaoke> ds = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * from karaoke");
                while(rs.next()){
                    Karaoke dv = new Karaoke(rs.getInt("id_karaoke"),rs.getString("ten_karaoke"),
                    rs.getBigDecimal("gia_karaoke"),rs.getTime("thoi_gian"));
                    ds.add(dv);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    }
    
}
