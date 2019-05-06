package html.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import html.dao.RecordsDao;
import html.model.Records;
import html.service.Recordsservice;

@Service("Recordsservice")
public class RecordsserviceImpl implements Recordsservice {

	@Autowired
	private RecordsDao recordsdao;
	
	@Override
	public int insertrecords(Records records) {
		// TODO Auto-generated method stub
		return recordsdao.insertrecords(records);
	}

	@Override
	public List<String> getalltextbytimeandkey(Map<String, Object> par) {
		// TODO Auto-generated method stub
		return recordsdao.getalltextbytimeandkey(par);
	}
	
	@Override
	public List<List<String>> getcountsbytimeandkey(Map<String, Object> par) {
		// TODO Auto-generated method stub
		List<List<String>> result = new ArrayList<List<String>>();
		@SuppressWarnings("unchecked")
		List<String> datas = (List<String>) par.get("datas");
		for(int i=0;i<datas.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("key_", par.get("key_"));
			map.put("crawledate", datas.get(i));
			List<String> temp = recordsdao.getalltextbytimeandkey(map);
			result.add(temp);
		}
		return result;
	}

	@Override
	public List<Records> searchbypage(Map<String, Object> par) {
		// TODO Auto-generated method stub
		return recordsdao.searchbypage(par);
	}

	@Override
	public String getttextbysid(Map<String, Object> par) {
		// TODO Auto-generated method stub
		return recordsdao.gettextbysid(par);
	}
	

}
