package by.grsu.makarevich.test.web.context;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.grsu.makarevich.test.db.dao.impl.*;
import by.grsu.makarevich.test.db.dao.IDao;
import by.grsu.makarevich.test.db.dao.AbstractDao;
import by.grsu.makarevich.test.db.model.*;


public class AppStartupListener implements ServletContextListener
{
    private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;
    private static final IDao<Integer, Result> resultDao = ResultDaoImpl.INSTANCE;
    private static final IDao<Integer, Subject> subjectDao = SubjectDaoImpl.INSTANCE;
    private static final IDao<Integer, Test1> testDao = TestDaoImpl.INSTANCE;
    private static final IDao<Integer, Role> roleDao = RoleDaoImpl.INSTANCE;

    private static final String DB_NAME = "production-db";

    private void initDb() throws SQLException {
		AbstractDao.init(DB_NAME);
		if (!AbstractDao.isDbExist()) {
			System.out.println(String.format("DB '%s' doesn't exist and will be created", DB_NAME));
			AbstractDao.createDbSchema();
			loadInitialData();
		} else {
			System.out.println(String.format("DB '%s' exists", DB_NAME));
		}
	}

    private void loadInitialData() {
		Role rolEntity = new Role();
		rolEntity.setName("RandomName");
		rolEntity.setCreated(getCurrentTime());
		rolEntity.setUpdated(getCurrentTime());
	    roleDao.insert(rolEntity);
		System.out.println("created: " + rolEntity);

		Subject subjectEntity = new Subject();
		subjectEntity.setName("RandomName");
		subjectEntity.setCreated(getCurrentTime());
		subjectEntity.setUpdated(getCurrentTime());
		subjectDao.insert(subjectEntity);
		System.out.println("created: " + subjectEntity);

		Test1 testEntity = new Test1();
		testEntity.setName("RandomName");
        testEntity.setStatus(false);
        testEntity.setSubjectId(subjectEntity.getId());
		testEntity.setCreated(getCurrentTime());
		testEntity.setUpdated(getCurrentTime());
		testDao.insert(testEntity);
		System.out.println("created: " + testEntity);

		User userEntity = new User();
		userEntity.setName("RandomName");
		userEntity.setSecondName("RandomSecondName");
		userEntity.setPatronimyc("RandomPatronimyc");
        userEntity.setRoleId(rolEntity.getId());
		userEntity.setCreated(getCurrentTime());
		userEntity.setUpdated(getCurrentTime());
		userDao.insert(userEntity);
		System.out.println("created: " + userEntity);

        Result resultEntity = new Result();
		resultEntity.setUserId(userEntity.getId());
        resultEntity.setTestId(testEntity.getId());
        resultEntity.setDate(new Timestamp(123));
        resultEntity.setMark(5.4);
		resultEntity.setCreated(getCurrentTime());
		resultEntity.setUpdated(getCurrentTime());
		resultDao.insert(resultEntity);

		System.out.println("initial data created");
	}

    private Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

    @Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
		try {
			initDb();
		} catch (SQLException e) {
			throw new RuntimeException("can't init DB", e);
		}
	}

    @Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}
}
