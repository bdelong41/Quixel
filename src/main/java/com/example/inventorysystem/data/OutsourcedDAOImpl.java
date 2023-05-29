package com.example.inventorysystem.data;

import com.example.inventorysystem.data.interfaces.InHouseDAO;
import com.example.inventorysystem.data.interfaces.OutsourcedDAO;
import com.example.inventorysystem.models.InHouse;
import com.example.inventorysystem.models.Outsourced;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutsourcedDAOImpl implements OutsourcedDAO {
    private static Connection con;
    @Override
    public Outsourced get(Integer id) {
        Outsourced outsourced = null;
        con = DataConnection.getConnection();//verifying connection is active
        if(con == null) return null;
        try {
            Statement st = con.createStatement();
            String query = "select * from Part where ID = '" + id + "';";
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                outsourced = new Outsourced(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getInt("Stock"),
                        rs.getInt("Min"),
                        rs.getInt("Max"),
                        rs.getString("Company_Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return outsourced;
    }

    @Override
    public List<Outsourced> getAll() {
        Outsourced outsourced = null;
        con = DataConnection.getConnection();//verifying connection is active
        List<Outsourced> ls = new ArrayList<Outsourced>();
        if(con == null) return ls;

        try {
            Statement st = con.createStatement();
            String query = "select * from Part where Machine_ID is NULL;";
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                outsourced = new Outsourced(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getInt("Stock"),
                        rs.getInt("Min"),
                        rs.getInt("Max"),
                        rs.getString("Company_Name"));
                ls.add(outsourced);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

    @Override
    public Outsourced get(String Name, String passwd) {
        return null;
    }

    @Override
    public int create() {
        con = DataConnection.getConnection();
        if(con == null) return -1;
        int id = -1;
        try {
            Statement st = con.createStatement();
            String query = "insert into Part(Name, Price, Stock, Min, Max, Company_Name) values('', 0,0,0,0,'')";

            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            //setting contact id to random contact until the appointment has been updated
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean update(Outsourced t) {
        con = DataConnection.getConnection();
        if(con == null) return false;
        Integer rs = 0;
        try {
            String query = "update Part " +
                    "set Name = ?, " +
                    "Price = ?, " +
                    "Stock = ?, " +
                    "Min = ?, " +
                    "Max = ?, " +
                    "Machine_ID = NULL, " +
                    "Company_Name = ? " +
                    "where ID = ? ;";

            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, t.getName());
            st.setDouble(2, t.getPrice());
            st.setInt(3, t.getStock());
            st.setInt(4, t.getMin());
            st.setInt(5, t.getMax());
            st.setString(6, t.getCompanyName());
            st.setInt(7, t.getID());


            rs = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(rs > 0){
            return true;
        }
        return false;

    }

    @Override
    public boolean delete(Outsourced t) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        con = DataConnection.getConnection();//verifying connection is active
        if(con == null) return false;
        int rs = 0;
        try {
            Statement st = con.createStatement();
            String query = "delete from Part where ID = '" + id + "';";

            rs = st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(rs > 0) return true;
        return false;
    }
}
