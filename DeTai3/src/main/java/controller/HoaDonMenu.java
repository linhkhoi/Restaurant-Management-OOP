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
public class HoaDonMenu {
    public void nhapAn(Connection conn, Scanner sc, int id){
        try {
            QLThucAn an = new QLThucAn();
            an.traCuu(conn).forEach(p->System.out.println(p));
            System.out.print("\nNhap id thuc an de them vao hoa don: ");
            int idAn = sc.nextInt();
            String ten ="";
            BigDecimal gia =new BigDecimal(BigInteger.ZERO);
            String sqlThemA ="SELECT ten_thuc_an, gia_thuc_an from thuc_an where id_thuc_an=?";
            PreparedStatement pstm = conn.prepareStatement(sqlThemA);
            pstm.setInt(1, idAn);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ten = rs.getString("ten_thuc_an");
                gia = rs.getBigDecimal("gia_thuc_an");
            }
            String sqlThemTA = "insert into hd_thuc_an values(?, ?, ?, ?)";
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
    
    
    
    public void nhapUong(Connection conn, Scanner sc, int id){
        try {
            QLThucUong uong = new QLThucUong();
            uong.traCuu(conn).forEach(p->System.out.println(p));
            System.out.print("\nNhap id thuc uong de them vao hoa don: ");
            int idUong = sc.nextInt();
            String tenUong ="";
            BigDecimal giaUong = new BigDecimal(BigInteger.ZERO);
            String sqlThemU ="SELECT ten_thuc_uong, gia_thuc_uong from thuc_uong where id_thuc_uong=?";
            PreparedStatement pstm = conn.prepareStatement(sqlThemU);
            pstm.setInt(1,idUong);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                tenUong = rs.getString("ten_thuc_uong");
                giaUong = rs.getBigDecimal("gia_thuc_uong");
            }
            String sqlThemTU = "insert into hd_thuc_uong values(?, ?, ?, ?)";
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
    
}
