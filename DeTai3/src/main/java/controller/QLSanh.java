/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.SanhCuoi;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Scanner;
import model.ThoiDiem;

/**
 *
 * @author Admin
 */
public class QLSanh implements IQuanLy<SanhCuoi>{

    public QLSanh() {
        
    }

    @Override
    public void them(SanhCuoi sanh, Connection conn) {
        String sql = "insert into sanh_cuoi"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            String newMa = DBGetId.getIdSanh(conn);
            pstm.setString(1, newMa);
            pstm.setString(2, sanh.getTenSanh());
            pstm.setInt(3, sanh.getViTri());
            pstm.setInt(4, sanh.getSucChua());
            pstm.setBigDecimal(5, sanh.getDsGia().get(ThoiDiem.SANG_NGAY_THUONG));
            pstm.setBigDecimal(6, sanh.getDsGia().get(ThoiDiem.CHIEU_NGAY_THUONG));
            pstm.setBigDecimal(7, sanh.getDsGia().get(ThoiDiem.TOI_NGAY_THUONG));
            pstm.setBigDecimal(8, sanh.getDsGia().get(ThoiDiem.SANG_CUOI_TUAN));
            pstm.setBigDecimal(9, sanh.getDsGia().get(ThoiDiem.CHIEU_CUOI_TUAN));
            pstm.setBigDecimal(10, sanh.getDsGia().get(ThoiDiem.TOI_CUOI_TUAN));
            pstm.execute();
        }catch(SQLException se){
            System.err.println(se.getMessage());
        }
        
    }
    @Override
    public void capNhat(Scanner sc, Connection conn){
        try {
            String sqlTen = "update sanh_cuoi set ten_sanh=? where id_sanh=?";
            String sqlVitri = "update sanh_cuoi set vi_tri=? where id_sanh=?";
            String sqlSucChua = "update sanh_cuoi set suc_chua=? where id_sanh=?";
            String sqlGiaSangNT = "update sanh_cuoi set gia_sang_ngay_thuong=? where id_sanh=?";
            String sqlGiaChieuNT = "update sanh_cuoi set gia_chieu_ngay_thuong=? where id_sanh=?";
            String sqlGiaToiNT = "update sanh_cuoi set gia_toi_ngay_thuong=? where id_sanh=?";
            String sqlGiaSangCT = "update sanh_cuoi set gia_sang_cuoi_tuan=? where id_sanh=?";
            String sqlGiaChieuCT = "update sanh_cuoi set gia_chieu_cuoi_tuan=? where id_sanh=?";
            String sqlGiaToiCT = "update sanh_cuoi set gia_toi_cuoi_tuan=? where id_sanh=?";
            this.traCuu(conn).forEach(p->p.hienThi());
            System.out.println("\nNhap ma sanh can cap nhat: ");
            String maS= Format.nhapIdSanh(sc);
            PreparedStatement stmt = conn.prepareStatement("SELECT id_sanh FROM sanh_cuoi WHERE id_sanh=?");
            stmt.setString(1, maS);
            ResultSet rs = stmt.executeQuery();
            char c ;
            if(rs.next()){
                do{
                    System.out.println("Ban muon sua thong tin nao");
                    System.out.println("1.Ten sanh");
                    System.out.println("2.Vi tri");
                    System.out.println("3.Suc chua");
                    System.out.println("4.Gia sang ngay thuong");
                    System.out.println("5.Gia chieu ngay thuong");
                    System.out.println("6.Gia toi ngay thuong");
                    System.out.println("7.Gia sang cuoi tuan");
                    System.out.println("8.Gia chieu cuoi tuan");
                    System.out.println("9.Gia toi cuoi tuan");
                    System.out.println("Chon: ");
                    int chon = sc.nextInt();
                    sc.nextLine();
                    switch(chon){
                        case 1:
                            System.out.print("Nhap lai ten sanh: ");
                            String tens = sc.nextLine();
                            stmt = conn.prepareStatement(sqlTen);
                            stmt.setString(1, tens);
                            stmt.setString(2, maS);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 2:
                            System.out.print("Nhap lai vi tri: ");
                            int vtS = sc.nextInt();
                            stmt = conn.prepareStatement(sqlVitri);
                            stmt.setInt(1, vtS);
                            stmt.setString(2, maS);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 3:
                            System.out.print("Nhap lai suc chua: ");
                            int scS = sc.nextInt();
                            stmt = conn.prepareStatement(sqlSucChua);
                            stmt.setInt(1, scS);
                            stmt.setString(2, maS);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 4:
                            System.out.print("Nhap lai gia sang ngay thuong: ");
                            BigDecimal giaST = sc.nextBigDecimal();
                            stmt = conn.prepareStatement(sqlGiaSangNT);
                            stmt.setBigDecimal(1, giaST);
                            stmt.setString(2, maS);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 5:
                            System.out.print("Nhap lai gia sang ngay thuong: ");
                            BigDecimal giaCT = sc.nextBigDecimal();
                            stmt = conn.prepareStatement(sqlGiaChieuNT);
                            stmt.setBigDecimal(1, giaCT);
                            stmt.setString(2, maS);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 6:
                            System.out.print("Nhap lai gia sang ngay thuong: ");
                            BigDecimal giaTT = sc.nextBigDecimal();
                            stmt = conn.prepareStatement(sqlGiaToiNT);
                            stmt.setBigDecimal(1, giaTT);
                            stmt.setString(2, maS);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 7:
                            System.out.print("Nhap lai gia sang ngay thuong: ");
                            BigDecimal giaSC = sc.nextBigDecimal();
                            stmt = conn.prepareStatement(sqlGiaSangCT);
                            stmt.setBigDecimal(1, giaSC);
                            stmt.setString(2, maS);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 8:
                            System.out.print("Nhap lai gia sang ngay thuong: ");
                            BigDecimal giaCC = sc.nextBigDecimal();
                            stmt = conn.prepareStatement(sqlGiaChieuCT);
                            stmt.setBigDecimal(1, giaCC);
                            stmt.setString(2, maS);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        case 9:
                            System.out.print("Nhap lai gia sang ngay thuong: ");
                            BigDecimal giaTC = sc.nextBigDecimal();
                            stmt = conn.prepareStatement(sqlGiaToiCT);
                            stmt.setBigDecimal(1, giaTC);
                            stmt.setString(2, maS);
                            stmt.executeUpdate();
                            stmt.close();
                            break;
                        default:
                            break;
                    }
                    System.out.println("Ban co muon sua them khong(Y/N):");
                    c = sc.next().charAt(0);
                }while(c=='y'||c=='Y');
                
                
            }else{
                System.out.println("Ban nhap ma sai!");
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
               
    }
    
    @Override
    public void xoa(String id, Connection conn){
        String sqlXoa = "delete from sanh_cuoi where id_sanh=?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlXoa)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException se){
            System.err.println(se.getMessage());
        }
        
    }
    
    @Override
    public List<SanhCuoi> traCuu(String kw, Connection conn){
        List<SanhCuoi> ds = new ArrayList<>();
        String sqlTra ="select * from sanh_cuoi where ten_sanh like ?";
        try (PreparedStatement pstm = conn.prepareCall(sqlTra)) {
            pstm.setString(1, "%" + kw + "%");
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                do{
                    EnumMap<ThoiDiem, BigDecimal> enumMap =  new EnumMap<>(ThoiDiem.class);
                    enumMap.put(ThoiDiem.SANG_NGAY_THUONG, rs.getBigDecimal("gia_sang_ngay_thuong"));
                    enumMap.put(ThoiDiem.CHIEU_NGAY_THUONG,rs.getBigDecimal("gia_chieu_ngay_thuong"));
                    enumMap.put(ThoiDiem.TOI_NGAY_THUONG,rs.getBigDecimal("gia_toi_ngay_thuong"));
                    enumMap.put(ThoiDiem.SANG_CUOI_TUAN,rs.getBigDecimal("gia_sang_cuoi_tuan"));
                    enumMap.put(ThoiDiem.CHIEU_CUOI_TUAN,rs.getBigDecimal("gia_chieu_cuoi_tuan"));
                    enumMap.put(ThoiDiem.TOI_CUOI_TUAN,rs.getBigDecimal("gia_toi_cuoi_tuan"));
                    
                    SanhCuoi sanh = new SanhCuoi(rs.getString("id_sanh"),rs.getString("ten_sanh"),
                            rs.getInt("vi_tri"),rs.getInt("suc_chua"));
                    
                    sanh.setDsGia(enumMap);
                    ds.add(sanh);
                    
                }while(rs.next());
            }else{
                System.out.println("Khong tim thay");
            }
            pstm.close();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return ds;
    } 
    
    //Overload
    public List<SanhCuoi> traCuu(int kw, Connection conn){
        List<SanhCuoi> ds = new ArrayList<>();
        String sqlTra ="SELECT * from sanh_cuoi where suc_chua >=?";
        try (PreparedStatement pstm = conn.prepareCall(sqlTra)) {
            pstm.setInt(1, kw);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                do{
                    EnumMap<ThoiDiem, BigDecimal> enumMap =  new EnumMap<>(ThoiDiem.class);
                    enumMap.put(ThoiDiem.SANG_NGAY_THUONG, rs.getBigDecimal("gia_sang_ngay_thuong"));
                    enumMap.put(ThoiDiem.CHIEU_NGAY_THUONG,rs.getBigDecimal("gia_chieu_ngay_thuong"));
                    enumMap.put(ThoiDiem.TOI_NGAY_THUONG,rs.getBigDecimal("gia_toi_ngay_thuong"));
                    enumMap.put(ThoiDiem.SANG_CUOI_TUAN,rs.getBigDecimal("gia_sang_cuoi_tuan"));
                    enumMap.put(ThoiDiem.CHIEU_CUOI_TUAN,rs.getBigDecimal("gia_chieu_cuoi_tuan"));
                    enumMap.put(ThoiDiem.TOI_CUOI_TUAN,rs.getBigDecimal("gia_toi_cuoi_tuan"));
                    
                    SanhCuoi sanh = new SanhCuoi(rs.getString("id_sanh"),rs.getString("ten_sanh"),
                            rs.getInt("vi_tri"),rs.getInt("suc_chua"));
                    
                    sanh.setDsGia(enumMap);
                    ds.add(sanh);
                }while(rs.next());
            }else{
                System.out.println("Khong tim thay");
            }
            pstm.close();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    } 
    
    public List<SanhCuoi> traCuu( Connection conn){
        List<SanhCuoi> ds = new ArrayList<>();
        String sqlTra ="SELECT * from sanh_cuoi";
        try (Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery(sqlTra);
            while(rs.next()){
                EnumMap<ThoiDiem, BigDecimal> enumMap =  new EnumMap<>(ThoiDiem.class);
                    enumMap.put(ThoiDiem.SANG_NGAY_THUONG, rs.getBigDecimal("gia_sang_ngay_thuong"));
                    enumMap.put(ThoiDiem.CHIEU_NGAY_THUONG,rs.getBigDecimal("gia_chieu_ngay_thuong"));
                    enumMap.put(ThoiDiem.TOI_NGAY_THUONG,rs.getBigDecimal("gia_toi_ngay_thuong"));
                    enumMap.put(ThoiDiem.SANG_CUOI_TUAN,rs.getBigDecimal("gia_sang_cuoi_tuan"));
                    enumMap.put(ThoiDiem.CHIEU_CUOI_TUAN,rs.getBigDecimal("gia_chieu_cuoi_tuan"));
                    enumMap.put(ThoiDiem.TOI_CUOI_TUAN,rs.getBigDecimal("gia_toi_cuoi_tuan"));
                    
                    SanhCuoi sanh = new SanhCuoi(rs.getString("id_sanh"),rs.getString("ten_sanh"),
                            rs.getInt("vi_tri"),rs.getInt("suc_chua"));
                    
                    sanh.setDsGia(enumMap);
                    ds.add(sanh);
            }
            stm.close();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ds;
    } 
}
