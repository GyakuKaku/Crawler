package html.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import html.dao.CrawlehistoryDao;
import html.model.Crawlehistory;
import html.service.Crawlehistoryservice;

@Service("Crawlehistoryservice")
public class CrawlehistoryserviceImpl implements Crawlehistoryservice {

	@Autowired
	private CrawlehistoryDao crawlehistoryDao;
	
	@Override
	public int inserthistory(Map<String,Object> crawlehistory) {
		// TODO Auto-generated method stub
		return crawlehistoryDao.inserthistory(crawlehistory);
	}

	@Override
	public List<Crawlehistory> getalhistory() {
		// TODO Auto-generated method stub
		return crawlehistoryDao.getallhistory();
	}

	@Override
	public List<String> getallkey() {
		// TODO Auto-generated method stub
		return crawlehistoryDao.getallkey();
	}

	@Override
	public List<String> getkeytime(Map<String, Object> par) {
		// TODO Auto-generated method stub
		return crawlehistoryDao.getkeytime(par);
	}

	@Override
	public List<Crawlehistory> find(Map<String, Object> par) {
		// TODO Auto-generated method stub
		return crawlehistoryDao.find(par);
	}

}
