package freemarker.strategy;

import freemarker.strategy.impl.*;
import lombok.Data;

/**
 * TODO Description
 *
 * @author JinGuiBo
 * @date 2018/7/4.
 */
@Data
public class ContextStrategy {

    ApplicationServiceStrategy applicationServiceStrategy;
    RepositoryStrategy repositoryStrategy;
    EntityRepositoryStrategy entityRepositoryStrategy;
    EntityStrategy entityStrategy;
    IApplicationServiceStrategy iApplicationServiceStrategy;
    IRepositoryStrategy iRepositoryStrategy;
    IQueryServiceStrategy iQueryServiceStrategy;
    QueryServiceStrategy queryServiceStrategy;

    public ContextStrategy() {
    }

    public void executeStrategy() {
        // 构建实体相关代码模板
        buildEntity();
    }

    /**
     * 构建query查询相关代码模板
     */
    private void buildQuery() {

        if(iQueryServiceStrategy != null) {
            iQueryServiceStrategy.generateCode();

            if (iQueryServiceStrategy.isCreated()) {
                queryServiceStrategy.generateCode();
            }
        }
    }

    /**
     * 构建领域模型相关代码模板
     */
    private void buildDomain() {

        if (iRepositoryStrategy != null && iRepositoryStrategy.isAggregateRoot()) {
            iRepositoryStrategy.generateCode();

            if(iRepositoryStrategy.isCreated()) {
                repositoryStrategy.generateCode();
                iApplicationServiceStrategy.generateCode();

                if(iApplicationServiceStrategy.isCreated()) {
                    applicationServiceStrategy.generateCode();
                }
            }

        }
    }

    /**
     * 构建实体相关代码模板
     */
    private void buildEntity() {
        // 构建实体(entity)代码
        if (entityStrategy != null) {
            entityStrategy.generateCode();

            // 创建实体仓储
            if(entityStrategy.isCreated() && entityRepositoryStrategy != null) {
                buildDomain();

                entityRepositoryStrategy.generateCode();
                if(entityRepositoryStrategy.isCreated()) {
                    // 构建query查询相关代码模板
                    buildQuery();
                }
            }
        }
    }
}
