package html.dao;

import java.util.List;

import html.model.Task;

public interface TaskDao {

	public int inserttask(Task task);
	public List<Task> selectall();
	public int deletetask(Task task);
}
