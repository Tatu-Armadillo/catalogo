package br.com.fiap.catalogo.integration.test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import br.com.fiap.catalogo.integration.config.TestConfigs;
import br.com.fiap.catalogo.integration.containers.AbstractIntegrationTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BatchTestIntegration extends AbstractIntegrationTest {

        private static RequestSpecification specification;

        @Test
        @Order(0)
        void saveProduct() throws IOException {
                specification = new RequestSpecBuilder()
                                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                                .setBasePath("/catalogo/import/products")
                                .setPort(TestConfigs.SERVER_PORT)
                                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                                .build();

                final var response = given()
                                .spec(specification)
                                .contentType(TestConfigs.CONTENT_TYPE_MULTIPART)
                                .multiPart(new ClassPathResource("catalogo.csv").getFile())
                                .when().post().then()
                                .statusCode(200)
                                .extract().body().asString();

                assertNotNull(response);
                assertEquals(response, "Job iniciado com sucesso! ID do Job: 1");
        }

        @Test
        @Order(1)
        void saveProductFailedWithFileTXT() throws IOException {
                specification = new RequestSpecBuilder()
                                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                                .setBasePath("/catalogo/import/products")
                                .setPort(TestConfigs.SERVER_PORT)
                                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                                .build();

                final var response = given()
                                .spec(specification)
                                .contentType(TestConfigs.CONTENT_TYPE_MULTIPART)
                                .multiPart(new ClassPathResource("catalogo.txt").getFile())
                                .when().post().then()
                                .statusCode(415)
                                .extract().body().asString();

                assertNotNull(response);
                assertEquals(response, "Arquivo diferente do '.csv' ");
        }

}
