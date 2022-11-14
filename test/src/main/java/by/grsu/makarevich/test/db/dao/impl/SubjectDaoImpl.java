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
    private static final SubjectDaoImpl INSTANCE = new SubjectDaoImpl();

    private SubjectDaoImpl()
    {
        super();
    }

    @Override
    public void insert(Subject t) 
    {
        
    }

    @Override
    public void update(Subject t) {
        
        
    }

    @Override
    public void delete(Integer id) {
        
        
    }

    @Override
    public Subject getById(Integer id) {
        
        return null;
    }

    @Override
    public List<Subject> getAll() {
        
        return null;
    }
    
}
