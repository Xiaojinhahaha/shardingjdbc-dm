package com.example.dmspi.metadata.database;

import com.example.constant.CommonConstants;
import org.apache.shardingsphere.infra.database.core.metadata.database.DialectDatabaseMetaData;
import org.apache.shardingsphere.infra.database.core.metadata.database.enums.NullsOrderType;
import org.apache.shardingsphere.infra.database.core.metadata.database.enums.QuoteCharacter;

import java.util.*;

public final class DmDatabaseMetaData implements DialectDatabaseMetaData {

    private static final List<String> RESERVED_KEYWORDS = Arrays.asList("ACCESS", "ADD", "ALL", "ALTER", "AND", "ANY", "ARRAYLEN", "AS", "ASC", "AUDIT", "BETWEEN", "BY", "CHAR", "CHECK", "CLUSTER",
            "COLUMN", "COMMENT", "COMPRESS", "CONNECT", "CREATE", "CURRENT", "DATE", "DATETIME","DECIMAL", "DEFAULT", "DELETE", "DESC", "DISTINCT", "DROP", "ELSE", "EXCLUSIVE", "EXISTS", "FILE", "FLOAT", "FOR",
            "FROM", "GRANT", "GROUP", "HAVING", "IDENTIFIED", "IMMEDIATE", "IN", "INCREMENT", "INDEX", "INITIAL", "INSERT", "INTEGER", "INTERSECT", "INTO", "IS", "LEVEL", "LIKE", "LOCK", "LONG",
            "MAXEXTENTS", "MINUS", "MODE", "MODIFY", "NOAUDIT", "NOCOMPRESS", "NOT", "NOTFOUND", "NOWAIT", "NULL", "NUMBER", "OF", "OFFLINE", "ON", "ONLINE", "OPTION", "OR", "ORDER", "PCTFREE",
            "PRIOR", "PRIVILEGES", "PUBLIC", "RAW", "RENAME", "RESOURCE", "REVOKE", "ROW", "ROWID", "ROWLABEL", "ROWNUM", "ROWS", "START", "SELECT", "SESSION", "SET", "SHARE", "SIZE", "SMALLINT",
            "SQLBUF", "SUCCESSFUL", "SYNONYM", "SYSDATE", "TABLE", "THEN", "TO", "TRIGGER", "UID", "UNION", "UNIQUE", "UPDATE", "USER", "VALIDATE", "VALUES", "VARCHAR", "VARCHAR2", "VIEW", "WHENEVER",
            "WHERE", "WITH");

    @Override
    public QuoteCharacter getQuoteCharacter() {
        return QuoteCharacter.QUOTE;
    }

    @Override
    public NullsOrderType getDefaultNullsOrderType() {
        return NullsOrderType.LAST;
    }

    @Override
    public boolean isReservedWord(final String identifier) {
        return RESERVED_KEYWORDS.contains(identifier.toUpperCase());
    }

    @Override
    public boolean isSchemaAvailable() {
        return true;
    }

    @Override
    public String formatTableNamePattern(final String tableNamePattern) {
        return tableNamePattern.toUpperCase();
    }

    @Override
    public String getDatabaseType() {
        return CommonConstants.DM;
    }
}
