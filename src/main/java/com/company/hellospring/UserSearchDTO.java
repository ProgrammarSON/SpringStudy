package com.company.hellospring;

import java.util.Arrays;

public class UserSearchDTO extends UserDTO{

	private String searchCondition;
	private String searchKeyword;
	
	private Integer start;
	private Integer end;
	
	private String[] ids;	//

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "UserSearchDTO [searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword + ", start="
				+ start + ", end=" + end + ", ids=" + Arrays.toString(ids) + "]";
	}	
	
}
