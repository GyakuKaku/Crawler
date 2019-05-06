package html.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import html.Common.utils.MyBatisUtil;
import html.dao.RecordsDao;
import html.model.Records;

public class RecordsDaoImpl {

	public int insertRecords(Records records){
		int i = 0;
		SqlSession session=MyBatisUtil.getSession();
		RecordsDao mapper=session.getMapper(RecordsDao.class);
		try {
        	i = mapper.insertrecords(records);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return i;
        }
		return i;
	}
	
	public List<String> getalltextbytimeandkey(Map<String,Object> par){
		List<String> result = new ArrayList<String>();
		SqlSession session=MyBatisUtil.getSession();
		RecordsDao mapper=session.getMapper(RecordsDao.class);
		try {
			result = mapper.getalltextbytimeandkey(par);
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
			return result;
		}
		return result;
	}
	
	public List<Records> searchbypage(Map<String,Object> par){
		List<Records> result = new ArrayList<Records>();
		SqlSession session=MyBatisUtil.getSession();
		RecordsDao mapper=session.getMapper(RecordsDao.class);
		try {
			result = mapper.searchbypage(par);
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
			return result;
		}
		return result;
	}
	
	public String getttextbysidv(Map<String,Object> par){
		String result = "";
		SqlSession session=MyBatisUtil.getSession();
		RecordsDao mapper=session.getMapper(RecordsDao.class);
		try {
			result = mapper.gettextbysid(par);
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
			return result;
		}
		return result;
	}
}
