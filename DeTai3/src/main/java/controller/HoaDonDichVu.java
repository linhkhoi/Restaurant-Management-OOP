/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class HoaDonDichVu {
    public void nhapKara(Connection conn, Scanner sc, int id){
        try {
            QLKara ka = new QLKara();
            ka.traCuu(conn).forEach(p->System.out.println(p));
            System.out.print("\nNhap id karaoke de them vao hoa don: ");
            int idAn = sc.nextInt();
            String ten ="";
            BigDecimal gia = new BigDecimal(BigInteger.ZERO);
            String sqlThemA ="SELECT ten_karaoke, gia_karaoke from karaoke where id_karaoke=?";
            PreparedStatement pstm = conn.prepareStatement(sqlThemA);
            pstm.setInt(1,idAn);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ten = rs.getString("ten_karaoke");
                gia = rs.getBigDecimal("gia_karaoke");
            }
            String sqlThemTA = "insert into hd_kara values(?, ?, ?, ?)";
            pstm = conn.prepareStatement(sqlThemTA);
            pstm.setInt(1, id);
            pstm.setInt(2, idAn);
            pstm.setString(3, ten);
            pstm.setBigDecimal(4, gia);
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void nhapCS(Connection conn,Scanner sc, int id){
        try {
            QLThueCS cs = new QLThueCS();
            cs.traCuu(conn).forEach(p->System.out.println(p));
            System.out.print("\nNhap id thue ca si de them vao hoa don: ");
            int idUong = sc.nextInt();
            String tenUong ="";
            BigDecimal giaUong = new BigDecimal(BigInteger.ZERO);
            String sqlThemU ="SELECT ten_ca_si, gia_thue from thue_ca_si where id_thue_ca_si=?";
            PreparedStatement pstm = conn.prepareStatement(sqlThemU);
            pstm.setInt(1,idUong);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                tenUong = rs.getString("ten_ca_si");
                giaUong = rs.getBigDecimal("gia_thue");
            }
            String sqlThemTU = "insert into hd_ca_si values(?, ?, ?, ?)";
            pstm = conn.prepareStatement(sqlThemTU);
            pstm.setInt(1, id);
            pstm.setInt(2, idUong);
            pstm.setString(3, tenUong);
            pstm.setBigDecimal(4, giaUong);
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void nhapTT(Connection conn, Scanner sc, int id){
        try {
            QLTrangTri tt = new QLTrangTri();
            tt.traCuu(conn).forEach(p->System.out.println(p));
            System.out.print("\nNhap id thue trang tri de them vao hoa don: ");
            int idt = sc.nextInt();
            String tent ="";
            BigDecimal giat = new BigDecimal(BigInteger.ZERO);
            String sqlTt ="SELECT ten_phoi_canh, gia_phoi_canh from trang_tri_phoi_canh where id_trang_tri_phoi_canh=?";
            PreparedStatement pstm = conn.prepareStatement(sqlTt);
            pstm.setInt(1,idt);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                tent = rs.getString("ten_phoi_canh");
                giat = rs.getBigDecimal("gia_phoi_canh");
            }
            String sqlThemTt = "insert into hd_tt values(?, ?, ?, ?)";
            pstm = conn.prepareStatement(sqlThemTt);
            pstm.setInt(1, id);
            pstm.setInt(2, idt);
            pstm.setString(3, tent);
            pstm.setBigDecimal(4, giat);
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
   
}
