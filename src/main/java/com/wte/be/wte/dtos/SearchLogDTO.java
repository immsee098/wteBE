package com.wte.be.wte.dtos;


public class SearchLogDTO {
    private int log_idx;
    private String search_content;
    private String result;

    public int getLog_idx() {
        return log_idx;
    }

    public void setLog_idx(int log_idx) {
        this.log_idx = log_idx;
    }

    public String getSearch_content() {
        return search_content;
    }

    public void setSearch_content(String search_content) {
        this.search_content = search_content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
