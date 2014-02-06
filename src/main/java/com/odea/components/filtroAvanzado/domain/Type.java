package com.odea.components.filtroAvanzado.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representa el tipo de dato del Field.
 * El metodo getFormatedValue(...) se utiliza para formatear el tipo de dato al modo en que
 * aparecera en la query.
 * @author gmazzei
*/

public enum Type {
	
	
	TEXT {
		
		@Override
		public String getFormatedValue(String value) {
			return "'" + value + "'";
		}

		@Override
		public List<Operator> getSupportedOperators() {
			List<Operator> operators = new ArrayList<Operator>();
			
			operators.add(Operator.IGUAL);
			operators.add(Operator.MAYOR);
			operators.add(Operator.MENOR);
			operators.add(Operator.DIFERENTE);
			operators.add(Operator.LIKE);
			
			return operators;
		}
	}, 
	NUMERIC {
		
		@Override
		public String getFormatedValue(String value) {
			return value;
		}

		@Override
		public List<Operator> getSupportedOperators() {
			List<Operator> operators = new ArrayList<Operator>();
			
			operators.add(Operator.IGUAL);
			operators.add(Operator.MAYOR);
			operators.add(Operator.MENOR);
			operators.add(Operator.DIFERENTE);
			
			return operators;
		}
	}, 
	DATE {
		
		@Override
		public String getFormatedValue(String value) {
			try {
    			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = dateFormat.parse(value);
				return "'" + date.toString() + "'";
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}

		@Override
		public List<Operator> getSupportedOperators() {
			List<Operator> operators = new ArrayList<Operator>();
			
			operators.add(Operator.IGUAL);
			operators.add(Operator.MAYOR);
			operators.add(Operator.MENOR);
			operators.add(Operator.DIFERENTE);
			
			return operators;
		}
	};
	
	

	
	public abstract String getFormatedValue(String value);
	
	public abstract List<Operator> getSupportedOperators();
	
}
