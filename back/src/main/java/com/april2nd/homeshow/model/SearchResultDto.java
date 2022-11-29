package com.april2nd.homeshow.model;

import java.util.List;
import java.util.Map;

public class SearchResultDto {

    List<Map<String, String>> dongResult;
    List<Map<String, String>> aptResult;

    public List<Map<String, String>> getDongResult() {
        return dongResult;
    }

    public void setDongResult(List<Map<String, String>> dongResult) {
        this.dongResult = dongResult;
    }

    public List<Map<String, String>> getAptResult() {
        return aptResult;
    }

    public void setAptResult(List<Map<String, String>> aptResult) {
        this.aptResult = aptResult;
    }

}
