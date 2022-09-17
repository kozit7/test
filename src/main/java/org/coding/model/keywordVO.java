package org.coding.model;

public class keywordVO {
	private String search_word;
	
	private String searchdate;

	public String getSearch_word() {
		return search_word;
	}

	public void setSearch_word(String search_word) {
		this.search_word = search_word;
	}

	public String getSearchdate() {
		return searchdate;
	}

	public void setSearchdate(String searchdate) {
		this.searchdate = searchdate;
	}

	@Override
	public String toString() {
		return "keywordVO [search_word=" + search_word + ", searchdate=" + searchdate + "]";
	}
	
	
}
