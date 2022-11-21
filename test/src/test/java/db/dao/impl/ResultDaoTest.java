package db.dao.impl;

import java.sql.Timestamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.makarevich.test.db.dao.IDao;
import by.grsu.makarevich.test.db.dao.impl.ResultDaoImpl;
import by.grsu.makarevich.test.db.model.Result;

public class ResultDaoTest extends AbstractDaoTest
{
    private static final IDao<Integer, Result> dao = ResultDaoImpl.INSTANCE;

    @Test
	public void testInsert() 
	{
		Result entity = new Result();
		entity.setUserId(1);
        entity.setTestId(1);
        entity.setDate(new Timestamp(123));
        entity.setMark(5.4);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() 
	{
		Result entity = new Result();
		entity.setUserId(1);
		entity.setTestId(1);
		entity.setDate(new Timestamp(123));
        entity.setMark(5.4);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		Integer temp = 4;
		entity.setUserId(temp);
		entity.setUpdated(getCurrentTime());
		dao.update(entity);

		Result updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( temp, updatedEntity.getId());
		Assertions.assertNotEquals(updatedEntity.getUpdated(), updatedEntity.getCreated());
	}

	@Test
	public void testDelete() 
	{
		Result entity = new Result();
		entity.setUserId(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() 
	{
		Result entity = new Result();
		entity.setUserId(1);
        entity.setTestId(1);
        entity.setDate(new Timestamp(123));
        entity.setMark(5.4);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		Result selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getId(), selectedEntity.getId());
		Assertions.assertEquals(entity.getCreated(), selectedEntity.getCreated());
		Assertions.assertEquals(entity.getUpdated(), selectedEntity.getUpdated());
	}

	@Test
	public void testGetAll() 
	{
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) 
		{
			Result entity = new Result();
			entity.setUserId(1);
        	entity.setTestId(1);
        	entity.setDate(new Timestamp(123));
        	entity.setMark(5.4);
			entity.setCreated(getCurrentTime());
			entity.setUpdated(getCurrentTime());
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}
