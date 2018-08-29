package com.shangyi.${project}.application.service.impl${module};

import com.shangyi.${project}.application.service${module}.I${className}ApplicationService;
import com.shangyi.${project}.domain.repository${module}.I${className}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${className}ApplicationServiceImpl implements I${className}ApplicationService {

    @Autowired
    private I${className}Repository ${lowerClassName}Repository;

}
