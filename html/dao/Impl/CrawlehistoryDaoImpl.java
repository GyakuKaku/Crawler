package html.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import html.Common.utils.MyBatisUtil;
import html.dao.CrawlehistoryDao;
import html.model.Crawlehistory;

public class CrawlehistoryDaoImpl {
	
	public int inserthistory(Map<String,Object> crawlehistory){
		int i = 0;
		SqlSession session=MyBatisUtil.getSession();
		CrawlehistoryDao mapper=session.getMapper(CrawlehistoryDao.class);
		try {
	    	i = mapper.inserthistory(crawlehistory);
	        session.commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.rollback();
	        return i;
	    }
		return i;
	}
	
	public List<Crawlehistory> getallhistory(){
		List<Crawlehistory> result = new ArrayList<Crawlehistory>();
		SqlSession session=MyBatisUtil.getSession();
		CrawlehistoryDao mapper=session.getMapper(CrawlehistoryDao.class);
		try {
			result = mapper.getallhistory();
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
		}
		return result;
	}
	
	public List<String> getallkey(){
		List<String> result = new ArrayList<String>();
		SqlSession session=MyBatisUtil.getSession();
		CrawlehistoryDao mapper=session.getMapper(CrawlehistoryDao.class);
		try {
			result = mapper.getallkey();
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
		}
		return result;
	}
	
	public List<String> getkeytime(Map<String,Object> par){
		List<String> result = new ArrayList<String>();
		SqlSession session=MyBatisUtil.getSession();
		CrawlehistoryDao mapper=session.getMapper(CrawlehistoryDao.class);
		try {
			result = mapper.getkeytime(par);
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
		}
		return result;
	}
	
	public List<Crawlehistory> find(Map<String,Object> par){
		List<Crawlehistory> result = new ArrayList<Crawlehistory>();
		SqlSession session=MyBatisUtil.getSession();
		CrawlehistoryDao mapper=session.getMapper(CrawlehistoryDao.class);
		try {
			result = mapper.find(par);
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
		}
		return result;
	}
}
