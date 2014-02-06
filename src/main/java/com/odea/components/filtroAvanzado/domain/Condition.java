package com.odea.components.filtroAvanzado.domain;

import java.io.Serializable;

/**
 * Condicion del filtro.
 * @author gmazzei
 */

public class Condition implements Serializable, Cloneable {
    
	private Operator operator;
    private Field field;
    private String value;

    public Condition() {}

    public Condition(Operator operator, Field field, String value) {
        this.operator = operator;
        this.field = field;
        this.value = value;
    }


    @Override
    public Condition clone() {
        try {
            Condition clone = (Condition) super.clone();
            clone.setField(this.field.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    
    //Getters & Setters
    
    public String getCondition() {
    	return this.field.getName() + " " + this.operator.getWordExpression() + " " + this.value;
    }
    
    public String getQueryCondition() {
    	return this.field.getColumnName() + " " + this.operator.getSymbol() + " " + this.getFormatedValue();
    }
    
    
    public String getFormatedValue() {
    	Type fieldType = this.getField().getType();
    	return fieldType.getFormatedValue(this.value);
    }
    

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
