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
import by.grsu.makarevich.test.db.dao.impl.RoleDaoImpl;
import by.grsu.makarevich.test.db.model.Role;
import by.grsu.makarevich.test.web.dto.RoleDto;

public class RoleServlet extends HttpServlet
{
    private static final IDao<Integer, Role> roleDao = RoleDaoImpl.INSTANCE;

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
		List<Role> role = roleDao.getAll(); // get data

		List<RoleDto> dtos = role.stream().map((entity) -> {
			RoleDto dto = new RoleDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setName(entity.getName());

			// build data for complex fields
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("role-list.jsp").forward(req, res); // delegate request processing to JSP
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String roleIdStr = req.getParameter("id");
		RoleDto dto = new RoleDto();
		if (!Strings.isNullOrEmpty(roleIdStr)) {
			// object edit
			Integer roleId = Integer.parseInt(roleIdStr);
			Role entity = roleDao.getById(roleId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("role-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Role role = new Role();
		String roleIdStr = req.getParameter("id");

		role.setName(req.getParameter("name"));
		role.setUpdated(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(roleIdStr)) {
			// new entity
			role.setCreated(new Timestamp(new Date().getTime()));
			roleDao.insert(role);
		} else {
			// updated entity
			role.setId(Integer.parseInt(roleIdStr));
			roleDao.update(role);
		}
		res.sendRedirect("/role"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		roleDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
