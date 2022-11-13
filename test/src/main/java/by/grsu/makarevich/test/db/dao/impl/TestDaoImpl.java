package by.grsu.makarevich.test.db.dao.impl;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.grsu.makarevich.test.db.dao.AbstractDao;
import by.grsu.makarevich.test.db.model.Test;
import by.grsu.makarevich.test.db.dao.IDao;

public class TestDaoImpl extends AbstractDao implements IDao<Integer, Test>
{

    @Override
    public void insert(Test entity) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("insert into test(name, created, updated) values(?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setTimestamp(2, entity.getCreated());
			pstmt.setTimestamp(3, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "test"));
		} catch (SQLException e) 
        {
			throw new RuntimeException("can't insert Test entity", e);
		}
    }

    @Override
    public void update(Test entity) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("update test set name=?, updated=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setTimestamp(2, entity.getUpdated());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't update test entity", e);
		}
    }

    @Override
    public void delete(Integer id) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("delete from test where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't delete Test entity", e);
		}
    }

    @Override
    public Test getById(Integer id) 
    {
        Test entity = null;
		try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("select * from test where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) 
            {
				entity = rowToEntity(rs);
			}
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't get User entity by id", e);
		}

		return entity;
    }

    @Override
    public List<Test> getAll() 
    {
        List<Test> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) 
        {
			ResultSet rs = c.createStatement().executeQuery("select * from brand");
			while (rs.next()) 
            {
				Test entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		}
        catch (SQLException e) 
        {
			throw new RuntimeException("can't select Brand entities", e);
		}

		return entitiesList;
    }

    private Test rowToEntity(ResultSet res) throws SQLException 
    {
		Test entity = new Test();
		entity.setId(res.getInt("id"));
		entity.setName(res.getString("name"));
		entity.setCreated(res.getTimestamp("created"));
		entity.setUpdated(res.getTimestamp("updated"));
		return entity;
	}
    
}
