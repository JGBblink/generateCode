package freemarker.strategy.impl;


import freemarker.annotation.IsColumn;
import freemarker.exection.NoCreateEntityException;
import freemarker.strategy.GenerateStrategy;
import freemarker.util.FreemarkerStrUtils;
import freemarker.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体代码生成策略
 *
 * @author JinGuiBo
 * @date 2018/7/4.
 */
@SuppressWarnings("unchecked")
public class EntityStrategy extends GenerateStrategy {

    private final String CHARSET = "utf-8";
    private final Integer BUFFER_SIZE = 1024;

    public EntityStrategy(Class clazz, Map<String, String> parame) {
        this.clazz = clazz;
        this.parame = parame;
    }

    @Override
    public void configParame() throws NoCreateEntityException {
        // 简单类名
        String className = clazz.getSimpleName();
        // 使用模板文件
        String templateName = "entity.ftl";
        // 业务模块名
        String module = parame.get("module");
        String modulePackage = "";
        if (!StringUtils.isEmpty(module)) {
            modulePackage = "." + module;
            module = StringUtils.replaceToPath(module) + "/";
        }

        String fileName ="/" + module + className + "Entity.java";
        String packageName = StringUtils.replaceToPath(parame.get("basePackage") + ".dao.entity");

        String filePath = "dao/src/main/java/" + packageName + fileName;

        // entity对应表名
        String table = FreemarkerStrUtils.upperCharToUnderLine(className);
        // 属性字段
        Field[] fields = clazz.getDeclaredFields();

        List columns = new ArrayList();
        for (Field field : fields) {
            field.setAccessible(true);

            if (field.getAnnotation(IsColumn.class) != null) {
                String name = field.getName();
                String tableName = FreemarkerStrUtils.upperCharToUnderLine(name);
                String type = field.getType().getSimpleName();

                Map column = new HashMap(2);
                column.put("table", tableName);
                column.put("name", name);
                column.put("type", type);

                columns.add(column);
            }
        }

        Map map = new HashMap(2);
        map.put("table", table);
        map.put("module", modulePackage);
        map.put("className", className);
        map.put("columns", columns);
        map.put("project", parame.get("project"));

        // 没有需要映射的表字段,不进行模板文件生成
        if(columns == null || columns.isEmpty()) {
            throw new NoCreateEntityException(className + " --> no need mapper to colum,no create code template");
        }
        // 执行模板,生成java文件
        executeTemple(templateName, filePath, map, CHARSET, BUFFER_SIZE);
    }

}
