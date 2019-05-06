package html.model;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class GridModel {
	private List  rows= new ArrayList();
	
	private int records;

	private int total=0;
	
	private int page;
	
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}

}
