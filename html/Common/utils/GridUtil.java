package html.Common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import html.model.GridModel;
import html.model.Records;

public class GridUtil {

	public static <T> GridModel initialize(List<T> list, int rows, int page){
		List<T> invdata = new ArrayList<T>();
		int first = (page-1)*rows;
		int records = list.size();
		int total = 0;
		GridModel result =new GridModel();
		if(records%rows != 0){
			total = records/rows + 1;
		}else{
			total = records/rows;
		}
		for(int i=first;i<first+rows && i < list.size();i++){
			invdata.add(list.get(i));
		}
		result.setPage(page);
		result.setRecords(records);;
		result.setTotal(total);
		result.setRows(invdata);
		return result;
	}
}
