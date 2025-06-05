// 示例测试代码：登录接口和鉴权逻辑测试
package com.powersales;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void testLoginAndAccessProtectedResource() throws Exception {
        // 1. 构造登录请求
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("username", "admin1");
        loginRequest.put("password", "Admin123!");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(MAPPER.writeValueAsString(loginRequest), headers);

        ResponseEntity<String> loginResponse = restTemplate.postForEntity("http://localhost:" + port + "/api/accounts/login", request, String.class);

        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).contains("token").doesNotContain("用户名或密码错误");

        // 2. 解析 token
        String token = MAPPER.readTree(loginResponse.getBody()).get("data").asText();

        // 3. 访问受保护接口
        headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> authRequest = new HttpEntity<>(headers);

        ResponseEntity<String> accountList = restTemplate.exchange("http://localhost:" + port + "/api/accounts", HttpMethod.GET, authRequest, String.class);

        assertThat(accountList.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(accountList.getBody()).contains("username");
    }

    @Test
    public void testBatchCreateAccountsWithTenantAndRole() throws Exception {
        // 构造批量创建账户参数：租户 ID、数量、角色（默认密码为 88888888）
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("tenantId", 1L);
        requestBody.put ("tenantName", "国网浙江省电力有限公司");
        requestBody.put("count", 3);
        requestBody.put("role", "user");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(MAPPER.writeValueAsString(requestBody), headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/api/accounts/batch", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("创建成功");
    }
}
