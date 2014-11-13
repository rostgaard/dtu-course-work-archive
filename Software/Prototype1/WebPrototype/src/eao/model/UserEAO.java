package eao.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dto.model.User;
import entity.model.UserEntity;
import enums.Role;

@LocalBean
@Stateless
public class UserEAO {

	@PersistenceContext(unitName = "Prototype1")
	EntityManager em;


	public UserEntity addUser(String userName, String email, String firstName,
			String lastName, Role role, String password){

		UserEntity user = new UserEntity();

		user.setUserName(userName);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setRole(role);
		user.setPassword(password);

		em.persist(user);
		em.flush();
		return user;
	}


	public UserEntity getUserByUserName(String userName){
		UserEntity user = em.find(UserEntity.class, userName);
		return user;
	}


	final private static String querytextGetAll = "SELECT e FROM UserEntity e";
	public List<UserEntity> getAllUsers(){
		TypedQuery<UserEntity> query = em.createQuery(querytextGetAll, UserEntity.class);	
		List<UserEntity> result = query.getResultList();

		if (result != null) {
			return result;
		} else {
			return Collections.emptyList();
		}
	}



}
