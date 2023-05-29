package com.example.inventorysystem.data;

import com.example.inventorysystem.data.interfaces.AssociationsDAO;
import com.example.inventorysystem.models.Part;
import com.example.inventorysystem.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssociationsDAOImpl implements AssociationsDAO {
    @Override
    public Integer get(Integer id) {
        return null;
    }

    @Override
    public List<Integer> getAll() {
        return null;
    }

    //returns all associated parts
    public List<Integer> getAssoc(int productID){
        Integer partID = null;
        List<Integer> ls = new ArrayList<Integer>();
        Connection con = DataConnection.getConnection();
        if(con == null) return ls;
        try {
            Statement st = con.createStatement();
            String query = "select Part_ID from Associations where Product_ID = "+ productID + ";";
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                ls.add(rs.getInt("Part_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

    @Override
    public Integer get(String Name, String passwd) {
        return null;
    }

    @Override
    public int create() {
        Connection con = DataConnection.getConnection();
        if(con == null) return -1;

        int id = -1;
        try {
            Statement st = con.createStatement();
            String query = "insert into Associations(Product_ID, Part_ID) values(null, null)";

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

    public boolean updateAssoc(int productID, int partID, int assocID){
        Integer rs = 0;
        Connection con = DataConnection.getConnection();
        if(con == null) return false;
        try {
            String query = "update Associations " +
                    "set Product_ID = ?, " +
                    "Part_ID = ? " +
                    "where ID = ? ;";

            PreparedStatement st = con.prepareStatement(query);

            st.setInt(1, productID);
            st.setInt(2, partID);
            st.setInt(3, assocID);
            rs = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(rs > 0){
            return true;
        }
        return false;
    }
    public boolean updateAllAssoc(Product product, int assocID){
        Integer rs = 0;
        Connection con = DataConnection.getConnection();
        if(con == null) return false;
        for(Part part: product.getAllAssociatedParts()) {
            int partID = part.getID();
            try {
                String query = "update Associations " +
                        "set Product_ID = ?, " +
                        "Part_ID = ?, " +
                        "where ID = ? ;";

                PreparedStatement st = con.prepareStatement(query);

                st.setInt(1, product.getId());
                st.setInt(2, partID);
                st.setInt(3, assocID);
                rs = st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (rs == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(Integer t) {
        return false;
    }

    @Override
    public boolean delete(Integer t) {
        return false;
    }

    public boolean deleteAssoc(Integer productID, Integer partID){
        Integer rs = 0;
        Connection con = DataConnection.getConnection();
        if(con == null) return false;
        try {
            String query = "delete Associations " +
                    "where Product_ID = ?, " +
                    " and Part_ID = ?;";

            PreparedStatement st = con.prepareStatement(query);

            st.setInt(1, productID);
            st.setInt(2, partID);
            rs = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rs == 0) {
            return false;
        }
        return true;
    }
    public boolean deleteAssoc(Integer productID){
        Integer rs = 0;
        Connection con = DataConnection.getConnection();
        if(con == null) return false;
        try {
            String query = "delete from Associations " +
                    "where Product_ID = ?;";

            PreparedStatement st = con.prepareStatement(query);

            st.setInt(1, productID);
            rs = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rs == 0) {
            return false;
        }
        return true;
    }
}
