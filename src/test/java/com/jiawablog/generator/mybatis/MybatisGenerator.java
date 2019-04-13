package com.jiawablog.generator.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisGenerator {

    static String generatorConfigPath = "src\\test\\java\\com\\jiawablog\\generator\\mybatis\\generatorConfig.xml";

    public static void main(String[] args) {
        try {
            System.out.println("生成mybatis代码开始");

            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            ConfigurationParser cp = new ConfigurationParser(warnings);
            InputStream inputStream = new FileInputStream(generatorConfigPath);
            Configuration configuration = cp.parseConfiguration(inputStream);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
            myBatisGenerator.generate(null);
            System.out.println("生成mybatis代码结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}