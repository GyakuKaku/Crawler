package html.dao.Impl;

import org.apache.ibatis.session.SqlSession;

import html.Common.utils.MyBatisUtil;
import html.dao.UserDao;

public class UserDaoImpl {

	public String getpassword(String account) {
		// TODO Auto-generated method stub
		SqlSession session=MyBatisUtil.getSession();
        UserDao mapper=session.getMapper(UserDao.class);
        String psd = "";
        try {
        	psd=mapper.getpsd(account);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return null;
        }
		return psd;
	}

}
