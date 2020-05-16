package org.jkiss.dbeaver.model.impl.sql;

import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLQueryTransformer;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;

/**
 * SQLQueryTransformerSelectAllFrom.
 * Transforms SQL query into SELECT * FROM <table_name>
*/
public class SQLQueryTransformerSelectAllFrom implements SQLQueryTransformer {
    private static final String SELECT_ALL_FROM_PREFIX = "SELECT * FROM ";
    private String dataTableIdentifier;

    public SQLQueryTransformerSelectAllFrom() {}

    public SQLQueryTransformerSelectAllFrom(String identifier) {
    	this.dataTableIdentifier = identifier;
	}

	@Override
    public SQLQuery transformQuery(DBPDataSource dataSource, SQLSyntaxManager syntaxManager, SQLQuery query) throws DBException {
        String selectAllFromQuery = SELECT_ALL_FROM_PREFIX + this.dataTableIdentifier;
        return new SQLQuery(dataSource, selectAllFromQuery, query, false);
    }
}
