package de.monticore.lang.monticar.struct.model.type;

import de.monticore.lang.monticar.struct._symboltable.StructSymbolReference;

public class StructReferenceFieldType implements StructFieldTypeInfo {
    private StructSymbolReference reference;

    public StructSymbolReference getReference() {
        return reference;
    }

    public void setReference(StructSymbolReference reference) {
        this.reference = reference;
    }
}
