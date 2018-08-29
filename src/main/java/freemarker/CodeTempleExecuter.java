package freemarker;


import freemarker.strategy.ContextStrategy;
import freemarker.strategy.impl.*;
import freemarker.util.ConfigReadFactory;

import java.util.Map;

/**
 * TODO Description
 *
 * @author JinGuiBo
 * @date 2018/7/4.
 */
public class CodeTempleExecuter {

    public static void main(String[] args) {
        CodeTempleExecuter executer = new CodeTempleExecuter();
    }

    /**
     * 构建代码
     */
    public void buildCode() {
        ConfigReadFactory configs = ConfigReadFactory.build();
        String basePackage = configs.get("basePackage");
        String className = configs.get("className");
        Map params = configs.fill(null);

        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // 实例化具体代码算法策略
            ApplicationServiceStrategy applicationServiceStrategy = new ApplicationServiceStrategy(clazz, params);
            RepositoryStrategy repositoryStrategy = new RepositoryStrategy(clazz, params);
            EntityRepositoryStrategy entityRepositoryStrategy = new EntityRepositoryStrategy(clazz, params);
            EntityStrategy entityStrategy = new EntityStrategy(clazz, params);
            IApplicationServiceStrategy iApplicationServiceStrategy = new IApplicationServiceStrategy(clazz, params);
            IRepositoryStrategy iRepositoryStrategy = new IRepositoryStrategy(clazz, params);
            IQueryServiceStrategy iQueryServiceStrategy = new IQueryServiceStrategy(clazz, params);
            QueryServiceStrategy queryServiceStrategy = new QueryServiceStrategy(clazz, params);

            ContextStrategy contextStrategy = new ContextStrategy();
            contextStrategy.setEntityStrategy(entityStrategy);
            contextStrategy.setEntityRepositoryStrategy(entityRepositoryStrategy);
            contextStrategy.setRepositoryStrategy(repositoryStrategy);
            contextStrategy.setIRepositoryStrategy(iRepositoryStrategy);
            contextStrategy.setIApplicationServiceStrategy(iApplicationServiceStrategy);
            contextStrategy.setApplicationServiceStrategy(applicationServiceStrategy);
            contextStrategy.setIQueryServiceStrategy(iQueryServiceStrategy);
            contextStrategy.setQueryServiceStrategy(queryServiceStrategy);

            contextStrategy.executeStrategy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
