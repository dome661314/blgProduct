package io.blg;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * Demo 会员启动类
 *
 * @author zhengwei
 * @date 2018/03/18
 */
@SpringBootApplication
@MapperScan(basePackages = {"io.blg.modules.*.dao"})
public class MemberApplication extends SpringBootServletInitializer {

    private final static Logger logger = LoggerFactory.getLogger(MemberApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
        logger.info("商品租赁管理系统blg-member启动完成！");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MemberApplication.class);
    }
}
