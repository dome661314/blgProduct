package io.blg;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@MapperScan(basePackages = {"io.blg.modules.*.dao"})
public class AdminApplication extends SpringBootServletInitializer {

	private final static Logger logger = LoggerFactory.getLogger(AdminApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
		logger.info("商品系统管理平台blg-admin启动完成！");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AdminApplication.class);
	}
}
