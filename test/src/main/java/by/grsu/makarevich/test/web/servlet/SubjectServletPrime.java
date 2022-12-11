package by.grsu.makarevich.test.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.makarevich.test.db.dao.IDao;
import by.grsu.makarevich.test.db.dao.impl.SubjectDaoImpl;
import by.grsu.makarevich.test.db.model.Subject;

public class SubjectServletPrime extends HttpServlet{
    private static final IDao<Integer, Subject> SubjectDao = SubjectDaoImpl.INSTANCE;

    @Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String paramId = req.getParameter("id");

		Integer SubjectId = Integer.parseInt(paramId); // read request parameter
		Subject SubjectById = SubjectDao.getById(SubjectId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (SubjectById == null) {
			pw.println("no brand by id=" + SubjectId);
		} else {
			pw.println(SubjectById.toString());
		}

		pw.println("</body></html>");
		pw.close();// closing the stream
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();// get the stream to write the data
		pw.println("<html><body>");
		try {
			String paramName = req.getParameter("name");
			Long paramCreated = Long.parseLong(req.getParameter("created"));
			Long paramUpdated = Long.parseLong(req.getParameter("updated"));
			Subject SubjectEntity = new Subject();
			SubjectEntity.setName(paramName);
			SubjectEntity.setCreated(new Timestamp(paramCreated));
			SubjectEntity.setUpdated(new Timestamp(paramUpdated));
			SubjectDao.insert(SubjectEntity);
			pw.println("Saved:" + SubjectEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}

