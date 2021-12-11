package com.sonu.fareyeapi.module.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GETRequestDTO {
    private String url;
    private String apiKey;
    private String params;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "GETRequestDTO{" +
                "url='" + url + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
