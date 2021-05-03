/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.DichVu;
import model.Karaoke;
import model.MonAn;
import model.ThucAn;
import model.ThucUong;
import model.ThueCaSi;
import model.TrangTriPhoiCanh;

/**
 *
 * @author Admin
 */
public class XuatHoaDon {
    
    public void xuatAll(Connection conn) throws SQLException{
        for(int id=1;id<DBGetId.getLastIdHD(conn);id++){
            this.xuat(conn, id);
        }
    }
    
    public void xuat(Connection conn, int id){
        try  {
            String sqlXuat = "select* from hoa_don where id_hoa_don=?";
            PreparedStatement pstm = conn.prepareStatement(sqlXuat);
            pstm.setInt(1,id );
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                System.out.println("\n=================================================");
                System.out.println("Ma hoa don: "+rs.getInt("id_hoa_don"));
                System.out.println("Ten buoi tiec: "+rs.getString("ten_buoi_tiec"));
                System.out.println("Ten sanh: "+rs.getString("ten_sanh"));
                System.out.println("\nNgay thue: "+rs.getDate("ngay_thue"));
                System.out.println("Thoi diem: "+rs.getString("thoi_diem"));
                System.out.printf("Gia sanh: %,.0f VND",rs.getBigDecimal("gia_sanh"));
                

                System.out.println("\nDanh sach mon an da dat: ");
                xuatBillAn(id, conn).forEach(p->System.out.println(p));
                xuatBillUong(id, conn).forEach(p->System.out.println(p));
                System.out.println();
                System.out.print("\nDanh sach dich vu thue: \n");
                xuatBillKara(id, conn).forEach(p->System.out.println(p));
                xuatBillCS(id, conn).forEach(p->System.out.println(p));
                xuatBillTT(id, conn).forEach(p->System.out.println(p));


                System.out.println("\n-------------------------------------------------");
                System.out.printf("%32s %,.0f VND","Tong tien:",rs.getBigDecimal("tong_tien"));
                System.out.println("\n=================================================");
            }
        }catch(SQLException se){
            System.err.println(se.getMessage());
        }
    
    }
    
    private List<MonAn> xuatBillAn(int id, Connection conn){
        List<MonAn> ds = new ArrayList<>();
        String sqlAn = "select* from hd_thuc_an where id_hd=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sqlAn);
            pstm.setInt(1,id );
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                MonAn mon = new ThucAn(rs.getInt("id_thuc_an"),rs.getString("ten_thuc_an"),
                            rs.getBigDecimal("gia_thuc_an"));
                    
                ds.add(mon);
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    }
   
    private List<MonAn> xuatBillUong(int id, Connection conn){
        List<MonAn> ds = new ArrayList<>();
        String sqlUong = "select* from hd_thuc_uong where id_hd=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sqlUong);
            pstm.setInt(1,id );
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                 MonAn mon = new ThucUong(rs.getInt("id_thuc_uong"),rs.getString("ten_thuc_uong"),
                    rs.getBigDecimal("gia_thuc_uong"));
                    ds.add(mon);
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    }
    
    private List<DichVu> xuatBillKara(int id, Connection conn){
        List<DichVu> ds = new ArrayList<>();
        try {
            String sqlKara = "select* from hd_kara where id_hd=?";
            PreparedStatement pstm = conn.prepareStatement(sqlKara);
            pstm.setInt(1,id );
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                DichVu dv = new Karaoke(rs.getInt("id_kara"),rs.getString("ten_kara"),
                    rs.getBigDecimal("gia_kara"));
                    ds.add(dv);
            }
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
        return ds;
    }
    
    private List<DichVu> xuatBillCS(int id, Connection conn){
        List<DichVu> ds = new ArrayList<>();
        try {
            String sqlCasi = "select* from hd_ca_si where id_hd=?";
            PreparedStatement pstm = conn.prepareStatement(sqlCasi);
            pstm.setInt(1,id );
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                DichVu dv = new ThueCaSi(rs.getInt("id_ca_si"),rs.getString("ten_dv_cs"),
                        rs.getBigDecimal("gia_thue"));
                ds.add(dv);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
        
    }
    
    private List<DichVu> xuatBillTT(int id, Connection conn){
        String sqlTT = "select* from hd_tt where id_hd=?"; 
        List<DichVu> ds = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sqlTT);
            pstm.setInt(1,id );
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                DichVu phoiCanh = new TrangTriPhoiCanh(rs.getInt("id_tt"),
                            rs.getString("ten_tt"),
                    rs.getBigDecimal("gia_tt"));
                ds.add(phoiCanh);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    }
    
    
     
     
    public BigDecimal tinhDTThang(int nam ,int thang, Connection conn) throws SQLException{
        BigDecimal tienThang = null;
        try (CallableStatement cstm = conn.prepareCall("{call sumThang(?,?,?)}")) {
            cstm.setInt(1, nam);
            cstm.setInt(2, thang);
            cstm.registerOutParameter(3, Types.DECIMAL);
            cstm.execute();
            tienThang = cstm.getBigDecimal(3);
        }catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        return  tienThang;
    }
    
    public BigDecimal tinhDTQuy(int nam,int quy, Connection conn) throws SQLException{
        BigDecimal tienQuy = null;
        try (CallableStatement cstm = conn.prepareCall("{call sumQuy(?,?,?)}")) {
            cstm.setInt(1, nam);
            cstm.setInt(2, quy);
            cstm.registerOutParameter(3, Types.DECIMAL);
            cstm.execute();
            tienQuy = cstm.getBigDecimal(3);
        }catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        return tienQuy;
        
    }
    
    
}
