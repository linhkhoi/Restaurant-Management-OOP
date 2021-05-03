/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class DBGetId {
    public static String getIdSanh(Connection conn) throws SQLException{
        String check = "SELECT id_sanh FROM sanh_cuoi ORDER BY id_sanh DESC LIMIT 1";
        String idSanh = "" ;
        try (Statement stm = conn.createStatement()) {
            ResultSet rs= stm.executeQuery(check);
            if(rs.next()){
                idSanh = rs.getString("id_sanh");
                String substr = idSanh.substring(1,4);
                int a = Integer.parseInt(substr);
                idSanh = String.format("S%03d", a+1);
            }else{
                idSanh = "S001";
            }
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        return idSanh;
    }
    
    public static int getLastIdHD(Connection conn) throws SQLException{
        int idHD =0;
        String check = "SELECT id_hoa_don FROM hoa_don ORDER BY id_hoa_don DESC LIMIT 1";
        try {
            Statement stm = conn.createStatement();
            ResultSet rs= stm.executeQuery(check);
            if(rs.next()){
                idHD = rs.getInt("id_hoa_don");
            }  
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        return idHD+1;
    }
}
