package com.example.dmspi.parser;

import com.example.constant.CommonConstants;
import org.apache.shardingsphere.sql.parser.api.visitor.statement.type.*;
import org.apache.shardingsphere.sql.parser.oracle.visitor.statement.type.*;
import org.apache.shardingsphere.sql.parser.spi.SQLStatementVisitorFacade;

public class DmStatementVisitorFacade implements SQLStatementVisitorFacade {

    @Override
    public Class<? extends DMLStatementVisitor> getDMLVisitorClass() {
        return OracleDMLStatementVisitor.class;
    }

    @Override
    public Class<? extends DDLStatementVisitor> getDDLVisitorClass() {
        return OracleDDLStatementVisitor.class;
    }

    @Override
    public Class<? extends TCLStatementVisitor> getTCLVisitorClass() {
        return OracleTCLStatementVisitor.class;
    }

    @Override
    public Class<? extends DCLStatementVisitor> getDCLVisitorClass() {
        return OracleDCLStatementVisitor.class;
    }

    @Override
    public Class<? extends DALStatementVisitor> getDALVisitorClass() {
        return OracleDALStatementVisitor.class;
    }

    @Override
    public Class<? extends RLStatementVisitor> getRLVisitorClass() {
        return null;
    }

    @Override
    public String getDatabaseType() {
        return CommonConstants.DM;
    }
}

