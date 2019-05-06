package html.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import html.dao.TaskDao;
import html.model.Task;
import html.service.Taskservice;

@Service("Taskservice")
public class TaskserviceImpl implements Taskservice {
	
	@Autowired
	private TaskDao taskdao;

	@Override
	public int inserttask(Task task) {
		// TODO Auto-generated method stub
		return taskdao.inserttask(task);
	}

	@Override
	public List<Task> selectall() {
		// TODO Auto-generated method stub
		return taskdao.selectall();
	}

	@Override
	public int deletetask(Task task) {
		// TODO Auto-generated method stub
		return taskdao.deletetask(task);
	}
}
