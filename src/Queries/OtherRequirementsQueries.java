package Queries;

public class OtherRequirementsQueries
{
	public String getSQLForAddJob(int zipcode, String techOne, String techTwo, int userId)
	{
		String sql = "BEGIN" + 
				"    INSERT INTO Job (u_id, zipcode) VALUES(" + userId + ", " + zipcode + ")" + 
				"    INSERT INTO Uses (j_id, t_id) SELECT LAST_INSERT_ID(), " + techOne + "" + 
				"    INSERT INTO Uses (j_id, t_id) SELECT LAST_INSERT_ID(), " + techTwo +
				"    COMMIT";
		
		return sql;
	}
}
