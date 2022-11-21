package by.grsu.makarevich.test.db.dao.impl;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.grsu.makarevich.test.db.dao.AbstractDao;
import by.grsu.makarevich.test.db.model.User;
import by.grsu.makarevich.test.db.dao.IDao;

public class UserDaoImpl extends AbstractDao implements IDao<Integer, User>
{
    public static final UserDaoImpl INSTANCE = new UserDaoImpl();

    private UserDaoImpl()
    {
        super();
    }
	
    @Override
    public void insert(User entity) 
    {
        try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into user(name, second_name, patronimyc, role_id, created, updated) values(?,?,?,?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getSecondName());
			pstmt.setString(3,entity.getPatronimyc());
			pstmt.setInt(4, entity.getRoleId());
			pstmt.setTimestamp(5, entity.getCreated());
			pstmt.setTimestamp(6, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "user"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert User entity", e);
		}
    }

    @Override
    public void update(User entity) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("update user set name=?, second_name=?, patronimyc=?, role_id=?, created=?, updated=? where id=?");
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
			throw new RuntimeException("can't update User entity", e);
		}
    }

    @Override
    public void delete(Integer id) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("delete from user where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't delete User entity", e);
		}
    }

    @Override
    public User getById(Integer id) 
    {
        User entity = null;
		try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("select * from user where id=?");
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
    public List<User> getAll() 
    {
        List<User> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) 
        {
			ResultSet rs = c.createStatement().executeQuery("select * from user");
			while (rs.next()) 
			{
				User entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		}
        catch (SQLException e) 
        {
			throw new RuntimeException("can't select User entities", e);
		}

		return entitiesList;
    }

    private User rowToEntity(ResultSet res) throws SQLException 
    {
		User entity = new User();
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
