package com.example.inventorysystem.data;

import com.example.inventorysystem.data.interfaces.ProductDAO;
import com.example.inventorysystem.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private static Connection con;
    @Override
    public Product get(Integer id) {
        Product product = null;
        Connection con = DataConnection.getConnection();
        if(con == null) return null;
        try {
            Statement st = con.createStatement();
            String query = "select * from Product where ID = '" + id + "';";
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                product = new Product(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getInt("Stock"),
                        rs.getInt("Min"),
                        rs.getInt("Max"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        Product product = null;
        List<Product> ls = new ArrayList<Product>();
        Connection con = DataConnection.getConnection();
        if(con == null) return ls;
        try {
            Statement st = con.createStatement();
            String query = "select * from Product;";
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                product = new Product(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getInt("Stock"),
                        rs.getInt("Min"),
                        rs.getInt("Max"));
                ls.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

    @Override
    public Product get(String Name, String passwd) {
        return null;
    }

    @Override
    public int create() {
        Connection con = DataConnection.getConnection();
        if(con == null) return -1;

        int id = -1;
        try {
            Statement st = con.createStatement();
            String query = "insert into Product(Name, Price, Stock, Min, Max) values('', 0,0,0,0)";

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
    public boolean update(Product t) {
        Integer rs = 0;
        con = DataConnection.getConnection();
        if(con == null) return false;
        try {
            String query = "update Product " +
                    "set Name = ?, " +
                    "Price = ?, " +
                    "Stock = ?, " +
                    "Min = ?, " +
                    "Max = ? " +
                    "where ID = ? ;";

            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, t.getName());
            st.setDouble(2, t.getPrice());
            st.setInt(3, t.getStock());
            st.setInt(4, t.getMin());
            st.setInt(5, t.getMax());
            st.setInt(6, t.getId());


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
    public boolean delete(Product t) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        int rs = 0;
        Connection con = DataConnection.getConnection();
        AssociationsDAOImpl associationsDAO = new AssociationsDAOImpl();
        if(con == null) return false;

        try {
            associationsDAO.deleteAssoc(id);//removing foreign key constraint
            Statement st = con.createStatement();
            String query = "delete from Product where ID = '" + id + "';";

            rs = st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(rs > 0) return true;
        return false;
    }
}
