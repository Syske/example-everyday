package io.github.syske.example20210504;

import com.beust.jcommander.internal.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.*;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @program: example-2021.05.04
 * @description: 二维码演示
 * @author: syske
 * @date: 2021-05-04 14:17
 */
public class Example {
    private final static QRCodeWriter qrCodeWriter = new QRCodeWriter();
    private final static QRCodeReader qrCodeReader = new QRCodeReader();

    public static void main(String[] args) throws Exception {

//        String content = "name : 云中志\ncontent : hello qrCode\n date : 2021-05-04\n url : https://www.cnblogs.com/caoleiCoding/";
////        generateQrCodeImage(content, "d://testQrCode1.png");
//        Map<EncodeHintType, Object> hintTypeObjectMap = Maps.newHashMap();
//        // 指定字符编码
//        hintTypeObjectMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//        generateQrCodeImage(content, "d://testQrCode1.png", hintTypeObjectMap);

        readQrCodeImage();
    }

    /**
     * 生成二维码图片
     *
     * @param content 文本内容
     * @param fileSaveFullPath 文件保存路径
     * @throws Exception
     */
    public static void generateQrCodeImage(String content,
                                           String fileSaveFullPath) throws Exception {
    generateQrCodeImage(content, fileSaveFullPath, null);
    }

    /**
     *
     * @param content
     * @param fileSaveFullPath
     * @param hints 参数配置
     * @throws Exception
     */
    public static void generateQrCodeImage(String content,
                                           String fileSaveFullPath, Map<EncodeHintType,Object> hints) throws Exception {
        BitMatrix bitMatrix = Objects.isNull(hints) ?
                qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 500, 500)
                :qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 500, 500, hints);
        System.out.println(bitMatrix);
        File file2 = new File("d://testQrCode2.png");
        Path path = file2.toPath();
        // 前景色 玉红色
        int onColor = 0xFF1661AB;
//        int onColor = 0xFFC02C38;
        // 背景色 白色
        int offColor = 0xFFFFFFFF;
//        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(onColor, offColor);
//        MatrixToImageWriter.writeToPath(bitMatrix, "png", path, matrixToImageConfig);
        // 原生生成图片
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? onColor : offColor);
            }
        }
        // 生成二维码图片
        ImageIO.write(image, "png", new File(fileSaveFullPath));
    }

    public static void readQrCodeImage() throws Exception {
        Properties properties = System.getProperties();
        properties.list(System.out);
        FileInputStream fileInputStream = new FileInputStream("d://testQrCode1.png");
        BufferedImage readImage = ImageIO.read(fileInputStream);
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(readImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result decode = qrCodeReader.decode(bitmap);
        System.out.println(decode);
    }


}
