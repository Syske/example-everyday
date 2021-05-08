package io.gtihub.syske.example20210508;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.List;
import java.util.UUID;

/**
 * @program: example-2021.05.08
 * @description: 2021.05.08 example
 * @author: syske
 * @date: 2021-05-08 17:39
 */
public class Example {
    public static void main(String[] args) {
        String s = writeExcel();
//        readExcel("64177656-6240-44b9-83f8-898bd8c52262.xlsx");
    }

    public static String writeExcel() {
        String fileName = UUID.randomUUID().toString() + ".xlsx";
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 20);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 20);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        ExcelWriterBuilder write = EasyExcel.write(fileName, DemoData.class).registerWriteHandler(horizontalCellStyleStrategy);
        ExcelWriterSheetBuilder sheet = write.sheet("模板");
        DemoData demoData = new DemoData();
        demoData.setDescription("描述信息");
        demoData.setName("名称");
        demoData.setTitle("标题");
        List<DemoData> dataList = Lists.newArrayList(demoData);
        sheet.doWrite(dataList);
        return fileName;
    }

    public static void readExcel(String filePath) {
        EasyExcel.read(filePath, DemoData.class, new DemoDataListener()).sheet().headRowNumber(0).doRead();
    }


}
