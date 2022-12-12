package by.grsu.makarevich.test.db.dao.impl;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.grsu.makarevich.test.db.dao.AbstractDao;
import by.grsu.makarevich.test.db.model.Test1;
import by.grsu.makarevich.test.db.dao.IDao;
import by.grsu.makarevich.test.web.dto.TableStateDto;
import by.grsu.makarevich.test.web.dto.SortDto;

public class TestDaoImpl extends AbstractDao implements IDao<Integer, Test1>
{
	public static final TestDaoImpl INSTANCE = new TestDaoImpl();

	private TestDaoImpl()
	{
		super();
	}

    @Override
    public void insert(Test1 entity) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("insert into test(name, subject_id, status, created, updated) values(?,?,?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2,entity.getSubjectId());
			pstmt.setBoolean(3, entity.getStatus());
			pstmt.setTimestamp(4, entity.getCreated());
			pstmt.setTimestamp(5, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "test"));
		} catch (SQLException e) 
        {
			throw new RuntimeException("can't insert Test entity", e);
		}
    }

    @Override
    public void update(Test1 entity) 
    {
        try (Connection c = createConnection()) 
        {
			PreparedStatement pstmt = c.prepareStatement("update test set name=?, subject_id=?, status=?, updated=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2, entity.getSubjectId());
			pstmt.setBoolean(3, entity.getStatus());
			pstmt.setTimestamp(4, entity.getUpdated());
			pstmt.setInt(5, entity.getId());
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
    public Test1 getById(Integer id) 
    {
        Test1 entity = null;
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
			throw new RuntimeException("can't get Test entity by id", e);
		}

		return entity;
    }

    @Override
    public List<Test1> getAll() 
    {
        List<Test1> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) 
        {
			ResultSet rs = c.createStatement().executeQuery("select * from test");
			while (rs.next()) 
            {
				Test1 entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		}
        catch (SQLException e) 
        {
			throw new RuntimeException("can't select Test entities", e);
		}

		return entitiesList;
    }

    private Test1 rowToEntity(ResultSet res) throws SQLException 
    {
		Test1 entity = new Test1();
		entity.setId(res.getInt("id"));
		entity.setName(res.getString("name"));
		entity.setSubjectId(res.getInt("subject_id"));
		entity.setStatus(res.getBoolean("status"));
		entity.setCreated(res.getTimestamp("created"));
		entity.setUpdated(res.getTimestamp("updated"));
		return entity;
	}

	@Override
	public List<Test1> find(TableStateDto tableStateDto)
	{
		List<Test1> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			StringBuilder sql = new StringBuilder("select * from test");

			final SortDto sortDto = tableStateDto.getSort();
			if (sortDto != null) {
				sql.append(String.format(" order by %s %s", sortDto.getColumn(), resolveSortOrder(sortDto)));
			}

			sql.append(" limit " + tableStateDto.getItemsPerPage());
			sql.append(" offset " + resolveOffset(tableStateDto));

			System.out.println("searching Tests using SQL: " + sql);
			ResultSet rs = c.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Test1 entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Test entities", e);
		}
		return entitiesList;
	}

	@Override
	public int count()
	{
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select count(*) as c from test");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("c");
		} catch (SQLException e) {
			throw new RuntimeException("can't get test count", e);
		}
	}
    
}
