package html.service;

import java.util.List;

import html.model.Task;

public interface Taskservice {
	public int inserttask(Task task);
	public List<Task> selectall();
	public int deletetask(Task task);
}
