/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 * @param <T> : Object
 */
public interface IQuanLy<T> {
    
    void them(T t,Connection conn);
    
    void capNhat(Scanner sc, Connection conn);
    
    void xoa(String id, Connection conn);
    
    List<T> traCuu(String kw, Connection conn);
}
