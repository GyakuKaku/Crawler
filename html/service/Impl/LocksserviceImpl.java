package html.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import html.dao.LocksDao;
import html.service.Locksservice;

@Service("Locksservice")
public class LocksserviceImpl implements Locksservice {

	@Autowired
	private LocksDao locksdao;
	
	@Override
	public int updatelock(int sid, int islock) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sid", sid);
		map.put("islock", islock);
		return locksdao.updatelock(map);
	}

	@Override
	public int islock(int sid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sid", sid);
		return locksdao.islock(map);
	}

}
