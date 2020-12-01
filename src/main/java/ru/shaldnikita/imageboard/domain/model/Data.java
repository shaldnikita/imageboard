package ru.shaldnikita.imageboard.domain.model;

import lombok.AllArgsConstructor;

import java.util.Base64;

@lombok.Data
@AllArgsConstructor
public class Data {
    private String name;
    private long size;
    private byte[] content;

    public String getBase64EncodedStringContent() {
        var encodedData = Base64.getEncoder().encode(this.content);
        return new String(encodedData);
    }
}
