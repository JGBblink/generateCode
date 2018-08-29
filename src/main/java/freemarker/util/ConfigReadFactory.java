package freemarker.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * TODO Description
 *
 * @author JinGuiBo
 * @date 2018/7/4.
 */
public class ConfigReadFactory <T>{

    private static Map<String,String> configs = new HashMap(5);
    private static ConfigReadFactory configRead = new ConfigReadFactory();

    private ConfigReadFactory() {
    }

    /**
     * 读取配置文件
     */
    private void loadConfig() {
        Properties properties = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 提取配置文件参数
        Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = properties.getProperty(key);
            configs.put(key, value);
        }

        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigReadFactory getInstance() {
        return configRead;
    }

    public static ConfigReadFactory build() {
        configs = new HashMap(5);
        configRead.loadConfig();
        return configRead;
    }

    public String get(String key) {
        return configs.get(key);
    }

    public Map<String, String> fill(Map<String,String> target) {
        if(target == null) {
            target = new HashMap<>(5);
        }
        target.putAll(configs);

        return target;
    }
}
