package br.com.pim.hotelaria.database;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class HotelariaPostgreSQL10Dialect extends PostgreSQL10Dialect {

	public HotelariaPostgreSQL10Dialect() {
		super();
		
		registraFuncoes();
	}	
	
	private void registraFuncoes() {
		registerFunction("unaccent", new StandardSQLFunction("unaccent"));
	}
}
