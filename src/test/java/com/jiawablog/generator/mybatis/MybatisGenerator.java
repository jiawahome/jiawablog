package com.jiawablog.generator.mybatis;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MybatisGenerator {

    static String generatorConfigPath = "src\\test\\java\\com\\jiawablog\\generator\\mybatis\\generatorConfig.xml";
    static String mapperPath = "src\\main\\resources\\mapper\\";

    public static void main(String[] args) {
        try {
            System.out.println("生成mybatis代码开始");

            File file = new File(generatorConfigPath);
            SAXReader reader=new SAXReader();
            //读取xml文件到Document中
            Document doc=reader.read(file);
            //获取xml文件的根节点
            Element rootElement=doc.getRootElement();
            //读取context节点
            Element contextElement = rootElement.element("context");
            //定义一个Element用于遍历
            Element tableElement;
            //遍历所有名叫“table”的节点
            for(Iterator i = contextElement.elementIterator("table"); i.hasNext();){
                tableElement=(Element)i.next();
                String obj = tableElement.attributeValue("domainObjectName");
                System.out.println("表："+tableElement.attributeValue("tableName"));
                System.out.println("domain："+tableElement.attributeValue("domainObjectName"));
                File f = new File(mapperPath + obj + "Mapper.xml");
                if (f.exists()) {
                    f.delete();
                }
            }

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