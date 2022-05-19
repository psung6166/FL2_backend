package com.snk.fl_solution_v02.object;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class HMap extends LinkedHashMap<String, Object>{

	private static final long serialVersionUID = 1L;
	
	public HMap() {
		super();
	}

	public HMap(LinkedHashMap<String, Object> data) {
		putAll(data);
	}
	
	/* comment : HMap 에서 String 으로 값추출 */
	public String getString(String key) {

		if (key == null) {
			return "";
		}

		Object value = get(key);

		if (value == null) {
			return "";
		}

		return value.toString();
	}
	
	/* comment : HMap 에서 String 으로 값추출 */
	public String getString(String key, String rtnStr) {

		if (key == null) {
			return rtnStr;
		}

		Object value = get(key);

		if (value == null) {
			return rtnStr;
		} else if (value.toString().equals("")) {
			return rtnStr;
		}

		return value.toString();
	}
	
	/* comment : HMap 에서 Int 로 값추출 */
	public int getInt(String key) {
		double value = getDouble(key);
		return (int) value;
	}
	
	/* comment : HMap 에서 Double 로 값추출 */
	public double getDouble(String key) {

		String value = getString(key);

		if (value.equals(""))
			return 0;
		double num = 0;
		try {
			num = Double.valueOf(value).doubleValue();
		} catch (Exception e) {
			num = 0;
		}
		return num;
	}
	
	/* comment : HMap 에서 Array 로 값추출 */
	public String[] getArray(String key) {

		if (key == null) {
			return new String[0];
		}

		Object value = get(key);

		if (value == null) {
			return new String[0];
		}

		if (value instanceof String[]) {
			return (String[]) value;
		} else {
			return new String[] { ((String) value).toString() };
		}
	}
	
	/* comment : HMap 에서 List 로 값추출 */
	public List<Object> getList(String key) {

		List<Object> list = null;

		try {
			list = new ArrayList<Object>();
			list = (List<Object>) super.get(key);
		} catch (ClassCastException e) {

			try {

				list = new ArrayList<Object>();
				list.add((String) super.get(key));
			} catch (Exception se) {
				list = null;
			}
		}

		return list;
	}
}
