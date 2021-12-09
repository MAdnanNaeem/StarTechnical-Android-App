package com.example.startechnical.AssisterClasses;

public class UploadsofDocuments {
    private String name;
    private String imagUri;

    public UploadsofDocuments() {
    }

    public UploadsofDocuments(String name, String imagUri) {
        this.name = name;
        this.imagUri = imagUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagUri() {
        return imagUri;
    }

    public void setImagUri(String imagUri) {
        this.imagUri = imagUri;
    }
}
