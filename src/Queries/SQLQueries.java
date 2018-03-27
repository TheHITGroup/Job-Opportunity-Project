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

	public String getSqlForMostPopularOfTwoTechnologiesByZip(String techOne, String techTwo, String zip)
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
				"        (L.zipcode ='"  + zip + "'" +
				"            AND U.t_id = T.id" + 
				"            AND U.j_id = J.id" + 
				"            AND J.zipcode = L.zipcode" + 
				"            AND name = '" + techOne + "')" + 
				"            OR (name = '" + techTwo + "' AND L.zipcode = '" + zip + "'" + 
				"            AND U.t_id = T.id" + 
				"            AND U.j_id = J.id" + 
				"            AND J.zipcode = L.zipcode)" + 
				"    GROUP BY name" + 
				"    ORDER BY COUNT(name) DESC) A" + 
				"    GROUP BY name" + 
				"    LIMIT 1) B" + 
				"    WHERE" + 
				"    (L.zipcode = '" + zip + "'" + 
				"        AND U.t_id = T.id" + 
				"        AND U.j_id = J.id" + 
				"        AND J.zipcode = L.zipcode" + 
				"        AND name = '" + techOne + "')" + 
				"        OR (name = '" + techTwo + "' AND L.zipcode = '" + zip + "'" +
				"        AND U.t_id = T.id" + 
				"        AND U.j_id = J.id" + 
				"        AND J.zipcode = L.zipcode)" + 
				"    GROUP BY max_name_count , name" + 
				"    HAVING max_name_count = COUNT(T.name)";
		
		return sql;
	}
	
	public String getSQLForMostPopularOfTwoCityStatesForTechnology(String tech, 
			String cityOne, String stateOne, String cityTwo, String stateTwo)
	{
		String sql = "SELECT " + 
				"    COUNT(L.city), max_city_count as count, L.city, L.state" + 
				"    FROM" + 
				"    Location AS L," + 
				"    Technology AS T," + 
				"    Job AS J," + 
				"    Uses AS U," + 
				"    (SELECT " + 
				"        MAX(city_count1) AS max_city_count" + 
				"    FROM" + 
				"        (SELECT " + 
				"        COUNT(city) AS city_count1, L.city" + 
				"    FROM" + 
				"        Location AS L, Technology AS T, Job AS J, Uses AS U" + 
				"    WHERE" + 
				"        (T.name = '" + tech + "' AND U.t_id = T.id" + 
				"            AND U.j_id = J.id" + 
				"            AND J.zipcode = L.zipcode" + 
				"            AND L.city = '" + cityOne + "'" + 
				"            AND L.state = '" + stateOne + "')" + 
				"            OR (name = '" + tech + "' AND U.t_id = T.id" + 
				"            AND U.j_id = J.id" + 
				"            AND J.zipcode = L.zipcode" + 
				"            AND city = '" + cityTwo + "'" + 
				"            AND state = '" + stateTwo + "')" + 
				"    GROUP BY city" + 
				"    ORDER BY COUNT(name) DESC) A" + 
				"    GROUP BY city" + 
				"    LIMIT 1) B" + 
				"    WHERE" + 
				"    (T.name = '" + tech + "' AND U.t_id = T.id" + 
				"        AND U.j_id = J.id" + 
				"        AND J.zipcode = L.zipcode" + 
				"        AND L.city = '" + cityOne + "'" + 
				"        AND L.state = '" + stateOne + "')" + 
				"        OR (name = '" + tech + "' AND U.t_id = T.id" + 
				"        AND U.j_id = J.id" + 
				"        AND J.zipcode = L.zipcode" + 
				"        AND city = '" + cityTwo + "'" + 
				"        AND state = '" + stateTwo + "')" + 
				"    GROUP BY max_city_count , city" + 
				"    HAVING max_city_count = COUNT(L.city)";
		return sql;
	}

}
