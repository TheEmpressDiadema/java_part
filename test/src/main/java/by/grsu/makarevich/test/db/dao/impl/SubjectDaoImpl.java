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

public class SubjectDaoImpl extends AbstractDao implements IDao<Integer, Subject>
{
    public static final SubjectDaoImpl INSTANCE = new SubjectDaoImpl();

    private SubjectDaoImpl()
    {
        super();
    }

    @Override
    public void insert(Subject entity) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("insert into subject(name, created, updated) values(?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setTimestamp(2, entity.getCreated());
			pstmt.setTimestamp(3, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "subject"));
		} catch (SQLException e) 
        {
			throw new RuntimeException("can't insert Subject entity", e);
		}
    }

    @Override
    public void update(Subject entity) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("update subject set name=?, updated=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setTimestamp(2, entity.getUpdated());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't update Subject entity", e);
		}    
    }

    @Override
    public void delete(Integer id) 
    {    
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("delete from subject where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't delete Subject entity", e);
		}
    }

    

    @Override
    public Subject getById(Integer id) 
    {
        Subject entity = null;
		try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("select * from subject where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) 
            {
				entity = rowToEntity(rs);
			}
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't get Subject entity by id", e);
		}

		return entity;
    }

    @Override
    public List<Subject> getAll() 
    {
        List<Subject> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) 
        {
			ResultSet rs = c.createStatement().executeQuery("select * from subject");
			while (rs.next()) 
            {
				Subject entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		}
        catch (SQLException e) 
        {
			throw new RuntimeException("can't select Subject entities", e);
		}

		return entitiesList;
    }
    
    private Subject rowToEntity(ResultSet res) throws SQLException 
    {
		Subject entity = new Subject();
		entity.setId(res.getInt("id"));
		entity.setName(res.getString("name"));
		entity.setCreated(res.getTimestamp("created"));
		entity.setUpdated(res.getTimestamp("updated"));
		return entity;
	}
}
