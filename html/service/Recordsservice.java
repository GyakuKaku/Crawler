package html.service;

import java.util.List;
import java.util.Map;

import html.model.Records;

public interface Recordsservice {
	public int insertrecords(Records records);
	public List<String> getalltextbytimeandkey(Map<String,Object> par);
	public List<List<String>> getcountsbytimeandkey(Map<String,Object> par);
	public List<Records> searchbypage(Map<String,Object> par);
	public String getttextbysid(Map<String,Object> par);
}
