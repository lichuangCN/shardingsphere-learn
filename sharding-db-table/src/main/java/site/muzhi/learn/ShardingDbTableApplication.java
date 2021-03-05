package site.muzhi.learn;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@MapperScan(basePackages = {"site.muzhi.common.mapper"})
@ComponentScan(basePackages = "site.muzhi")
public class ShardingDbTableApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingDbTableApplication.class, args);
    }

}
