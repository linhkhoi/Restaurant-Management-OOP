/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.QLSanh;
import controller.DBConnection;
import controller.DBGetId;
import controller.XuatHoaDon;
import controller.Format;
import controller.HoaDon;
import controller.HoaDonDichVu;
import controller.HoaDonMenu;
import controller.QLKara;
import controller.QLThucAn;
import controller.QLThucUong;
import controller.QLThueCS;
import controller.QLTrangTri;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import model.Karaoke;
import model.SanhCuoi;
import model.ThucAn;
import model.ThucUong;
import model.ThueCaSi;
import model.TrangTriPhoiCanh;

/**
 *
 * @author Admin
 */
public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        HoaDonMenu menu = new HoaDonMenu();
        HoaDonDichVu dv = new HoaDonDichVu();
        XuatHoaDon xuat = new XuatHoaDon();
        HoaDon hd = new HoaDon();
        Scanner sc = new Scanner(System.in);
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            int x;
            do {
                System.out.println("\n ----QUAN LY NHA HANG TIEC CUOI----");
                System.out.println("|1. QUAN LY SANH                   |");
                System.out.println("|2. QUAN LY MON AN                 |");
                System.out.println("|3. QUAN LY DICH VU                |");
                System.out.println("|4. THEM HOA DON                   |");
                System.out.println("|5. XUAT DANH SACH HOA DON         |");
                System.out.println("|6. TINH DOANH THU THEO THANH      |");
                System.out.println("|7. TINH DOANH THU THEO QUY        |");
                System.out.println("|8. THOAT                          |");
                System.out.println(" ----------------------------------");
                System.out.print("======>> ");
                x=sc.nextInt();
                switch(x){
                    case 1:
                        quanLySanh(sc, conn);
                        break;
                    case 2:
                        quanLyMenu(sc,conn);
                        break;
                    case 3:
                        quanLyDV(sc,conn);
                        break;
                    case 4:
                        int id = DBGetId.getLastIdHD(conn);
                        conn.setAutoCommit(false);
                        try {
                            hd.nhapHD(sc, conn);
                            char c  ;
                            do{
                                System.out.println("Ban muon nhap mon an nao bill: ");
                                System.out.println("1.Thuc an");
                                System.out.println("2.Thuc uong");
                                System.out.println("chon: ");
                                int chon = sc.nextInt();
                                if(chon==1){
                                    menu.nhapAn(conn, sc,id);
                                }else{
                                    menu.nhapUong(conn, sc,id);
                                }
                                System.out.print("Ban co muon nhap them khong(Y/N):");
                                c = sc.next().charAt(0);
                            }while(c=='y'||c=='Y');
                            char d ;
                            do{
                                System.out.println("Ban muon nhap dich vu nao bill: ");
                                System.out.println("1.karaoke");
                                System.out.println("2.thue ca si");
                                System.out.println("3.trang tri");
                                System.out.println("4.Khong dung dich vu");
                                System.out.println("chon: ");
                                int chon = sc.nextInt();
                                switch(chon){
                                    case 1:
                                        dv.nhapKara(conn, sc,id);
                                        break;
                                    case 2:
                                        dv.nhapCS(conn, sc,id);
                                        break;
                                    case 3:
                                        dv.nhapTT(conn, sc,id);
                                    default:
                                        break;
                                }
                                System.out.print("Ban co muon nhap them khong(Y/N):");
                                d = sc.next().charAt(0);
                            }while(d=='y'||d=='Y');
                            hd.tongBill(conn,id);
                            conn.commit();
                        }catch(SQLException se){
                            System.err.println(se.getMessage());
                            conn.rollback();
                        }
                        conn.setAutoCommit(true);
                        xuat.xuat(conn, id);
                        break;
                    case 5:
                        System.out.print("Ban muon xuat\n1.theo ma hoa don\n2.tat ca\nChon: ");
                        int i = sc.nextInt();
                        if(i==1){
                            System.out.print("\nNhap ma hoa don can xuat: ");
                            int idhd = sc.nextInt();
                            xuat.xuat(conn, idhd);
                        }else{
                            xuat.xuatAll(conn);
                        }
                        break;
                    case 6:
                        System.out.println("Nhap thang va nam muon xuat doanh thu");
                        System.out.print("==>Nam: ");
                        int nam = sc.nextInt();
                        System.out.print("==>Nhap thang: ");
                        int thang = sc.nextInt();
                        System.out.printf("\nTONG DOANH THU THEO THANG %d NAM %d LA: %,.0f VND",
                        thang,nam, xuat.tinhDTThang(nam, thang, conn));
                        break;
                    case 7:
                        System.out.println("Nhap quy va nam muon xuat doanh thu");
                        System.out.print("==>Nam:");
                        int namQuy = sc.nextInt();
                        System.out.print("==>Nhap quy: ");
                        int quy = sc.nextInt();
                        System.out.printf("\nTONG DOANH THU THEO THANG %d NAM %d LA: %,.0f VND",
                        quy,namQuy, xuat.tinhDTQuy(namQuy, quy, conn));
                        break;
                    case 8:
                    default:    
                        System.out.println("GOODBYE!!!");
                        break;
                }
                
            }while(x<8);
            conn.close();
            
        }catch(SQLException se){
            System.err.println(se.getMessage());
        }
    }
    public static void quanLySanh(Scanner sc, Connection conn) throws SQLException{
        int x;   
        QLSanh qlS = new QLSanh();
        do   {    
            System.out.println("\n --------QUAN LY SANH CUOI---------");        
            System.out.println("|1. THEM SANH CUOI                 |");           
            System.out.println("|2. XOA SANH CUOI                  |");           
            System.out.println("|3. CAP NHAT SANH CUOI             |");
            System.out.println("|4. TRA CUU SANH CUOI              |");
            System.out.println("|5. THOAT                          |");
            System.out.println(" ----------------------------------");            
            System.out.print("======>> ");
            x=sc.nextInt();   
            switch(x){ 
                case 1:
                    SanhCuoi sanh = new SanhCuoi();
                    sanh.nhap(sc);
                    qlS.them(sanh, conn);
                    break;
                case 2:
                    qlS.traCuu(conn).forEach(p->p.hienThi());
                    System.out.println("Nhap ma sanh de xoa");
                    String id = Format.nhapIdSanh(sc);
                    qlS.xoa(id, conn);
                    break;
                case 3:
                    qlS.capNhat(sc, conn);
                    break;
                case 4:
                    sc.nextLine();
                    System.out.println("Nhap ten sanh can tra cuu");
                    String kw = sc.nextLine();
                    qlS.traCuu(kw, conn).forEach(p->p.hienThi());
                    break;
                default:
                    break;
            }
        }while(x<5);
    }
    
    public static void quanLyMenu(Scanner sc, Connection conn) throws SQLException, ParseException{
        QLThucAn qLThucAn = new QLThucAn();
        QLThucUong qLThucUong = new QLThucUong();
        int x;   
        do   {    
            System.out.println("\n ----------QUAN LY MON AN-----------");        
            System.out.println("|1. THEM MON AN                     |");           
            System.out.println("|2. XOA MON AN                      |");           
            System.out.println("|3. CAP NHAT MON AN                 |");
            System.out.println("|4. TRA CUU MON AN                  |");
            System.out.println("|5. THOAT                           |");
            System.out.println(" -----------------------------------");            
            System.out.print("======>> ");
            x=sc.nextInt();   
            switch(x){ 
                case 1:
                    System.out.println("Ban muon them: ");
                    System.out.println("1.Thuc an\n2.ThucUong");
                    System.out.println("Chon: ");
                    x = sc.nextInt();
                    if(x==1){
                        ThucAn mon = new ThucAn();
                        mon.nhap(sc);
                        qLThucAn.them(mon, conn);
                    }else{
                        ThucUong mon = new ThucUong();
                        mon.nhap(sc);
                        qLThucUong.them(mon, conn);
                    }
                    break;
                case 2:
                    System.out.println("Ban muon xoa: ");
                    System.out.println("1.Thuc an\n2.ThucUong");
                    System.out.println("Chon: ");
                    x = sc.nextInt();
                    if(x==1){
                        qLThucAn.traCuu(conn).forEach(p->System.out.println(p));
                        System.out.println("Nhap ma can xoa: ");
                        String i = sc.next();
                        qLThucAn.xoa(i, conn);
                    }else{
                        qLThucUong.traCuu(conn).forEach(p->System.out.println(p));
                        System.out.println("Nhap ma can xoa: ");
                        String i = sc.next();
                        qLThucUong.xoa(i, conn);
                    }
                    
                    break;
                case 3:
                    System.out.println("Ban muon cap nhat: ");
                    System.out.println("1.Thuc an\n2.ThucUong");
                    System.out.println("Chon: ");
                    x = sc.nextInt();
                    if(x==1){
                        qLThucAn.capNhat(sc, conn);
                    }else{
                        qLThucUong.capNhat(sc, conn);
                    }
                    break;
                case 4:
                    System.out.println("Nhap ten mon an can tra cuu");
                    sc.nextLine();
                    String kw = sc.nextLine();
                    qLThucAn.traCuu(kw, conn).forEach(p->System.out.println(p));
                    qLThucUong.traCuu(kw, conn).forEach(p->System.out.println(p));
                    break;
                default:
                    break;
            }
        }while(x<5);
    }
    public static void quanLyDV(Scanner sc, Connection conn) throws SQLException{
        QLKara qLKara = new QLKara();
        QLThueCS qLThueCS = new QLThueCS();
        QLTrangTri qLTrangTri = new QLTrangTri();
        int x;     
        do   {    
            System.out.println("\n ----------QUAN LY DICH VU----------");        
            System.out.println("|1. THEM DICH VU                    |");           
            System.out.println("|2. XOA DICH VU                     |");           
            System.out.println("|3. CAP NHAT DICH VU                |");
            System.out.println("|4. TRA CUU DICH VU                 |");
            System.out.println("|5. THOAT                           |");
            System.out.println(" -----------------------------------");            
            System.out.print("======>> ");
            x=sc.nextInt();   
            switch(x){ 
                case 1:
                    System.out.println("Ban muon them:\n1.Karaoke\n2.Thue ca si\n3.Trang tri\nchon: ");
                    x = sc.nextInt();
                    switch(x){
                        case 1:
                            Karaoke ka = new Karaoke();
                            ka.nhap(sc);
                            qLKara.them(ka, conn);
                            break;
                        case 2:
                            ThueCaSi cs = new ThueCaSi();
                            cs.nhap(sc);
                            qLThueCS.them(cs, conn);
                            break;
                        case 3:
                            TrangTriPhoiCanh tt = new TrangTriPhoiCanh();
                            tt.nhap(sc);
                            qLTrangTri.them(tt, conn);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Ban muon xoa:\n1.Karaoke\n2.Thue ca si\n3.Trang tri\nchon: ");
                    x = sc.nextInt();
                    String id;
                    switch(x){
                        case 1:
                            qLKara.traCuu(conn).forEach(p->System.out.println(p));
                            System.out.println("Nhap ma can xoa: ");
                            id = sc.next();
                            qLKara.xoa(id, conn);
                            break;
                        case 2:
                            qLThueCS.traCuu(conn).forEach(p->System.out.println(p));
                            System.out.println("Nhap ma can xoa: ");
                            id = sc.next();
                            qLThueCS.xoa(id, conn);
                            break;
                        case 3:
                            qLTrangTri.traCuu(conn).forEach(p->System.out.println(p));
                            System.out.println("Nhap ma can xoa: ");
                            id = sc.next();
                            qLTrangTri.xoa(id, conn);
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Ban muon cap nhat:\n1.Karaoke\n2.Thue ca si\n3.Trang tri\nchon: ");
                    x = sc.nextInt();
                    switch(x){
                        case 1:
                            qLKara.capNhat(sc, conn);
                            break;
                        case 2:
                            qLThueCS.capNhat(sc, conn);
                            break;
                        case 3:
                            qLTrangTri.capNhat(sc, conn);
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Nhap ten dich vu ban muon tra cuu: ");
                    sc.nextLine();
                    String ten = sc.nextLine();
                    qLKara.traCuu(ten, conn).forEach(p->System.out.println(p));
                    qLThueCS.traCuu(ten, conn).forEach(p->System.out.println(p));
                    qLTrangTri.traCuu(ten, conn).forEach(p->System.out.println(p));
                    break;
                default:
                    break;
            }
        }while(x<5);
    }
}
