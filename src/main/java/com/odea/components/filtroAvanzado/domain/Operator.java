package com.odea.components.filtroAvanzado.domain;

/**
 * Operador del filtro.
 * 
 * symbol: Operador que se utilizara en la query.
 * wordExpression: Forma en la que aparecera el operador en la vista del usuario.
 * 
 * @author gmazzei
 */

public enum Operator {
    
	MAYOR(">", ">"),
    MENOR("<", "<"),
    IGUAL("=", "="),
    LIKE("LIKE", "similar a"),
    DIFERENTE("!=", "diferente de");

	
	private final String symbol;
	private final String wordExpression;
	
    Operator(String symbol, String wordExpression) {
        this.symbol = symbol;
        this.wordExpression = wordExpression;
    }
    
    
    //Getters & Setters
    
	public String getSymbol() {
        return this.symbol;
    }
    
    public String getWordExpression() {
    	return this.wordExpression;
    }
    
}