package com.example.inventorysystem.data;

import com.example.inventorysystem.data.interfaces.InHouseDAO;
import com.example.inventorysystem.models.InHouse;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public record InHouseDAOImpl() implements InHouseDAO {
    private static Connection con;
    @Override
    public InHouse get(Integer id) {
        InHouse inHouse = null;
        con = DataConnection.getConnection();//verifying connection is active
        if(con == null) return null;
        try {
            Statement st = con.createStatement();
            String query = "select * from Part where ID = '" + id + "';";
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                inHouse = new InHouse(rs.getInt("ID"),
                rs.getString("Name"),
                rs.getDouble("Price"),
                rs.getInt("Stock"),
                rs.getInt("Min"),
                rs.getInt("Max"),
                rs.getInt("Machine_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inHouse;
    }

    @Override
    public List<InHouse> getAll() {
        InHouse inHouse = null;
        con = DataConnection.getConnection();//verifying connection is active
        List<InHouse> ls = new ArrayList<InHouse>();
        if(con == null) return ls;
        try {
            Statement st = con.createStatement();
            String query = "select * from Part where Company_Name is NULL;";
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                inHouse = new InHouse(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getInt("Stock"),
                        rs.getInt("Min"),
                        rs.getInt("Max"),
                        rs.getInt("Machine_ID"));
                ls.add(inHouse);
            }
        } catch (SQLException| NullPointerException e) {
            e.printStackTrace();
        }
        return ls;
    }

    @Override
    public InHouse get(String Name, String passwd) {
        return null;
    }

    @Override
    public int create() {
        con = DataConnection.getConnection();
        if(con == null) return -1;
        int id = -1;
        try {
            Statement st = con.createStatement();
            String query = "insert into Part(Name, Price, Stock, Min, Max, Machine_ID) values('', 0,0,0,0,0)";

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
    public boolean update(InHouse t) {
        Integer rs = 0;
        con = DataConnection.getConnection();
        if(con == null) return false;
        try {
            String query = "update Part " +
                    "set Name = ?, " +
                    "Price = ?, " +
                    "Stock = ?, " +
                    "Min = ?, " +
                    "Max = ?, " +
                    "Machine_ID = ?, " +
                    "Company_Name = NULL " +
                    "where ID = ? ;";

            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, t.getName());
            st.setDouble(2, t.getPrice());
            st.setInt(3, t.getStock());
            st.setInt(4, t.getMin());
            st.setInt(5, t.getMax());
            st.setInt(6, t.getMachineID());
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
    public boolean delete(InHouse t) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        int rs = 0;
        con = DataConnection.getConnection();
        if(con == null) return false;
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
