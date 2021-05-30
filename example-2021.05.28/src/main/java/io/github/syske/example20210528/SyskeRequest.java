package io.github.syske.example20210528;

import java.io.InputStream;

/**
 * @program: example-2021.05.28
 * @description: 请求
 * @author: syske
 * @date: 2021-05-29 15:48
 */
public class SyskeRequest {
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public SyskeRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
