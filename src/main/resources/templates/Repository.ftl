package com.shangyi.${project}.infrastructrue.repository${module};

import com.shangyi.${project}.dao.entity${module}.${className}Entity;
import com.shangyi.${project}.dao.repository${module}.I${className}EntityRepository;
import com.shangyi.${project}.domain.model${module}.${className};
import com.shangyi.${project}.domain.repository${module}.I${className}Repository;
import com.shangyi.common.exceptions.InvalidOperationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ${className}Repository implements I${className}Repository {

    @Autowired
    I${className}EntityRepository ${lowerClassName}EntityRepository;

    @Override
    public void add(${className} model) {
        if (model == null) {
            throw new InvalidOperationException("需要添加的模型不能为空");
        }

        ${className}Entity newEntity = map(model, null);
        ${lowerClassName}EntityRepository.save(newEntity);

    }

    @Override
    public void update(${className} model) {
        if (model == null) {
            throw new InvalidOperationException("需要修改的模型不能为空");
        }
    }

    @Override
    public void delete(String id) {
        if (id == null || "".equals(id)) {
            throw new InvalidOperationException("需要删除的领域id不能为空");
        }
    }

    @Override
    public ${className} get(String id) {
        if (id == null || "".equals(id)) {
            throw new InvalidOperationException("需要删除的领域id不能为空");
        }
        return null;
    }


    private ${className}Entity map(${className} model, ${className}Entity oldEntity) {
        ${className}Entity ${lowerClassName}Entity = oldEntity;

        if (oldEntity == null) {
            ${lowerClassName}Entity = new ${className}Entity();
        }

        BeanUtils.copyProperties(model, ${lowerClassName}Entity);

        return ${lowerClassName}Entity;
    }

}
