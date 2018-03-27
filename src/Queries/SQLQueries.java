package Queries;

public class SQLQueries
{

	public String getSqlForMostPopularOfTwoTechnologies(String techOne, String techTwo, String city, String state)
	{
		
		String sql = "SELECT " + 
				"    COUNT(T.name) as count, max_name_count, name" + 
				"    FROM" + 
				"    Location AS L," + 
				"    Technology AS T," + 
				"    Job AS J," + 
				"    Uses AS U," + 
				"    (SELECT " + 
				"        MAX(name_count1) AS max_name_count" + 
				"    FROM" + 
				"        (SELECT " + 
				"        COUNT(name) AS name_count1, T.name" + 
				"    FROM" + 
				"        Location AS L, Technology AS T, Job AS J, Uses AS U" + 
				"    WHERE" + 
				"        (city ='"  + city + "' AND state ='" + state + "'" +
				"            AND U.t_id = T.id" + 
				"            AND U.j_id = J.id" + 
				"            AND J.zipcode = L.zipcode" + 
				"            AND name = '" + techOne + "')" + 
				"            OR (name = '" + techTwo + "' AND city = '" + city + "'" + 
				"            AND state = '" + state + "'" + 
				"            AND U.t_id = T.id" + 
				"            AND U.j_id = J.id" + 
				"            AND J.zipcode = L.zipcode)" + 
				"    GROUP BY name" + 
				"    ORDER BY COUNT(name) DESC) A" + 
				"    GROUP BY name" + 
				"    LIMIT 1) B" + 
				"    WHERE" + 
				"    (city = '" + city + "' AND state = '" + state + "'" + 
				"        AND U.t_id = T.id" + 
				"        AND U.j_id = J.id" + 
				"        AND J.zipcode = L.zipcode" + 
				"        AND name = '" + techOne + "')" + 
				"        OR (name = '" + techTwo + "' AND city = '" + city + "'" + 
				"        AND state = '" + state + "'" + 
				"        AND U.t_id = T.id" + 
				"        AND U.j_id = J.id" + 
				"        AND J.zipcode = L.zipcode)" + 
				"    GROUP BY max_name_count , name" + 
				"    HAVING max_name_count = COUNT(T.name)";
		
		return sql;
	}

}
