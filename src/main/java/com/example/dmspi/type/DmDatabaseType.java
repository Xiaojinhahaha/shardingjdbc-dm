package com.example.dmspi.type;

import com.example.constant.CommonConstants;
import org.apache.shardingsphere.infra.database.core.type.DatabaseType;

import java.util.Collection;
import java.util.Collections;

public final class DmDatabaseType implements DatabaseType {

    @Override
    public Collection<String> getJdbcUrlPrefixes() {
        return Collections.singleton(String.format("jdbc:%s:", getType().toLowerCase()));
    }

    @Override
    public String getType() {
        return CommonConstants.DM;
    }
}
