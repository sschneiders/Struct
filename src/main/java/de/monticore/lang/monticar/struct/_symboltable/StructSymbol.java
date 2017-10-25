package de.monticore.lang.monticar.struct._symboltable;

import de.monticore.symboltable.Symbols;

import java.util.Collection;

public class StructSymbol extends StructSymbolTOP {
    public StructSymbol(String name) {
        super(name);
    }

    public Collection<StructFieldDefinitionSymbol> getStructFieldDefinitions() {
        return Symbols.sortSymbolsByPosition(getSpannedScope().resolveLocally(StructFieldDefinitionSymbol.KIND));
    }
}
