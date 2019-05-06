package html.service;

import java.util.List;
import java.util.Map;

import html.model.Crawlehistory;

public interface Crawlehistoryservice {
	public int inserthistory(Map<String,Object> crawlehistory);
	
	public List<Crawlehistory> getalhistory();
	
	public List<String> getallkey();
	
	public List<String> getkeytime(Map<String,Object> par);
	
	public List<Crawlehistory> find(Map<String,Object> par);
}
