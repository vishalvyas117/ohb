package com.ohb.app.payload;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBCResponsePayLoad {

	private ResPayLoad payLoad = new ResPayLoad();

	private List<Map<String, String>> rows = new ArrayList<Map<String, String>>(0);

	public ResPayLoad getPayLoad() {
		return payLoad;
	}

	public List<Map<String, String>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, String>> rows) {
		this.rows = rows;
	}
}
