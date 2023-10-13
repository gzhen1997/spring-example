package com.demo;

import com.demo.swagger.common.oConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @auther gzhen
 * @date 2023-10-13  15:19
 * @description
 */
@SpringBootApplication
@Slf4j
public class SwaggerServerStarter {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(SwaggerServerStarter.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = oConvertUtils.getString(env.getProperty("server.servlet.context-path"));
        log.info("\n----------------------------------------------------------\n\t" +
                "Application 信贷中台基础服务 is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "doc.html\n" +
                "External: \thttp://" + ip + ":" + port + path + "doc.html\n" +
                "Swagger文档: \thttp://" + ip + ":" + port + path + "doc.html\n" +
                "----------------------------------------------------------");
    }
}
