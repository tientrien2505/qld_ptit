/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import home.model.GiaoVu;
import home.model.News;
import home.model.SubContent;

/**
 *
 * @author TruongDao
 */
public class GiaoVuDAO {
    private Connection conn = null;
    private Statement stm = null;
    private PreparedStatement  pstm = null;
    private String database = "";
    private String user = "";
    private String pass = "";        

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public GiaoVuDAO(String database, String user, String pass) {
        this.database = database;
        this.user = user;
        this.pass = pass;        
//        String connectionUrl = "jdbc:sqlserver://TRUONGDAO-PC\\KHANGHOA:1433;"
//                        + "database="+database+";"
//                        + "user="+user+";"
//                        + "password="+pass+";"
////                        + "encrypt=true;integratedSecurity=true;"
////                        + "trustServerCertificate=false;"
////                        + "hostNameInCertificate=*.database.windows.net;"
//                        + "loginTimeout=30;";
        String connectionUrl = "jdbc:sqlserver://localhost;Instance=.\\TRUONGDAO:1433;databaseName="+database+";user="+user+";password="+pass;
        try{
            System.out.println("start");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           this.conn = DriverManager.getConnection(connectionUrl); 
            System.out.println("end");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void insert(ArrayList<GiaoVu> listGiaoVu){
        if (listGiaoVu == null)
            return;
        try {
            checkExist(listGiaoVu);
            for (GiaoVu giaoVu : listGiaoVu){
                String query = "INSERT INTO dbo.giaovu (link,time,title) values"+
                "(?,?,?)";  
                pstm = conn.prepareStatement(query);
                String link = giaoVu.getLink();
                pstm.setString(1, link);
                String time = giaoVu.getTime();
                pstm.setString(2, time);
                String title = giaoVu.getTitle();
                pstm.setString(3, title);
                pstm.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void checkExist(ArrayList<GiaoVu> listGiaoVu){
        try {
            ArrayList<GiaoVu> list = listGiaoVu;
            String query = "SELECT link FROM dbo.giaovu";
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if (rs == null)
                return ;
            while(rs.next()){
                String link = rs.getNString("link");
                for (int i = 0; i < listGiaoVu.size(); i++){                    
                    if (listGiaoVu.get(i).getLink().equals(link)){
                        list.remove(i);
                        break;
                    }
                }
            }
            listGiaoVu = list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public ArrayList<GiaoVu> select(int offset, int n){
        ArrayList<GiaoVu> listGiaoVu = new ArrayList<GiaoVu>();
        int sl = n+offset;
        try {
            String query = "SELECT TOP "+sl+" * FROM dbo.giaovu ORDER BY id DESC";
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            int i = 0;
            while(rs.next()){
                if (i < offset){
                    i++;
                    continue;
                }
                GiaoVu giaoVu = new GiaoVu();
                giaoVu.setLink(rs.getNString("link"));
                giaoVu.setTime(rs.getNString("time"));
                giaoVu.setTitle(rs.getNString("title"));
                listGiaoVu.add(giaoVu);
            }
            return listGiaoVu;
        } catch (SQLException ex) {
            ex.printStackTrace();   
        }
        return listGiaoVu;
    }
}
