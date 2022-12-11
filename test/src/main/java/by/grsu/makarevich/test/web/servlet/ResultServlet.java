package by.grsu.makarevich.test.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Strings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.makarevich.test.db.dao.IDao;
import by.grsu.makarevich.test.db.dao.impl.ResultDaoImpl;
import by.grsu.makarevich.test.db.dao.impl.UserDaoImpl;
import by.grsu.makarevich.test.db.dao.impl.TestDaoImpl;
import by.grsu.makarevich.test.db.model.User;
import by.grsu.makarevich.test.web.dto.TestDto;
import by.grsu.makarevich.test.web.dto.UserDto;
import by.grsu.makarevich.test.db.model.Result;
import by.grsu.makarevich.test.web.dto.ResultDto;
import by.grsu.makarevich.test.db.model.Test1;

public class ResultServlet extends HttpServlet
{
    private static final IDao<Integer, Result> resultDao = ResultDaoImpl.INSTANCE;
    private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;
    private static final IDao<Integer, Test1> testDao = TestDaoImpl.INSTANCE;

    @Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		String viewParam = req.getParameter("view");
		if ("edit".equals(viewParam)) {
			handleEditView(req, res);
		} else {
			handleListView(req, res);
		}
	}

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Result> results = resultDao.getAll(); // get data

		List<ResultDto> dtos = results.stream().map((entity) -> {
			ResultDto dto = new ResultDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setDate(entity.getDate());
            dto.setMark(entity.getMark());

			// build data for complex fields
			User user = userDao.getById(entity.getUserId());
			dto.setUserName(user.getName());

			Test1 test = testDao.getById(entity.getTestId());
			dto.setTestName(test.getName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("result-list.jsp").forward(req, res); // delegate request processing to JSP
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String resultIdStr = req.getParameter("id");
		ResultDto dto = new ResultDto();
		if (!Strings.isNullOrEmpty(resultIdStr)) {
			// object edit
			Integer resultId = Integer.parseInt(resultIdStr);
			Result entity = resultDao.getById(resultId);
			dto.setId(entity.getId());
            dto.setDate(entity.getDate());
            dto.setMark(entity.getMark());
			dto.setUserId(entity.getUserId());
			dto.setTestId(entity.getTestId());
			dto.setCreated(entity.getCreated());
			dto.setUpdated(entity.getUpdated());
		}
		req.setAttribute("dto", dto);
		req.setAttribute("allTests", getAllTestsDtos());
		req.setAttribute("allUsers", getAllUsersDtos());
		req.getRequestDispatcher("result-edit.jsp").forward(req, res);
	}

	private List<TestDto> getAllTestsDtos() {
		return testDao.getAll().stream().map((entity) -> {
			TestDto dto = new TestDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());
	}

	private List<UserDto> getAllUsersDtos() {
		return userDao.getAll().stream().map((entity) -> {
			UserDto dto = new UserDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setSecondName(entity.getSecondName());
			dto.setPatronimyc(entity.getPatronimyc());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Result result = new Result();
		String resultIdStr = req.getParameter("id");
		String userIdStr = req.getParameter("userId");
		String testIdStr = req.getParameter("testId");

		result.setMark(Double.parseDouble(req.getParameter("mark")));
        result.setDate(Timestamp.valueOf(req.getParameter("date") + ":00.000"));
		result.setUserId(userIdStr == null ? null : Integer.parseInt(userIdStr));
		result.setTestId(testIdStr == null ? null : Integer.parseInt(testIdStr));
		result.setUpdated(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(resultIdStr)) {
			// new entity
			result.setCreated(new Timestamp(new Date().getTime()));
			resultDao.insert(result);
		} else {
			// updated entity
			result.setId(Integer.parseInt(resultIdStr));
			resultDao.update(result);
		}
		res.sendRedirect("/result"); // will send 302 back to client and client will execute GET /result
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		resultDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
