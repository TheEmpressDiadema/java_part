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
import by.grsu.makarevich.test.db.model.Subject;
import by.grsu.makarevich.test.web.dto.SubjectDto;

public class SubjectServlet extends HttpServlet
{
    private static IDao<Integer, Subject> subjectDao = SubjectDaoImpl.INSTANCE;

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
		List<Subject> subject = subjectDao.getAll(); // get data

		List<SubjectDto> dtos = subject.stream().map((entity) -> {
			SubjectDto dto = new SubjectDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setCreated(entity.getCreated());
			dto.setUpdated(entity.getUpdated());

			// build data for complex fields
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("subject-list.jsp").forward(req, res); // delegate request processing to JSP
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String subjectIdStr = req.getParameter("id");
		SubjectDto dto = new SubjectDto();
		if (!Strings.isNullOrEmpty(subjectIdStr)) {
			// object edit
			Integer roleId = Integer.parseInt(subjectIdStr);
			Subject entity = subjectDao.getById(roleId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("subject-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Subject subject = new Subject();
		String subjectIdStr = req.getParameter("id");

		subject.setName(req.getParameter("name"));
		subject.setUpdated(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(subjectIdStr)) {
			// new entity
			subject.setCreated(new Timestamp(new Date().getTime()));
			subjectDao.insert(subject);
		} else {
			// updated entity
			subject.setId(Integer.parseInt(subjectIdStr));
			subjectDao.update(subject);
		}
		res.sendRedirect("/subject"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		subjectDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
