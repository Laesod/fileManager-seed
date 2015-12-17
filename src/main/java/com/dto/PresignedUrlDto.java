package com.dto;

/**
 * Created by aautushk on 12/16/2015.
 */
public class PresignedUrlDto {
    private String presignedUrl;

    public PresignedUrlDto(String presignedUrl){
        this.presignedUrl = presignedUrl;
    }

    public String getPresignedUrl() {
        return presignedUrl;
    }

    public void setPresignedUrl(String presignedUrl) {
        this.presignedUrl = presignedUrl;
    }
}
