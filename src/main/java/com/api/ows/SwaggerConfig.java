package com.api.ows;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger RESTFul API 도큐먼트 생성용 클래스
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /** Swagger 문서명 */
    @Value("${swagger.name}")
    private String documentName;

    /** Swagger로 생성할 Document의 경로 */
    @Value("${swagger.path}")
    private String documentControllerPath;

    /**
     * 컨트롤러에 대한 REST API 문서 작성용 Docket
     * @return
     */
    @Bean
    public Docket apiCommon() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)              // false : 불필요한 응답코드 및 설명 제거
                .groupName(this.documentName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(this.documentControllerPath))
                .paths(PathSelectors.any()).build();            // 전체 패키지 안의 API 조회 가능
    }

}
