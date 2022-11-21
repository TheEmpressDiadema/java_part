package db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.makarevich.test.db.dao.IDao;
import by.grsu.makarevich.test.db.dao.impl.SubjectDaoImpl;
import by.grsu.makarevich.test.db.dao.impl.TestDaoImpl;
import by.grsu.makarevich.test.db.model.Test1;
import by.grsu.makarevich.test.db.model.Subject;

public class TestDaoTest extends AbstractDaoTest
{
    private static final IDao<Integer, Test1> dao = TestDaoImpl.INSTANCE;
    private static final IDao<Integer, Subject> subjectDao = SubjectDaoImpl.INSTANCE;

    @Test
	public void testInsert() 
	{
		Test1 entity = new Test1();
		entity.setName("RandomName");
        entity.setStatus(false);
        entity.setSubjectId(saveSubject().getId());
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() 
	{
		Test1 entity = new Test1();
        entity.setName("RandomName");
        entity.setStatus(false);
        entity.setSubjectId(saveSubject().getId());
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		String temp = "Temp";
		entity.setName(temp);
		entity.setUpdated(getCurrentTime());
		dao.update(entity);

		Test1 updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( temp, updatedEntity.getName());
		Assertions.assertNotEquals(updatedEntity.getUpdated(), updatedEntity.getCreated());
	}

	@Test
	public void testDelete() 
	{
		Test1 entity = new Test1();
		entity.setName("RandomName");
        entity.setStatus(false);
        entity.setSubjectId(saveSubject().getId());
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() 
	{
		Test1 entity = new Test1();
		entity.setName("RandomName");
        entity.setStatus(false);
        entity.setSubjectId(saveSubject().getId());
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		Test1 selectedEntity = dao.getById(entity.getId());

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
			Test1 entity = new Test1();
			entity.setName("RandomName");
            entity.setStatus(false);
            entity.setSubjectId(saveSubject().getId());
			entity.setCreated(getCurrentTime());
			entity.setUpdated(getCurrentTime());
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
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
}
