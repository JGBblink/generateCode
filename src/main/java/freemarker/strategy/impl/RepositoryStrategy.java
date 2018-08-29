package freemarker.strategy.impl;


import freemarker.strategy.GenerateStrategy;
import freemarker.util.FreemarkerStrUtils;
import freemarker.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 领域repository代码生成策略
 *
 * @author JinGuiBo
 * @date 2018/7/4.
 */
@SuppressWarnings("unchecked")
public class RepositoryStrategy extends GenerateStrategy {

    private final String CHARSET = "utf-8";
    private final Integer BUFFER_SIZE = 1024;

    public RepositoryStrategy(Class clazz, Map<String, String> parame) {
        this.clazz = clazz;
        this.parame = parame;
    }

    @Override
    public void configParame() {
        // 简单类名
        String className = clazz.getSimpleName();
        // 首字母小写类名
        String lowerClassName = FreemarkerStrUtils.lowerFirst(className);
        // 文件构建前缀
        String pathPrefix = parame.get("basePath");
        // 使用模板文件
        String templateName = "Repository.ftl";
        // 业务模块名
        String module = parame.get("module");
        String modulePackage = "";
        if (!StringUtils.isEmpty(module)) {
            modulePackage = "." + module;
            module = StringUtils.replaceToPath(module) + "/";
        }

        String fileName ="/" + module + className + "Repository.java";
        String packageName = StringUtils.replaceToPath(parame.get("basePackage") + ".infrastructrue.repository");

        String filePath = "infrastructrue/src/main/java/" + packageName + fileName;

        Map map = new HashMap(3);
        map.put("module", modulePackage);
        map.put("project", parame.get("project"));
        map.put("className", className);
        map.put("lowerClassName", lowerClassName);

        // 执行模板,生成java文件
        executeTemple(templateName, filePath, map, CHARSET, BUFFER_SIZE);
    }
}
