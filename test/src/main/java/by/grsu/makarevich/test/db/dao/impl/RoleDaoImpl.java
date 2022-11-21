package by.grsu.makarevich.test.db.dao.impl;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.grsu.makarevich.test.db.dao.AbstractDao;
import by.grsu.makarevich.test.db.model.Role;
import by.grsu.makarevich.test.db.dao.IDao;

public class RoleDaoImpl extends AbstractDao implements IDao<Integer, Role>
{
    public static final RoleDaoImpl INSTANCE = new RoleDaoImpl();

    private RoleDaoImpl()
    {
        super();
    }

    @Override
    public void insert(Role entity) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("insert into role_object(name, created, updated) values(?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setTimestamp(2, entity.getCreated());
			pstmt.setTimestamp(3, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "role_object"));
		} catch (SQLException e) 
        {
			throw new RuntimeException("can't insert Role entity", e);
		}
    }
    
    @Override
    public void update(Role entity) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("update role_object set name=?,  updated=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setTimestamp(2, entity.getUpdated());
			pstmt.setInt(3, entity.getId());

			pstmt.executeUpdate();
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't update Role entity", e);
		}    
    }
    @Override
    public void delete(Integer id) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("delete from role_object where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't delete Role entity", e);
		}        
    }

    @Override
    public List<Role> getAll() 
    {
        List<Role> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) 
        {
			ResultSet rs = c.createStatement().executeQuery("select * from role_object");
			while (rs.next()) 
            {
				Role entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		}
        catch (SQLException e) 
        {
			throw new RuntimeException("can't select Role entities", e);
		}

		return entitiesList;
    }

    @Override
    public Role getById(Integer id) 
    {
        Role entity = null;
		try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("select * from role_object where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) 
            {
				entity = rowToEntity(rs);
			}
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't get Role entity by id", e);
		}

		return entity;
    }

    private Role rowToEntity(ResultSet res) throws SQLException 
    {
		Role entity = new Role();
		entity.setId(res.getInt("id"));
		entity.setName(res.getString("name"));
		entity.setCreated(res.getTimestamp("created"));
		entity.setUpdated(res.getTimestamp("updated"));
		return entity;
	}
}
