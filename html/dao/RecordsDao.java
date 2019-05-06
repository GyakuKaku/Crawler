package html.dao;

import java.util.List;
import java.util.Map;

import html.model.Records;

public interface RecordsDao {

	public int insertrecords(Records records);
	
	public List<String> getalltextbytimeandkey(Map<String,Object> par);
	
	public List<Records> searchbypage(Map<String,Object> par);
	
	public String gettextbysid(Map<String,Object> par);
}
