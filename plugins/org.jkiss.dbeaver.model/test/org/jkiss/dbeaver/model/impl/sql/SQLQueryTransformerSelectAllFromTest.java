package org.jkiss.dbeaver.model.impl.sql;

import static org.junit.jupiter.api.Assertions.*;

import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SQLQueryTransformerSelectAllFromTest {

	@Test
	void testTransformQueryWhenIdentifierGiven() throws DBException {
		SQLQueryTransformerSelectAllFrom queryTransformer = new SQLQueryTransformerSelectAllFrom("table_name");
		DBPDataSource dataSource = Mockito.mock(DBPDataSource.class);
		SQLSyntaxManager syntaxManager = Mockito.mock(SQLSyntaxManager.class);
		SQLQuery query = Mockito.mock(SQLQuery.class);
		SQLQuery actualQuery = queryTransformer.transformQuery(dataSource, syntaxManager, query);
		String expedtedResult = "SELECT * FROM table_name";
		assertEquals(expedtedResult, actualQuery.getOriginalText());
	}

	@Test
	void testTransformQueryWhenNoSpecifiedIdentifier() throws DBException {
		SQLQueryTransformerSelectAllFrom queryTransformer = new SQLQueryTransformerSelectAllFrom();
		DBPDataSource dataSource = Mockito.mock(DBPDataSource.class);
		SQLSyntaxManager syntaxManager = Mockito.mock(SQLSyntaxManager.class);
		SQLQuery query = Mockito.mock(SQLQuery.class);
		Exception exception = assertThrows(DBException.class, () -> queryTransformer.transformQuery(dataSource, syntaxManager, query));
		String expedtedResult = "No specified table identifier";
		assertEquals(expedtedResult, exception.getMessage());
	}
}
