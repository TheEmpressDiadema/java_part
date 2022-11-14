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
    public void insert(Subject entity) 
    {
        try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into subject(name, second_name, patronimyc, role_id, created, updated) values(?,?,?,?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getSecondName());
			pstmt.setString(3,entity.getPatronimyc());
			pstmt.setInt(4, entity.getRoleId());
			pstmt.setTimestamp(5, entity.getCreated());
			pstmt.setTimestamp(6, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "Subject"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Subject entity", e);
		}
    }

    @Override
    public void update(Subject entity) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("update Subject set name=?, second_name=?, patronimyc=?, role_id=?, created=?, updated=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2,entity.getSecondName());
			pstmt.setString(3, entity.getPatronimyc());
			pstmt.setInt(4, entity.getRoleId());
			pstmt.setTimestamp(5, entity.getCreated());
			pstmt.setTimestamp(6, entity.getUpdated());
			pstmt.executeUpdate();
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't update Test entity", e);
		}
    }

    @Override
    public void delete(Integer id) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("delete from Subject where id=?");
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
			PreparedStatement pstmt = c.prepareStatement("select * from Subject where id=?");
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
			ResultSet rs = c.createStatement().executeQuery("select * from Subject");
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
		entity.setSecondName(res.getString("second_name"));
		entity.setPatronimyc(res.getString("patronimyc"));
		entity.setRoleId(res.getInt("role_id"));
		entity.setCreated(res.getTimestamp("created"));
		entity.setUpdated(res.getTimestamp("updated"));
		return entity;
	}
    
    
}
