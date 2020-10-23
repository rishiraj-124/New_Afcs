package com.example.afcs.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GateMasterRowMapper implements RowMapper<GateMaster> {

	
	public GateMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
		GateMaster gateMaster = new GateMaster();
		gateMaster.setId(rs.getLong("id"));
		gateMaster.setGateType(rs.getString("gate_type"));
		gateMaster.setGateDesc(rs.getString("gate_desc"));
		return gateMaster;
	}

}
