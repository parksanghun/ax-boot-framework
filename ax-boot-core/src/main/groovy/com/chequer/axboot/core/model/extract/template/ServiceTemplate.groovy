package com.chequer.axboot.core.model.extract.template

class ServiceTemplate {

    public static final String SERVICE_TEMPLATE =
'''
import com.chequer.axboot.core.domain.BaseService;
import org.springframework.stereotype.Service;
import javax.inject.Inject;


@Service
public class ${serviceClassName} extends BaseService<${entityClassName}, ${keyClassRefName}> {
    private ${repositoryClassName} ${repositoryClassFieldName};

    @Inject
    public ${serviceClassName}(${repositoryClassName} ${repositoryClassFieldName}) {
        super(${repositoryClassFieldName});
        this.${repositoryClassFieldName} = ${repositoryClassFieldName};
    }
}
'''
}
