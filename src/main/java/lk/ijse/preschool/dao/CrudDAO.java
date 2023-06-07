package lk.ijse.preschool.dao;

import lk.ijse.preschool.entity.SuperEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T extends SuperEntity> extends SuperDAO{
    public ArrayList<T> getAll()throws SQLException, ClassNotFoundException;

    public boolean add(T dto)throws SQLException, ClassNotFoundException;

    public boolean update(T dto)throws SQLException, ClassNotFoundException;

    public boolean exist(String id)throws SQLException, ClassNotFoundException;

    public String  generateNewId()throws SQLException, ClassNotFoundException;

    public boolean  delete(String id)throws SQLException, ClassNotFoundException;

    public T  search(String id)throws SQLException, ClassNotFoundException;
}
