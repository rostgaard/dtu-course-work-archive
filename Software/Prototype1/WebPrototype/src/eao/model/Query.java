package eao.model;

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
	
	final protected static String querytextGetAll = "SELECT e FROM EventEntity e";
	final protected static String querytextGetAllApps = "SELECT s FROM AppEntity s";
	
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

}
