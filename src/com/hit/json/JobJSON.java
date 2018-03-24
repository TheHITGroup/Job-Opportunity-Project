package com.hit.json;

import java.sql.Timestamp;

public class JobJSON
{
	private int id;
	
	private Timestamp datetime;
	
	private int u_id;
	
	private int zipcode;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Timestamp getDatetime()
	{
		return datetime;
	}

	public void setDatetime(Timestamp datetime)
	{
		this.datetime = datetime;
	}

	public int getU_id()
	{
		return u_id;
	}

	public void setU_id(int u_id)
	{
		this.u_id = u_id;
	}

	public int getZipcode()
	{
		return zipcode;
	}

	public void setZipcode(int zipcode)
	{
		this.zipcode = zipcode;
	}
}
