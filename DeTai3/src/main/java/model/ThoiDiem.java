/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public enum ThoiDiem {
    SANG_NGAY_THUONG {
        @Override
        public BigDecimal layGiaSanh(String idSanh, Connection conn) {
            BigDecimal gia = null;
            try {
                String sqlST ="select gia_sang_ngay_thuong from sanh_cuoi where id_sanh=?";
                PreparedStatement pstm = conn.prepareStatement(sqlST);
                pstm.setString(1,idSanh);
                ResultSet rs = pstm.executeQuery();
                while(rs.next()){
                    gia = rs.getBigDecimal("gia_sang_ngay_thuong");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThoiDiem.class.getName()).log(Level.SEVERE, null, ex);
            }
            return gia;
        }

        @Override
        public String toString() {
            return "Sang ngay thuong";
        }   
    },
    CHIEU_NGAY_THUONG {
        @Override
        public BigDecimal layGiaSanh(String idSanh, Connection conn) {
            BigDecimal gia = null;
            try {
                String sqlST ="select gia_chieu_ngay_thuong from sanh_cuoi where id_sanh=?";
                PreparedStatement pstm = conn.prepareStatement(sqlST);
                pstm.setString(1,idSanh);
                ResultSet rs = pstm.executeQuery();
                while(rs.next()){
                    gia = rs.getBigDecimal("gia_chieu_ngay_thuong");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThoiDiem.class.getName()).log(Level.SEVERE, null, ex);
            }
            return gia;
        }

        @Override
        public String toString() {
            return "Chieu ngay thuong";
        }

        
    },
    TOI_NGAY_THUONG {
        @Override
        @SuppressWarnings("AssignmentToMethodParameter")
        public BigDecimal layGiaSanh(String idSanh, Connection conn) {
            BigDecimal gia = null;
            try {
                String sqlST ="select gia_toi_ngay_thuong from sanh_cuoi where id_sanh=?";
                PreparedStatement pstm = conn.prepareStatement(sqlST);
                pstm.setString(1,idSanh);
                ResultSet rs = pstm.executeQuery();
                while(rs.next()){
                    gia = rs.getBigDecimal("gia_toi_ngay_thuong");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThoiDiem.class.getName()).log(Level.SEVERE, null, ex);
            }
            return gia;
        }

        @Override
        public String toString() {
            return "Toi ngay thuong";
        }

    },
    SANG_CUOI_TUAN {
        @Override
        @SuppressWarnings("AssignmentToMethodParameter")
        public BigDecimal layGiaSanh(String idSanh, Connection conn) {
            BigDecimal gia = null;
            try {
                String sqlST ="select gia_sang_cuoi_tuan from sanh_cuoi where id_sanh=?";
                PreparedStatement pstm = conn.prepareStatement(sqlST);
                pstm.setString(1,idSanh);
                ResultSet rs = pstm.executeQuery();
                while(rs.next()){
                    gia = rs.getBigDecimal("gia_sang_cuoi_tuan");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThoiDiem.class.getName()).log(Level.SEVERE, null, ex);
            }
            return gia;
        }

        @Override
        public String toString() {
            return "Sang cuoi tuan";
        }

    }, 
    CHIEU_CUOI_TUAN {
        @Override
        @SuppressWarnings("AssignmentToMethodParameter")
        public BigDecimal layGiaSanh(String idSanh, Connection conn) {
            BigDecimal gia = null;
            try {
                String sqlST ="select gia_chieu_cuoi_tuan from sanh_cuoi where id_sanh=?";
                PreparedStatement pstm = conn.prepareStatement(sqlST);
                pstm.setString(1,idSanh);
                ResultSet rs = pstm.executeQuery();
                while(rs.next()){
                    gia = rs.getBigDecimal("gia_chieu_cuoi_tuan");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThoiDiem.class.getName()).log(Level.SEVERE, null, ex);
            }
            return gia;
        }

        @Override
        public String toString() {
            return "Chieu cuoi tuan";
        }

    }, 
    TOI_CUOI_TUAN {
        @Override
        @SuppressWarnings("AssignmentToMethodParameter")
        public BigDecimal layGiaSanh(String idSanh, Connection conn) {
            BigDecimal gia = null;
            try {
                String sqlST ="select gia_toi_cuoi_tuan from sanh_cuoi where id_sanh=?";
                PreparedStatement pstm = conn.prepareStatement(sqlST);
                pstm.setString(1,idSanh);
                ResultSet rs = pstm.executeQuery();
                while(rs.next()){
                    gia = rs.getBigDecimal("gia_toi_cuoi_tuan");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThoiDiem.class.getName()).log(Level.SEVERE, null, ex);
            }
            return gia;
        }

        @Override
        public String toString() {
            return "Toi cuoi tuan";
        }

    };
    public abstract BigDecimal layGiaSanh(String idSanh,Connection conn);
}
