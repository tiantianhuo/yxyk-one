package yxyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import yxyk.listener.ApplicationStartup;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by zhaokai
 * Date: 2020/6/16
 * Time：10:50
 */
@SpringBootApplication
@EnableCaching
@ServletComponentScan
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ArticleApplication.class);
        springApplication.addListeners(new ApplicationStartup());
        springApplication.run(args);
    }
}