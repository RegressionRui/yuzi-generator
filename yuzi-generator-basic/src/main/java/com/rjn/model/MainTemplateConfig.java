package com.rjn.model;

import lombok.Data;

/**
 * 动态模板配置，用来接收要传递给模板的参数
 */
@Data
public class MainTemplateConfig {
    // 我们先明确几个动态生成的需求
    // 1. 在代码开头增加作者 @Author 注释（增加代码）
    // 2. 修改程序输出的信息提示（替换代码）
    // 3. 将循环读取输入改为单次读取（可选代码）

    // 注意要根据替换需求选择参数的类型，比如可选生成用boolean，字符串替换用String
    // 为了防止用户输入为空导致模板报错，可设置默认值。也可在模板中设置，但不建议

    /**
     * 作者注释（字符串，填充值）
     */
    private String author = "rjn";

    /**
     * 输出信息
     */
    private String outputText = "输出结果";

    /**
     * 是否生成循环（开关）
     */
    private boolean loop;
}
