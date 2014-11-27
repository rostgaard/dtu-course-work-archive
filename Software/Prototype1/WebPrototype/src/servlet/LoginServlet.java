package servlet;

import java.io.IOException;
import javax.ejb.EJB;
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
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	UserEAO eao;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	// handling log in logic
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getParameter("login") != null){
			String inputUserName = request.getParameter("username");
			String inputPassword = request.getParameter("password");

			try{
				UserEntity userEntity = eao.getUserByUserName(inputUserName);
				User user = Conversion.convertUserEntity(userEntity);
				
				if(inputPassword.equals(user.getPassword())){
					HttpSession session = request.getSession();
					
					userEntity.setLastLogin(session.getCreationTime());
					eao.update(userEntity);
					
					session.setAttribute("user", user);
					response.sendRedirect("dashboard.jsp");
				}
				else{
					response.sendRedirect("login.jsp");
				}

			}
			catch(IllegalArgumentException | NullPointerException e){
				response.sendRedirect("login.jsp");
			}
		}
	}
}