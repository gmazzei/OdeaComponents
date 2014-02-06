package com.odea.components.filtroAvanzado.domain;

import java.io.Serializable;

/**
 * Atributo o campo de la entidad por el que se desea filtrar.
 * 
 * name: nombre que aparece en la pantalla (para que vea el usuario).
 * type: tipo de dato. Para mas informacion ver enum Type.
 * columnName: nombre de la columna relacionada al atributo.
 *  
 * @author gmazzei
 */

public class Field implements Serializable, Cloneable {
    
	private final String name;
    private final Type type;
    private final String columnName;

    public Field(String name, Type type, String columnName) {
        this.name = name;
        this.type = type;
        this.columnName = columnName;
    }

    
    @Override
    protected Field clone() {
        try {
			return (Field) super.clone();
		} catch (CloneNotSupportedException ex) {
			throw new RuntimeException(ex);
		}
    }
    
    
    //Getters & Setters
    
    public String getColumnName() {
        return columnName;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    
}
