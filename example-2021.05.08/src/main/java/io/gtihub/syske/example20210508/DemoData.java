package io.gtihub.syske.example20210508;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @program: example-2021.05.08
 * @description: demoData
 * @author: syske
 * @date: 2021-05-08 18:12
 */
@Data
public class DemoData {
    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("标题")
    private String title;

    @ExcelProperty("描述")
    private String description;
}
