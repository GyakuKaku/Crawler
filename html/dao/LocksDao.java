package html.dao;

import java.util.Map;

public interface LocksDao {
	
	public int updatelock(Map<String,Object> par);
	public int islock(Map<String,Object> par);
}
