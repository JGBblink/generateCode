package freemarker;


import freemarker.strategy.ContextStrategy;
import freemarker.strategy.impl.*;
import freemarker.util.ClassUtil;
import freemarker.util.ConfigReadFactory;

import java.util.*;

/**
 * TODO Description
 *
 * @author JinGuiBo
 * @date 2018/7/4.
 */
public class CodeTempleScanerExecuter {

    /**
     * 构建代码
     */
    public void buildCode() {
        ConfigReadFactory configs = ConfigReadFactory.build();
        String modelPackage = configs.get("modelPackage");

        Map params = configs.fill(null);

        // 加载指定包下的所有类
        Set<Class<?>> classSet = ClassUtil.getClassSet(modelPackage);

        for (Class clazz : classSet) {
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
}
