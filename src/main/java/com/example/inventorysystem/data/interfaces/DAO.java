package com.example.inventorysystem.data.interfaces;

import java.util.List;

public interface DAO<dataType> {
    dataType get(Integer id);
    List<dataType> getAll();

    dataType get(String Name, String passwd);

    int create();//wrapping customer and user information
    boolean update(dataType t);
    boolean delete(dataType t);
    boolean delete(Integer id);
}
