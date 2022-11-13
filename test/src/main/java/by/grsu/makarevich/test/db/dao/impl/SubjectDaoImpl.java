package by.grsu.makarevich.test.db.dao.impl;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.grsu.makarevich.test.db.dao.AbstractDao;
import by.grsu.makarevich.test.db.model.Subject;
import by.grsu.makarevich.test.db.dao.IDao;

public class SubjectDaoImpl extends Subject implements IDao<Integer, Subject>
{

    @Override
    public void insert(Subject t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Subject t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Subject getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Subject> getAll() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
