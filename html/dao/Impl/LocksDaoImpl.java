package html.dao.Impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import html.Common.utils.MyBatisUtil;
import html.dao.LocksDao;

public class LocksDaoImpl {

	public int updatelock(Map<String,Object> par){
		int i = 0;
		SqlSession session=MyBatisUtil.getSession();
		LocksDao mapper=session.getMapper(LocksDao.class);
		try {
        	i = mapper.updatelock(par);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return i;
        }
		return i;
	}
	
	public int islock(Map<String,Object> par){
		int i = 0;
		SqlSession session=MyBatisUtil.getSession();
		LocksDao mapper=session.getMapper(LocksDao.class);
		try {
        	i = mapper.islock(par);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return -1;
        }
		return i;
	}
}
