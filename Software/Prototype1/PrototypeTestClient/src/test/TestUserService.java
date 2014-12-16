package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest;
import service.Role;
import service.User;

/**
 * 
 * @author Luai s113444
 *
 */
public class TestUserService {

	String username = "Test";
	String email = "email";
	String firstname = "firstname";
	String lastname = "lastname";
	Role role = Role.MANAGER;
	String password = "Password";

	@Before
	public void setUp() throws Exception {
		SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.users().deleteUser().delete(username);
	}
	
	@After
	public void tearDown() throws Exception {
		SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.users().deleteUser().delete(username);
	}
	
	@Test
	public void userTest() {
		
		User addUser = SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.users().addUser().getAsUser(username, email, firstname, lastname, Role.MANAGER.toString(), password);
		
		assertTrue(checkUser(addUser));
		
		User getUser = SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.users().getUserByUserName().getAsUser(username);
		
		assertTrue(checkUser(getUser));
		assertEquals(addUser.getLastLogin(), getUser.getLastLogin());
		
		String lastLogin = SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.users().getLastLoginByUserName().getAsJson(username, String.class);
		assertEquals("" + getUser.getLastLogin(), lastLogin);
		
		// Add the same user
		try {
			SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.users().addUser().getAsUser(username, email, firstname, lastname, Role.MANAGER.toString(), password);
			fail();
		} catch (Exception e) {
			assertEquals("500 Internal Server Error", e.getMessage());
		}
		
	}
	
	private boolean checkUser(User user) {
		return user.getUserName().equals(username) && user.getEmail().equals(email) && user.getFirstName().equals(firstname)
				&& user.getLastName().equals(lastname) && user.getRole().equals(role) && user.getPassword().equals(password);
	}

}
