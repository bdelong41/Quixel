package com.example.inventorysystem.data;

import java.io.IOError;
import java.io.IOException;
import java.sql.*;
public class DataConnection {
    private static String url;
    private static String uname;
    private static String passwd;
    private static Connection con;

    //connection string vars
    private static String serverName;

    private static String port;
    private static String databaseName;



    /**
     * Creates static database connection upon object creation.
     */
    static
    {
        url = "jdbc:mysql://" + serverName + ":" + port + "/" + databaseName +"?connectionTimeZone=UTC";

        try {
            con = DriverManager.getConnection(url, uname, passwd);
        } catch (Exception e) {
            System.out.println("Failed to connect");
            e.printStackTrace();
        }

    }
    /**
     * Static function.
     * checks if connection is still active.
     * if the connection is closed then it creates a new connection.
     * @return Connection.
     */
    public static Connection getConnection() {
        //construct connection string
        url = "jdbc:mysql://" + serverName + ":" + port + "/" + databaseName +"?connectionTimeZone=UTC";
        try {
            if (con.isClosed() || con == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, uname, passwd);
            }
            return con;
        }catch (SQLException | ClassNotFoundException | NullPointerException e){
            e.printStackTrace();
        }
        return con;
    }
    public static boolean closeConnection(){
        if(con == null) {
            return true;
        }

        try{
            if(con.isClosed()){
                return true;
            }
            else{
                con.close();
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static String getDBName(){
        String name = null;
        //close current connection
        closeConnection();
        //create new connection
        getConnection();
        if(con == null) return null;
        try {
            Statement st = con.createStatement();
            String query = "Select database();";
            ResultSet rs = st.executeQuery(query);
            if(rs.next());
            {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        closeConnection();
        return name;
    }
    public static void setUname(String Username){
        uname = Username;
    }
    public static void setPasswd(String password){
        passwd = password;
    }

    public static void setServerName(String serverName) {
        DataConnection.serverName = serverName;
    }

    public static void setPort(String port) {
        DataConnection.port = port;
    }

    public static void setDatabaseName(String databaseName) {
        DataConnection.databaseName = databaseName;
    }

    public static String getUname() {
        return uname;
    }

    public static String getPasswd() {
        return passwd;
    }

    public static Connection getCon() {
        return con;
    }

    public static String getServerName() {
        return serverName;
    }

    public static String getPort() {
        return port;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static Connection checkConnection(){
        try{
            if(con != null){
                if(!con.isClosed()){
                    con.close();
                }
            }
            url = "jdbc:mysql://" + serverName + ":" + port + "/" + databaseName +"?connectionTimeZone=UTC";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, uname, passwd);
        }catch(Exception e){
            System.out.println("Failed to connect");
            e.printStackTrace();
        }
        return con;
    }
}
