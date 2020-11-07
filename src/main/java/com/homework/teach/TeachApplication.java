package com.homework.teach;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@MapperScan("com.homework.teach.mapper")
@SpringBootApplication
public class TeachApplication {
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		//<!-- 该参数默认为false -->
		//<!-- 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用 -->
		//<!-- 和startPage中的pageNum效果一样-->
		properties.setProperty("offsetAsPageNum", "true");
		// <!-- 该参数默认为false -->
		//<!-- 设置为true时，使用RowBounds分页会进行count查询 -->
		properties.setProperty("rowBoundsWithCount", "true");
		//   <!-- 3.3.0版本可用 - 分页参数合理化，默认false禁用 -->
		//<!-- 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页 -->
		//<!-- 禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据 -->
//        properties.setProperty("reasonable", "false");
		properties.setProperty("dialect", "mysql");    //配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}
	public static void main(String[] args) {
		SpringApplication.run(TeachApplication.class, args);
	}

}
