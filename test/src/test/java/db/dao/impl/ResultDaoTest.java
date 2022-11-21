package db.dao.impl;

import java.sql.Timestamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.makarevich.test.db.dao.IDao;
import by.grsu.makarevich.test.db.dao.impl.ResultDaoImpl;
import by.grsu.makarevich.test.db.dao.impl.UserDaoImpl;
import by.grsu.makarevich.test.db.dao.impl.TestDaoImpl;
import by.grsu.makarevich.test.db.dao.impl.RoleDaoImpl;
import by.grsu.makarevich.test.db.dao.impl.SubjectDaoImpl;
import by.grsu.makarevich.test.db.model.Result;
import by.grsu.makarevich.test.db.model.User;
import by.grsu.makarevich.test.db.model.Test1;
import by.grsu.makarevich.test.db.model.Role;
import by.grsu.makarevich.test.db.model.Subject;

public class ResultDaoTest extends AbstractDaoTest
{
    private static final IDao<Integer, Result> dao = ResultDaoImpl.INSTANCE;
	private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;
	private static final IDao<Integer, Test1> testDao = TestDaoImpl.INSTANCE;
	private static final IDao<Integer, Role> roleDao = RoleDaoImpl.INSTANCE;
	private static final IDao<Integer, Subject> subjectDao = SubjectDaoImpl.INSTANCE;

    @Test
	public void testInsert() 
	{
		Result entity = new Result();
		entity.setUserId(saveUser().getId());
        entity.setTestId(saveTest().getId());
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
		entity.setUserId(saveUser().getId());
        entity.setTestId(saveTest().getId());
		entity.setDate(new Timestamp(123));
        entity.setMark(5.4);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		Double temp = 4.4;
		entity.setMark(temp);
		entity.setUpdated(getCurrentTime());
		dao.update(entity);

		Result updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( temp, updatedEntity.getMark());
		Assertions.assertNotEquals(updatedEntity.getUpdated(), updatedEntity.getCreated());
	}

	@Test
	public void testDelete() 
	{
		Result entity = new Result();
		entity.setUserId(saveUser().getId());
        entity.setTestId(saveTest().getId());
		entity.setMark(5.4);
		entity.setDate(getCurrentTime());
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
		entity.setUserId(saveUser().getId());
        entity.setTestId(saveTest().getId());
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
			entity.setUserId(saveUser().getId());
        	entity.setTestId(saveTest().getId());
        	entity.setDate(new Timestamp(123));
        	entity.setMark(5.4);
			entity.setCreated(getCurrentTime());
			entity.setUpdated(getCurrentTime());
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}

	private User saveUser() 
	{
		User entity = new User();
		entity.setName("Matesha");
		entity.setSecondName("Mateshevna");
		entity.setPatronimyc("TrashMode");
		entity.setRoleId(saveRole().getId());
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		userDao.insert(entity);
		return entity;
	}
	private Role saveRole() 
	{
		Role entity = new Role();
		entity.setName("Matesha");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		roleDao.insert(entity);
		return entity;
	}
	private Subject saveSubject() 
	{
		Subject entity = new Subject();
		entity.setName("Matesha");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		subjectDao.insert(entity);
		return entity;
	}

	private Test1 saveTest() 
	{
		Test1 entity = new Test1();
		entity.setName("Matesha");
		entity.setSubjectId(saveSubject().getId());
		entity.setStatus(false);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		testDao.insert(entity);
		return entity;
	}
}
