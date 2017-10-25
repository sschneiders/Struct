package de.monticore.lang.monticar.struct._symboltable;

import de.monticore.lang.monticar.struct.model.type.StructFieldTypeInfo;

public class StructFieldDefinitionSymbol extends StructFieldDefinitionSymbolTOP {
    private StructFieldTypeInfo typeInfo;

    public StructFieldDefinitionSymbol(String name) {
        super(name);
    }

    public StructFieldTypeInfo getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(StructFieldTypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }
}
