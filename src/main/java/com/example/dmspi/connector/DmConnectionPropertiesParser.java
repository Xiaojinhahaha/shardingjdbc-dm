package com.example.dmspi.connector;

import com.example.constant.CommonConstants;
import org.apache.shardingsphere.infra.database.core.connector.ConnectionProperties;
import org.apache.shardingsphere.infra.database.core.connector.ConnectionPropertiesParser;
import org.apache.shardingsphere.infra.database.core.connector.url.JdbcUrl;
import org.apache.shardingsphere.infra.database.core.connector.StandardConnectionProperties;
import org.apache.shardingsphere.infra.database.core.connector.url.StandardJdbcUrlParser;

import java.util.Properties;

public final class DmConnectionPropertiesParser implements ConnectionPropertiesParser {

    @Override
    public ConnectionProperties parse(final String url, final String username, final String catalog) {
        JdbcUrl jdbcUrl = new StandardJdbcUrlParser().parse(url);
        return new StandardConnectionProperties(jdbcUrl.getHostname(), jdbcUrl.getPort(5236), jdbcUrl.getDatabase(), null, jdbcUrl.getQueryProperties(), new Properties());
    }
    @Override
    public String getDatabaseType() {
        return CommonConstants.DM;
    }
}
