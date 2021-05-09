package io.gtihub.syske.example20210508;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import lombok.Data;
import org.apache.poi.ss.usermodel.FillPatternType;

/**
 * @program: example-2021.05.08
 * @description: demoData
 * @author: syske
 * @date: 2021-05-08 18:12
 */
@Data
public class DemoData {
    // 字符串的头背景设置成粉红 IndexedColors.PINK.getIndex()
    @HeadStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 14)
    // 字符串的头字体设置成20
    @HeadFontStyle(fontHeightInPoints = 30)
    // 字符串的内容的背景设置成天蓝 IndexedColors.SKY_BLUE.getIndex()
    @ContentStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 40)
    // 字符串的内容字体设置成20
    @ContentFontStyle(fontHeightInPoints = 30)
    @ColumnWidth(20)
    @ExcelProperty(value = "名称", index = 0)
    private String name;

    @ColumnWidth(50)
    @ExcelProperty(value = "标题", index = 2)
    private String title;

    @ColumnWidth(70)
    @ExcelProperty(value = "描述", index = 1)
    private String description;
}
