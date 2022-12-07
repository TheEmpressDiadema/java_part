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
import by.grsu.makarevich.test.db.dao.impl.UserDaoImpl;
import by.grsu.makarevich.test.db.model.User;
import by.grsu.makarevich.test.web.dto.UserDto;
import by.grsu.makarevich.test.db.model.Role;

public class UserServlet extends HttpServlet
{
    private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;
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
		List<User> users = userDao.getAll(); // get data

		List<UserDto> dtos = users.stream().map((entity) -> {
			UserDto dto = new UserDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setSecondName(entity.getSecondName());
			dto.setPatronimyc(entity.getPatronimyc());
			dto.setCreated(entity.getCreated());
			dto.setUpdated(entity.getUpdated());

			Role role = roleDao.getById(entity.getRoleId());
			dto.setRoleName(role.getName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("user-list.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userIdStr = req.getParameter("id");
		UserDto dto = new UserDto();
		if (!Strings.isNullOrEmpty(userIdStr)) {
			Integer userId = Integer.parseInt(userIdStr);
			User entity = userDao.getById(userId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setSecondName(entity.getSecondName());
			dto.setPatronimyc(entity.getPatronimyc());
			dto.setRoleId(entity.getRoleId());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("user-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		User user = new User();
		String userIdStr = req.getParameter("id");
		String roleIdStr = req.getParameter("roleId");
		user.setName(req.getParameter("name"));
		user.setSecondName(req.getParameter("secondName"));
		user.setPatronimyc(req.getParameter("patronimyc"));
		user.setRoleId(roleIdStr == null ? null : Integer.parseInt(roleIdStr));
		user.setUpdated(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(userIdStr)) {
			user.setCreated(new Timestamp(new Date().getTime()));
			userDao.insert(user);
		} else {
			user.setId(Integer.parseInt(userIdStr));
			userDao.update(user);
		}
		res.sendRedirect("/user");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		userDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
