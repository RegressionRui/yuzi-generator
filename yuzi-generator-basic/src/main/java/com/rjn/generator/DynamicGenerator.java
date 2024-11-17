package com.rjn.generator;

import com.rjn.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


/**
 * 动态文件生成器
 */
public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        // 指定模板文件所在的路径
        /**
         * 此处由于创建 yuzi-generator-basic时在yuzi-generator下创建的module，使用相对路径时定位到的根目录为yuzi-generator
         * 故会报错，找不到src目录，若以新项目创建 yuzi-generator-basic则正常，解决方法即手动拼接路径
         * junit单元测试中却没有问题
         * 最开始最好以新项目创建 yuzi-generator-basic
         */
        // 拼接路径
        String projectPath = System.getProperty("user.dir")+ File.separator + "yuzi-generator-basic";
        String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("rjn");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerate(inputPath, outputPath, mainTemplateConfig);


    }

    /**
     * 生成文件
     *
     * @param inputPath 模板文件输入路径
     * @param outputPath 输出路径
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径，以输入路径对应的父目录作为模板所在目录
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板,上面已经指定了路径，参数直接传文件名称即可
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        // 直接使用FileWriter对象，指定生成的文件路径和名称
        Writer out = new FileWriter(outputPath);

        // 调用template对象的process方法处理并生成文件
        template.process(model, out);
        // 生成文件后别忘了关闭流
        out.close();
    }

}
