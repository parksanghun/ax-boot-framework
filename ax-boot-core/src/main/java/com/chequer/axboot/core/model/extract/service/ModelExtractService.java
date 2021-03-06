package com.chequer.axboot.core.model.extract.service;

import com.chequer.axboot.core.api.ApiException;
import com.chequer.axboot.core.code.ApiStatus;
import com.chequer.axboot.core.code.Types;
import com.chequer.axboot.core.config.AXBootContextConfig;
import com.chequer.axboot.core.model.JPAMvcModelExtractedCode;
import com.chequer.axboot.core.model.extract.metadata.Table;
import com.chequer.axboot.core.model.extract.service.jdbc.JdbcMetadataService;
import com.chequer.axboot.core.model.extract.template.TemplateParser;
import com.chequer.axboot.core.model.extract.template.file.TemplateCode;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ModelExtractService {

    @Inject
    private JdbcMetadataService jdbcMetadataService;

    @Inject
    private AXBootContextConfig axBootContextConfig;

    public JPAMvcModelExtractedCode getJpaMvcModel(String tableName, String className, String apiPath) {
        Table table = jdbcMetadataService.getTable(tableName);

        if (table == null) {
            throw new ApiException(ApiStatus.BAD_REQUEST, "테이블이 존재하지 않습니다. 테이블명을 확인하세요");
        }

        JPAMvcModelExtractedCode jpaMvcModelExtractedCode = new JPAMvcModelExtractedCode();

        jpaMvcModelExtractedCode.setController(TemplateParser.getControllerTemplate(className, apiPath, table));
        jpaMvcModelExtractedCode.setEntity(TemplateParser.getEntityTemplate(className, table));
        jpaMvcModelExtractedCode.setService(TemplateParser.getServiceTemplate(className, table));
        jpaMvcModelExtractedCode.setRepository(TemplateParser.getRepositoryTemplate(className, table));
        jpaMvcModelExtractedCode.setVo(TemplateParser.getVoTemplate(className, table));
        jpaMvcModelExtractedCode.setMyBatisInterface(TemplateParser.getMyBatisInterfaceTemplate(className, table));
        jpaMvcModelExtractedCode.setMyBatisXML(TemplateParser.getMyBatisXMLTemplate(className, table));

        AXBootContextConfig.Modeler modelerConfig = axBootContextConfig.getModelerConfig();

        if (modelerConfig.isOutput()) {
            String outputDir = modelerConfig.getOutputDir();

            try {
                File outputDirFile = new File(outputDir);
                FileUtils.forceMkdir(outputDirFile);
                FileUtils.cleanDirectory(outputDirFile);

                IOUtils.write(jpaMvcModelExtractedCode.getEntity().code(), new FileOutputStream(new File(outputDir + jpaMvcModelExtractedCode.getEntity().name())), "UTF-8");
                IOUtils.write(jpaMvcModelExtractedCode.getController().code(), new FileOutputStream(new File(outputDir + jpaMvcModelExtractedCode.getController().name())), "UTF-8");
                IOUtils.write(jpaMvcModelExtractedCode.getService().code(), new FileOutputStream(new File(outputDir + jpaMvcModelExtractedCode.getService().name())), "UTF-8");
                IOUtils.write(jpaMvcModelExtractedCode.getRepository().code(), new FileOutputStream(new File(outputDir + jpaMvcModelExtractedCode.getRepository().name())), "UTF-8");
                IOUtils.write(jpaMvcModelExtractedCode.getVo().code(), new FileOutputStream(new File(outputDir + jpaMvcModelExtractedCode.getVo().name())), "UTF-8");
                IOUtils.write(jpaMvcModelExtractedCode.getMyBatisInterface().code(), new FileOutputStream(new File(outputDir + jpaMvcModelExtractedCode.getMyBatisInterface().name())), "UTF-8");
                IOUtils.write(jpaMvcModelExtractedCode.getMyBatisXML().code(), new FileOutputStream(new File(outputDir + jpaMvcModelExtractedCode.getMyBatisXML().name())), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jpaMvcModelExtractedCode;
    }

    public TemplateCode getTemplateCode(String templateType, String tableName, String className, String apiPath) {
        Table table = jdbcMetadataService.getTable(tableName);

        switch (templateType) {
            case Types.ModelExtractorTemplate.CONTROLLER:
                return TemplateParser.getControllerTemplate(className, apiPath, table);

            case Types.ModelExtractorTemplate.VO:
                return TemplateParser.getVoTemplate(className, table);

            case Types.ModelExtractorTemplate.ENTITY:
                return TemplateParser.getEntityTemplate(className, table);

            case Types.ModelExtractorTemplate.SERVICE:
                return TemplateParser.getServiceTemplate(className, table);

            case Types.ModelExtractorTemplate.REPOSITORY:
                return TemplateParser.getRepositoryTemplate(className, table);

            case Types.ModelExtractorTemplate.MYBATIS_INTERFACE:
                return TemplateParser.getMyBatisInterfaceTemplate(className, table);

            case Types.ModelExtractorTemplate.MYBATIS_XML:
                return TemplateParser.getMyBatisXMLTemplate(className, table);
        }

        return null;
    }
}
