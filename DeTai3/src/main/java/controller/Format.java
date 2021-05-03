/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class Format {
    public static Date nhapNgay(String date){
        Date ngayTC;
        SimpleDateFormat ngay = new SimpleDateFormat("dd/MM/yyyy");
        while(true){
                try {
                    ngayTC = ngay.parse(date);
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                    System.out.println("Nhap lai");
                    continue;
                }
                return ngayTC;
            }
    }
    
    public static String nhapIdSanh(Scanner sc){
        String idSanh;
        boolean kt=false;
            do{
                idSanh = sc.next();

                String regrex = "^[S]{1}\\d{3}";
                Pattern pattern = Pattern.compile(regrex);
                Matcher matcher = pattern.matcher(idSanh);
                if(matcher.find()){
                    kt=true;
                }else{
                    System.out.print("ban nhap sai dinh dang cua id sanh.\nVui long nhap lai: ");
                }
            
            }while(kt==false);
        return idSanh;
    }
    
}
