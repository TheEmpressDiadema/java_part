package by.grsu.makarevich.test.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.makarevich.test.db.dao.IDao;
import by.grsu.makarevich.test.db.dao.impl.RoleDaoImpl;
import by.grsu.makarevich.test.db.model.Role;
import by.grsu.makarevich.test.web.ValidationUtils;

public class RoleServlet extends HttpServlet{
    private static final IDao<Integer, Role> roleDao = RoleDaoImpl.INSTANCE;

    @Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String paramId = req.getParameter("id");

		// validation
		if (!ValidationUtils.isInteger(paramId)) {
			res.sendError(400); // send HTTP status 400 and close response
			return;
		}

		Integer roleId = Integer.parseInt(paramId); // read request parameter
		Role roleById = roleDao.getById(roleId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (roleById == null) {
			pw.println("no brand by id=" + roleId);
		} else {
			pw.println(roleById.toString());
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
			Role roleEntity = new Role();
			roleEntity.setName(paramName);
			roleEntity.setCreated(new Timestamp(paramCreated));
			roleEntity.setUpdated(new Timestamp(paramUpdated));
			roleDao.insert(roleEntity);
			pw.println("Saved:" + roleEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}
