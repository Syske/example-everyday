package io.github.syske.example20210528;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @program: example-2021.05.28
 * @description: 响应
 * @author: syske
 * @date: 2021-05-29 15:47
 */
public class SyskeResponse {
        private OutputStream outputStream;

        public SyskeResponse(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        //将文本转换为字节流
        public void write(String content) throws IOException {
            StringBuffer httpResponse = new StringBuffer();
            httpResponse.append("HTTP/1.1 200 OK\n")      //按照HTTP响应报文的格式写入
                    .append("Content-Type:text/html\n")
                    .append("\r\n")
                    .append("<html><head><link rel=\"icon\" href=\"data:;base64,=\"></head><body>")
                    .append(content)          //将页面内容写入
                    .append("</body></html>");
            outputStream.write(httpResponse.toString().getBytes());      //将文本转为字节流
            outputStream.flush();
            outputStream.close();
        }
}
