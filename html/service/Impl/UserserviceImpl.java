package html.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import html.dao.UserDao;
import html.service.Userservice;

@Service("Userservice")
public class UserserviceImpl implements Userservice{

	@Autowired
	private UserDao userdao;
	
	public String getpsd(String account){
		return userdao.getpsd(account);
	}	
}
