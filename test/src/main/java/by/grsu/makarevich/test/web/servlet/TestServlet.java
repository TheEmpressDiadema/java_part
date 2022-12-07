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
import by.grsu.makarevich.test.db.dao.impl.SubjectDaoImpl;
import by.grsu.makarevich.test.db.dao.impl.TestDaoImpl;
import by.grsu.makarevich.test.db.model.Test1;
import by.grsu.makarevich.test.web.dto.TestDto;
import by.grsu.makarevich.test.db.model.Subject;
import by.grsu.makarevich.test.web.dto.SubjectDto;


public class TestServlet extends HttpServlet
{
    private static final IDao<Integer, Test1> testDao = TestDaoImpl.INSTANCE;
    private static final IDao<Integer, Subject> subjectDao = SubjectDaoImpl.INSTANCE;

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
		List<Test1> tests = testDao.getAll(); // get data

		List<TestDto> dtos = tests.stream().map((entity) -> {
			TestDto dto = new TestDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setStatus(entity.getStatus());
			dto.setCreated(entity.getCreated());
			dto.setUpdated(entity.getUpdated());

			Subject brand = subjectDao.getById(entity.getSubjectId());
			dto.setSubjectName(brand.getName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("test-list.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String modelIdStr = req.getParameter("id");
		TestDto dto = new TestDto();
		if (!Strings.isNullOrEmpty(modelIdStr)) {
			Integer modelId = Integer.parseInt(modelIdStr);
			Test1 entity = testDao.getById(modelId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setStatus(entity.getStatus());
			dto.setSubjectId(entity.getSubjectId());
		}
		req.setAttribute("dto", dto);
		req.setAttribute("allSubjects", getAllSubjectsDtos());
		req.getRequestDispatcher("test-edit.jsp").forward(req, res);
	}

	private List<SubjectDto> getAllSubjectsDtos() {
		return subjectDao.getAll().stream().map((entity) -> {
			SubjectDto dto = new SubjectDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Test1 test = new Test1();
		String testIdStr = req.getParameter("id");
		String subjectIdStr = req.getParameter("subjectId");
		test.setName(req.getParameter("name"));
		test.setStatus(Boolean.parseBoolean(req.getParameter("status")));
		test.setSubjectId(subjectIdStr == null ? null : Integer.parseInt(subjectIdStr));
		test.setUpdated(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(testIdStr)) {
			test.setCreated(new Timestamp(new Date().getTime()));
			testDao.insert(test);
		} else {
			test.setId(Integer.parseInt(testIdStr));
			testDao.update(test);
		}
		res.sendRedirect("/test");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		testDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
