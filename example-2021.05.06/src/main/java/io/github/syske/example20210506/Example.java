package io.github.syske.example20210506;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.*;

/**
 * @program: example-2021.05.06
 * @description: 2021.05.06-example
 * @author: syske
 * @create: 2021-05-06 20:46
 */
public class Example {
    public static void main(String[] args) throws Exception {
       /* if (args.length == 0) {
            System.out.println("Usage: \nGZIPcompress file\n\tUses GZIP compression to compress the file to test.gz");
            System.exit(1);
        }
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        BufferedOutputStream outputStream = new BufferedOutputStream(
                new GZIPOutputStream(
                        new FileOutputStream("test.gz")));
        System.out.println("Writing File");
        int c;
        while ((c = in.read()) != -1) {
            outputStream.write(c);
        }
        in.close();
        outputStream.close();
        System.out.println("Reading file");
        BufferedReader in2 = new BufferedReader(
                new InputStreamReader(
                        new GZIPInputStream(
                                new FileInputStream("test.gz"))));
        String s;
        while ((s = in2.readLine()) != null) {
            System.out.println(s);
        }*/
//       compressFileByGZ("D:\\face-img-3fac79bf0ce543f6bf24dcb9a064e27a.jpg");
//        compressFileByZIP("D:\\tmp\\img\\created");
        extractZip("created.zip");
//        extractGz("fb53ecbf-2039-47b6-a4c1-2f4ea063f06epom.xml.gz");
    }


    /**
     * 压缩gz
     * @param filePath
     * @return
     * @throws Exception
     */
    private static String compressFileByGZ(String filePath) throws Exception {
        File file = new File(filePath);
        System.out.println(file.getName());
        String outputFIleName = file.getName() + ".gz";
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream(outputFIleName));

        FileInputStream fileInputStream = new FileInputStream(filePath);
        byte[] bytes = new byte[1024];
        int read;
        while ((read = fileInputStream.read(bytes)) != -1) {
            gzipOutputStream.write(bytes);
        }
        fileInputStream.close();
        gzipOutputStream.close();

        return outputFIleName;
    }

    /**
     * 解压GZ
     * @param filePath
     * @throws Exception
     */
    public static void extractGz(String filePath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath).getName().replace(".gz", ""));
        byte[] bytes = new byte[1024];
        int x ;
        while ((x = gzipInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes);
        }
        fileInputStream.close();
        gzipInputStream.close();
        fileOutputStream.close();
    }

    /**
     * 压缩zip
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    private static String compressFileByZIP(String filePath) throws Exception {
        File file = new File(filePath);
        System.out.println(file.getName());
        String outputFIleName = file.getName() + ".zip";
        ArrayList<File> fileList = new ArrayList<>();
        if (file.isDirectory()) {
            fileList.addAll(Arrays.asList(file.listFiles()));
        } else {
            fileList.add(file);
        }
        FileInputStream fileInputStream = null;
        CheckedOutputStream checkedOutputStream = new CheckedOutputStream(new FileOutputStream(outputFIleName), new Adler32());
        ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream);
        for (File f : fileList) {
            if (f.isDirectory()) {
                continue;
            }
            zipOutputStream.putNextEntry(new ZipEntry(f.getName()));
            fileInputStream = new FileInputStream(f);
            byte[] bytes = new byte[1024];
            int read;
            while ((read = fileInputStream.read(bytes)) != -1) {
                zipOutputStream.write(bytes);
            }
        }


        byte[] bytes = new byte[1024];
        int read;
        while ((read = fileInputStream.read(bytes)) != -1) {
            zipOutputStream.write(bytes);
        }
        fileInputStream.close();
        zipOutputStream.close();

        return outputFIleName;
    }

    /**
     * 解压zip文件
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String extractZip(String filePath) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(filePath);
        CheckedInputStream checkedInputStream = new CheckedInputStream(fileInputStream, new Adler32());
        ZipInputStream zipInputStream = new ZipInputStream(checkedInputStream);
        ZipEntry zipEntry;

        FileOutputStream fileOutputStream = null;
        File savePath = new File(filePath.replace(".zip", ""));
        if (!savePath.exists()) {
            savePath.mkdir();
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(zipInputStream);
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            fileOutputStream = new FileOutputStream(savePath.getName() + "/" + zipEntry.getName());
            int x;
            byte[] bytes = new byte[1024];
            while ((x = bufferedInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes);
            }
            fileOutputStream.close();
        }
        zipInputStream.close();
        fileInputStream.close();
        return null;
    }
}
