package de.monticore.lang.monticar.struct._symboltable.validation;

import de.monticore.symboltable.Symbol;

public interface SymbolChecker<T extends Symbol> {
    boolean isValid(T symbol);
}
