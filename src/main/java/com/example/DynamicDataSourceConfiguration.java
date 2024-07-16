package com.example;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.provider.AbstractDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import lombok.SneakyThrows;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.driver.jdbc.core.driver.ShardingSphereURLManager;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 分库分表多数据源配置
 * @author xiaoj
 */
@Configuration
@ConditionalOnClass(DynamicDataSourceAutoConfiguration.class)
@AutoConfigureBefore({DynamicDataSourceAutoConfiguration.class, SpringBootConfiguration.class})
public class DynamicDataSourceConfiguration {
    /**
     * 分表数据源名称
     */
    public static final String SHARDING_DATA_SOURCE_NAME = "sharding";

    /**
     * 分表数据源名称
     */
    public static final String URL_PREFIX = "jdbc:shardingsphere:";

    @Resource
    private DataSourceProperties dataSourceProperties;

    @Resource
    private DynamicDataSourceProperties dynamicDataSourceProperties;


    /**
     * 将shardingDataSource放到了多数据源（dataSourceMap）中
     */
    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        return new AbstractDataSourceProvider() {
            @SneakyThrows
            @Override
            public Map<String, DataSource> loadDataSources() {
                DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(ShardingSphereURLManager.getContent(dataSourceProperties.getUrl(), URL_PREFIX));
                Map<String, DataSource> dataSourceMap = new HashMap<>();
                // 将 shardingjdbc 管理的数据源也交给动态数据源管理
                dataSourceMap.put(SHARDING_DATA_SOURCE_NAME, dataSource);
                return dataSourceMap;
            }
        };
    }

    @Primary
    @Bean
    public DataSource dataSource() {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setStrict(dynamicDataSourceProperties.getStrict());
        dataSource.setStrategy(dynamicDataSourceProperties.getStrategy());
        dataSource.setP6spy(dynamicDataSourceProperties.getP6spy());
        dataSource.setSeata(dynamicDataSourceProperties.getSeata());
        dataSource.setPrimary(dynamicDataSourceProperties.getPrimary());
        return dataSource;
    }
}
