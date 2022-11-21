package db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.makarevich.test.db.dao.IDao;
import by.grsu.makarevich.test.db.dao.impl.UserDaoImpl;
import by.grsu.makarevich.test.db.model.User;

public class UserDaoTest extends AbstractDaoTest
{
    private static final IDao<Integer, User> dao = UserDaoImpl.INSTANCE;

    @Test
	public void testInsert() 
    {
		User entity = new User();
		entity.setName("RandomName");
		entity.setSecondName("RandomSecondName");
		entity.setPatronimyc("RandomPatronimyc");
        entity.setRoleId(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() 
    {
		User entity = new User();
		entity.setName("RandomName");
        entity.setSecondName("RandomSecondName");
		entity.setPatronimyc("RandomPatronimyc");
        entity.setRoleId(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		String temp = "Temp";
		entity.setName(temp);
		entity.setUpdated(getCurrentTime());
		dao.update(entity);

		User updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( temp, updatedEntity.getId());
		Assertions.assertNotEquals(updatedEntity.getUpdated(), updatedEntity.getCreated());
	}

	@Test
	public void testDelete() 
	{
		User entity = new User();
		entity.setName("RandomName");
        entity.setSecondName("RandomSecondName");
		entity.setPatronimyc("RandomPatronimyc");
        entity.setRoleId(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() 
    {
		User entity = new User();
		entity.setName("RandomName");
        entity.setSecondName("RandomSecondName");
		entity.setPatronimyc("RandomPatronimyc");
        entity.setRoleId(1);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		User selectedEntity = dao.getById(entity.getId());

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
			User entity = new User();
			entity.setName("RandomName");
            entity.setSecondName("RandomSecondName");
		    entity.setPatronimyc("RandomPatronimyc");
            entity.setRoleId(1);
			entity.setCreated(getCurrentTime());
			entity.setUpdated(getCurrentTime());
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}
