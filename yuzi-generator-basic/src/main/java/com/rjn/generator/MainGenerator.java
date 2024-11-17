package com.rjn.generator;

import com.rjn.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器，动静结合生成
 */
public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException {

        // 1、静态文件生成
        // 获取整个项目的根路径   D:\Study\code\yuzi-generator
        String projectPath = System.getProperty("user.dir");

        // 路径“yuzi-generator-demo-projects/acm-template”中分隔符'/'在不同操作系统下不一样，可用File.separator
        // 输入路径
        String inputPath = projectPath + File.separator + "yuzi-generator-demo-projects" + File.separator + "acm-template";
        // 输出路径
        String outputPath = projectPath;
        // 复制
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);

        // 2、动态文件生成
        String dynamicInputPath = projectPath + File.separator + "yuzi-generator-basic" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutPath = projectPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("芮俊男");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和结果：");

        DynamicGenerator.doGenerate(dynamicInputPath,dynamicOutPath,mainTemplateConfig);
    }
}
