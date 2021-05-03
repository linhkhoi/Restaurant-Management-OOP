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
import model.ThueCaSi;

/**
 *
 * @author Admin
 */
public class QLThueCS implements IQuanLy<ThueCaSi>{

    @Override
    public void them(ThueCaSi th, Connection conn) {
        String sqlThue = "insert into thue_ca_si (ten_dv_thue_ca_si, gia_thue, ten_ca_si, so_luong_bai) "
                + "values ( ?, ?, ?, ?)";
        try (PreparedStatement pstm = conn.prepareStatement(sqlThue)) {
            pstm.setString(1, th.getTenDV());
            pstm.setBigDecimal(2, th.getGiaDV());
            pstm.setString(3, th.getTenCaSi());
            pstm.setInt(4, th.getSoLuongBai());
            pstm.execute();
            pstm.close();
            System.out.println("Them thanh cong!");
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void capNhat(Scanner sc, Connection conn) {
        try {
            String sqlTenDVCS = "update thue_ca_si ten_dv_thue_ca_si=? where id_thue_ca_si=?";
            String sqlGiaCS = "update thue_ca_si set gia_thue=? where id_thue_ca_si=?";
            String sqlTenCS = "update thue_ca_si set ten_ca_si=? where id_thue_ca_si=?";
            String sqlSL = "update thue_ca_si set so_luong_bai=? where id_thue_ca_si=?";
            this.traCuu(conn).forEach(p->p.hienThi());
            System.out.print("\nNhap ma karaoke can cap nhat: ");
            int chon= sc.nextInt();
            PreparedStatement stmt = conn.prepareStatement("SELECT id_karaoke FROM karaoke WHERE id_karaoke =?");
            stmt.setInt(1, chon);
            ResultSet rs = stmt.executeQuery();
            char c ;
            if(rs.next()){
                do{
                    System.out.println("Ban muon sua thong tin nao");
                    System.out.println("1.Ten dich vu thue ca si");
                    System.out.println("2.Gia thue ca si");
                    System.out.println("3.Ten ca si");
                    System.out.println("4.So luong bai");
                    System.out.println("Chon: ");
                    int chon1 = sc.nextInt();
                    switch(chon1){
                        case 1:
                            System.out.print("Nhap lai ten dich vu ca si: ");
                            String tens = sc.nextLine();
                            stmt = conn.prepareStatement(sqlTenDVCS);
                            stmt.setString(1, tens);
                            stmt.setInt(2, chon);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 2:
                            System.out.print("Nhap lai gia thue: ");
                            BigDecimal gia = sc.nextBigDecimal();
                            stmt = conn.prepareStatement(sqlGiaCS);
                            stmt.setBigDecimal(1, gia);
                            stmt.setInt(2, chon);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 3:
                            System.out.print("Nhap ten ca si: ");
                            String tenca = sc.nextLine();
                            stmt = conn.prepareStatement(sqlTenCS);
                            stmt.setString(1, tenca);
                            stmt.setInt(2, chon);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 4:
                        default:    
                            System.out.print("Nhap ten ca si: ");
                            int sl = sc.nextInt();
                            stmt = conn.prepareStatement(sqlSL);
                            stmt.setInt(1, sl);
                            stmt.setInt(2, chon);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                    }
                    System.out.print("Ban co muon cap nhat them khong(Y/N): ");
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
        String sqlXoaCa = "delete from thue_ca_si where id_thue_ca_si=?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlXoaCa)) {
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<ThueCaSi> traCuu(String kw, Connection conn) {
        List<ThueCaSi> ds = new ArrayList<>();
        try {
            String sqlKara ="select * from thue_ca_si where ten_ca_si like ?";
            PreparedStatement pstm = conn.prepareCall(sqlKara);
            pstm.setString(1, "%" + kw + "%");
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                do{
                     ThueCaSi dv = new ThueCaSi(rs.getInt("id_thue_ca_si"),rs.getString("ten_dv_thue_ca_si"),
                        rs.getBigDecimal("gia_thue"),rs.getString("ten_ca_si"),rs.getInt("so_luong_bai"));
                    
                    ds.add(dv);
                }while(rs.next());
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    }
    
    
    //Overloading
    public List<ThueCaSi> traCuu(Connection conn){
        List<ThueCaSi> ds = new ArrayList<>();
        String sqlCS ="select * from thue_ca_si";
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sqlCS);
            while(rs.next()){
                ThueCaSi dv = new ThueCaSi(rs.getInt("id_thue_ca_si"),rs.getString("ten_dv_thue_ca_si"),
                        rs.getBigDecimal("gia_thue"),rs.getString("ten_ca_si"),rs.getInt("so_luong_bai"));
                ds.add(dv);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    }
}
