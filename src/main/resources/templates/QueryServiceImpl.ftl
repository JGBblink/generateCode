package com.shangyi.${project}.query.service.impl${module};

import com.shangyi.${project}.dao.repository${module}.I${className}EntityRepository;
import com.shangyi.${project}.query.service${module}.I${className}QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${className}QueryServiceImpl implements I${className}QueryService {

    @Autowired
    I${className}EntityRepository ${lowerClassName}EntityRepository;

}
