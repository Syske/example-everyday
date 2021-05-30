package io.github.syske.example20210528;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: example-2021.05.28
 * @description: 服务端
 * @author: syske
 * @date: 2021-05-29 14:34
 */
public class SyskeCatServer {
    private static final int port = 8080;
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    public static void main(String[] args) throws Exception{
        startServer4();
    }
    public static void startServer1() throws Exception{
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Socket accept = null;
            System.out.println("SyskeCatServer is starting……");
            while ((accept = serverSocket.accept()) != null){
                threadPoolExecutor.execute(new RequestHandler(accept));
            }
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }

    public static void startServer2() throws Exception{
        //开启ServerSocket服务，设置端口号为8080
        ServerSocket serverSocket=new ServerSocket(port);
        System.out.println("======服务启动成功========");
        //当服务没有关闭时
        while(!serverSocket.isClosed()){
            //使用socket进行通信
            Socket socket=serverSocket.accept();
            //收到客户端发出的inputstream
            InputStream inputStream=socket.getInputStream();
            System.out.println("执行客户请求:"+Thread.currentThread());
            System.out.println("收到客户请求");
            //读取inputstream的内容
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            String msg=null;
            while((msg=reader.readLine())!=null){
                if(msg.length()==0) break;
                System.out.println(msg);
            }
            //返回outputstream，主体内容是OK
            OutputStream outputStream=socket.getOutputStream();
            outputStream.write(RequestHandler.getResponseContent("hello"));
            outputStream.flush();
            outputStream.close();
            socket.close();
        }
    }

    public static void startServer3() throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Socket accept = null;
            System.out.println("SyskeCatServer is starting……");
            while ((accept = serverSocket.accept()) != null) {
                doService(accept);
            }
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }

    public static void startServer4() throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Socket accept = null;
            System.out.println("SyskeCatServer is starting……");
            while ((accept = serverSocket.accept()) != null) {
                SyskeRequest syskeRequest = new SyskeRequest(accept.getInputStream());
                SyskeResponse syskeResponse = new SyskeResponse(accept.getOutputStream());
                doService(syskeRequest, syskeResponse);
            }
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
    public static void doService(Socket socket) throws Exception {
        try {
            byte[] bytes = new byte[1024];
            String readLine = null;
            StringBuilder requestStr = new StringBuilder();
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
            Map<String, String> requestAttributeMap = new HashMap<>();
            int index = 0;
            while ((readLine = reader.readLine()) != null) {
                if(readLine.length()==0) break;
                System.out.println(readLine);
                requestStr.append(readLine);
                if (index > 0) {
                    String[] split = readLine.split(":", 2);
                    requestAttributeMap.put(split[0], split[1]);
                }
                index ++;

            }
            System.out.println(String.format("请求参数：%s", requestStr));
            System.out.println("requestAttributeMap" + requestAttributeMap);
            socket.getOutputStream().write(RequestHandler.getResponseContent("hello2"));
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doService(SyskeRequest request, SyskeResponse response) {
        try {
            String readLine;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            while ((readLine = bufferedReader.readLine()) != null) {
                if(readLine.length() == 0){
                    break;
                }
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            String requestStr = new String(toByteArray);
            System.out.println(String.format("请求参数：%s", requestStr));
            String[] split = requestStr.split("\r\n");
            System.out.println("end");
            response.write("hello syskeCat");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class RequestHandler implements Runnable {

    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String header = bufferedReader.readLine();
            System.out.println(header);
            String s = bufferedReader.readLine();
            System.out.println(s);
            String s1 = bufferedReader.readLine();
            System.out.println(s1);
            String msg=null;
            while((msg=bufferedReader.readLine())!=null){
                if(msg.length()==0) break;
                System.out.println(msg);
            }
            socket.getOutputStream().write(getResponseContent(String.format("hello syskeCat, dateTime:%d", System.currentTimeMillis())));

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static byte[] getResponseContent(String content) throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        //按照HTTP响应报文的格式写入
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type:text/html\n")
                .append("\r\n")
                .append("<html><head><link rel=\"icon\" href=\"data:;base64,=\"></head><body>")
                .append(content)
                .append("</body></html>");
        return httpResponse.toString().getBytes(StandardCharsets.UTF_8);
    }
}



class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    private static final int MAX_WORKER_NUMBERS = 10;

    public DefaultThreadPool() {

    }

    public DefaultThreadPool(int num) {

    }



    @Override
    public void execute(Job job) {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void addWorkers(int num) {

    }

    @Override
    public void removeWorker(int num) {

    }

    @Override
    public int getJobSize() {
        return 0;
    }
}

interface ThreadPool<Job extends Runnable> {
    // 执行一个job
    void execute(Job job);
    // 关闭线程池
    void shutdown();
    // 增加工作者线程
    void addWorkers(int num);
    // 减少工作者线程
    void removeWorker(int num);
    // 得到正在等待执行地任务数量
    int getJobSize();
}

