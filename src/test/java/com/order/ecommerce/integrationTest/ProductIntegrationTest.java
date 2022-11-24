package com.order.ecommerce.integrationTest;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
@Transactional

@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY)
@AutoConfigureMockMvc
@Sql("/product/insert_prerequisite_records.sql")
public class ProductIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void testGetProduct() {
        String file = "src/test/resources/productTemplate.json";
        String json = readFileAsString(file);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/api/v1/products/103")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json);
        MockHttpServletResponse response=mockMvc.perform(requestBuilder).andReturn().getResponse();
        Assertions.assertThat(200).isEqualTo(response.getStatus());

    }
    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
