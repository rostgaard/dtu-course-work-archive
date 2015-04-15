package eao.model;

/**
 * 
 * @author s113444(Luai), s124255(Nicolai K), s124259(Jesper)
 *
 */
public class Query {
	
	final protected static String queryGetApp = 
			"SELECT a " +
			"FROM AppEntity a " +
			"WHERE " + 
			  "a.mac = :mac " +
			"AND " +
			 "a.eventType =  :eventType ";
	
	final protected static String queryGetApplist = 
			"SELECT a " +
			"FROM AppEntity a " +
			"WHERE " + 
			  "a.mac = :mac ";
	
	
	final protected static String queryGetDevice = 
			"SELECT a " +
			"FROM DeviceEntity a " +
			"WHERE " +
				"a.mac = :mac ";
	
	final protected static String querytextGetAll = "SELECT e FROM EventEntity e";
	final protected static String querytextGetAllApps = "SELECT s FROM AppEntity s";
	final protected static String querytextGetAllDeviceNames = "SELECT q FROM DeviceEntity q";
	
	final protected static String querytext =
			"SELECT e " +
			"FROM EventEntity e, AppEntity s " +
			"WHERE " + 
			  "s.id = :id " +
			"AND " +
			  "e.sensor = s " +
			"AND " +
			  "e.time >  :time " +
			"ORDER BY e.time ASC";
		
	final protected static String querytext2 = 
			"SELECT e " +
			"FROM EventEntity e, AppEntity s " +
			"WHERE " + 
			  "s.id = :id " +
			"AND " +
			 "e.appEntity = s " +
			"AND " +
			  "s.eventType =  :eventType ";	
	
	final protected static String getPolicyByName = 
			"SELECT p " +
			"FROM PolicyEntity p " +
			"WHERE " + 
			  "p.name = :name ";
	
	final protected static String getAllPolicies = "SELECT p FROM PolicyEntity p";
	final protected static String getAllRuleStrings = "SELECT r FROM RuleStringEntity r";
	
	final protected static String getEventsInTimespan =
			"SELECT e " +
			"FROM EventEntity e " +
			"WHERE " + 
			  "e.time >  :time " +
			"ORDER BY e.time ASC";
			;
}
