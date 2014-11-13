package web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dto.model.User;
import eao.model.Conversion;
import eao.model.UserEAO;
import entity.model.UserEntity;
import enums.Role;


@LocalBean
@Stateless
@Path("/users")
public class UserWebService {

	@EJB UserEAO eao;
	
	@GET
	@Path("/addUser")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public User addUser(@QueryParam("userName") String userName, @QueryParam("email") String email, 
			@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName, @QueryParam("role") Role role,
			@QueryParam("password") String password) {

		UserEntity userEntity = eao.addUser(userName, email, firstName, lastName, role, password);
		
		System.out.println("" + userEntity.getUserName());

		return Conversion.convertUserEntity(userEntity);
	}
	
	
	@GET
	@Path("/getUsers")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUserList() {
		
		List<UserEntity> userEntities = new ArrayList<UserEntity>();
		List<User> users = new ArrayList<User>();
		userEntities = eao.getAllUsers();
		users = Conversion.convertUserEntityList(userEntities);
		return users;
	}
	
	@GET
	@Path("/getUserByUserName")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByUserName(@QueryParam("userName") String userName){
		UserEntity userEntity = eao.getUserByUserName(userName);
		
		return Conversion.convertUserEntity(userEntity);
		
	}
	
	
}
