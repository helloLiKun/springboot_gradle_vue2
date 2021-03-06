package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * https://www.cnblogs.com/softidea/p/7068149.html
 * https://www.cnblogs.com/softidea/p/5991897.html
 * https://docs.spring.io/spring-security/site/docs/4.1.0.RELEASE/reference/htmlsingle/
 */
@SpringBootApplication
@EnableConfigurationProperties
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
       /* String address = "http://localhost:8088/WS_Server/Webservice";
               //使用Endpoint类提供的publish方法发布WebService，发布时要保证使用的端口号没有被其他应用程序占用
             Endpoint.publish(address , new WebServiceImpl());
             System.out.println("发布webservice成功!");*/
    }




}
