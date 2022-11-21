package by.grsu.makarevich.test.db.dao.impl;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.grsu.makarevich.test.db.dao.AbstractDao;
import by.grsu.makarevich.test.db.model.Result;
import by.grsu.makarevich.test.db.dao.IDao;

public class ResultDaoImpl extends AbstractDao implements IDao<Integer, Result>
{
    public static final ResultDaoImpl INSTANCE = new ResultDaoImpl();

    private ResultDaoImpl()
    {
        super();
    }

    @Override
    public void insert(Result entity) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("insert into result(user_id, test_id, date, mark, created, updated) values(?,?,?,?,?,?)");
			pstmt.setInt(1, entity.getUserId());
            pstmt.setInt(2, entity.getTestId());
            pstmt.setTimestamp(3, entity.getDate());
            pstmt.setDouble(4, entity.getMark());
			pstmt.setTimestamp(5, entity.getCreated());
			pstmt.setTimestamp(6, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "result"));
		} catch (SQLException e) 
        {
			throw new RuntimeException("can't insert Result entity", e);
		}
    }

    @Override
    public void update(Result entity) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("update result set user_id=?, test_id created=?, date=?, mark=?, created=?, updated=? where id=?");
			pstmt.setInt(1, entity.getUserId());
            pstmt.setInt(2, entity.getTestId());
            pstmt.setTimestamp(3, entity.getDate());
            pstmt.setDouble(4, entity.getMark());
			pstmt.setTimestamp(2, entity.getCreated());
			pstmt.setTimestamp(3, entity.getUpdated());
			pstmt.executeUpdate();
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't update Result entity", e);
		}
    }

    @Override
    public void delete(Integer id) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("delete from result where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't delete Result entity", e);
		}    
    }

    @Override
    public Result getById(Integer id) 
    {
        Result entity = null;
		try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("select * from result where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) 
            {
				entity = rowToEntity(rs);
			}
		} 
        catch (SQLException e) 
        {
			throw new RuntimeException("can't get Result entity by id", e);
		}

		return entity;
    }

    @Override
    public List<Result> getAll() 
    {
        List<Result> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) 
        {
			ResultSet rs = c.createStatement().executeQuery("select * from result");
			while (rs.next()) 
            {
				Result entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		}
        catch (SQLException e) 
        {
			throw new RuntimeException("can't select Result entities", e);
		}

		return entitiesList;
    }
    
    private Result rowToEntity(ResultSet res) throws SQLException 
    {
		Result entity = new Result();
		entity.setId(res.getInt("id"));
        entity.setUserId(res.getInt("user_id"));
        entity.setTestId(res.getInt("test_id"));
        entity.setDate(res.getTimestamp("date"));
        entity.setMark(res.getDouble("mark"));
		entity.setCreated(res.getTimestamp("created"));
		entity.setUpdated(res.getTimestamp("updated"));
		return entity;
	}
}
