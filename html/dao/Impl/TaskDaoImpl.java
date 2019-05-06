package html.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import html.Common.utils.MyBatisUtil;
import html.dao.TaskDao;
import html.model.Task;

public class TaskDaoImpl {

	public int inserttask(Task task){
		int i = 0;
		SqlSession session=MyBatisUtil.getSession();
		TaskDao mapper=session.getMapper(TaskDao.class);
		try {
        	i = mapper.inserttask(task);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return i;
        }
		return i;
	}
	
	public List<Task> selectall(){
		List<Task> result = new ArrayList<Task>();
		SqlSession session=MyBatisUtil.getSession();
		TaskDao mapper=session.getMapper(TaskDao.class);
		try {
        	result = mapper.selectall();
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return result;
        }
		return result;
	}
	
	public int deletetask(Task task){
		int i = 0;
		SqlSession session=MyBatisUtil.getSession();
		TaskDao mapper=session.getMapper(TaskDao.class);
		try {
        	i = mapper.deletetask(task);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return i;
        }
		return i;
	}
}
