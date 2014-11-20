package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.model.User;
import eao.model.Conversion;
import eao.model.UserEAO;
import entity.model.UserEntity;

/**
 * 
 * @author s124259
 *
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	UserEAO eao;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getParameter("login") != null){
			String userName = request.getParameter("username");
			String password = request.getParameter("password");

			System.out.println(userName + " " + password);
			try{
				UserEntity userEntity = eao.getUserByUserName(userName);
				User user = Conversion.convertUserEntity(userEntity);
				if(password.equals(user.getPassword())){

					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					response.sendRedirect("dashboard.jsp");
				}
				else{
					response.sendRedirect("default.jsp");
				}

			}
			catch(IllegalArgumentException | NullPointerException e){
				response.sendRedirect("default.jsp");
			}
		}
	}
}