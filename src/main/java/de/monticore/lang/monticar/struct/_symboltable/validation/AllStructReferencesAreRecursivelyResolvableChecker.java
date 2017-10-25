package de.monticore.lang.monticar.struct._symboltable.validation;

import de.monticore.lang.monticar.struct._symboltable.StructFieldDefinitionSymbol;
import de.monticore.lang.monticar.struct._symboltable.StructSymbol;
import de.monticore.lang.monticar.struct.model.type.StructFieldTypeInfo;
import de.monticore.lang.monticar.struct.model.type.StructReferenceFieldType;
import de.monticore.lang.monticar.struct.model.type.VectorStructFieldType;
import de.monticore.symboltable.references.FailedLoadingSymbol;
import de.se_rwth.commons.logging.Log;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AllStructReferencesAreRecursivelyResolvableChecker implements SymbolChecker<StructSymbol> {

    private final static String NEW_LINE = System.lineSeparator();

    private final Set<String> visitedStructs = new HashSet<>();

    @Override
    public boolean isValid(StructSymbol symbol) {
        String structFullName = symbol.getFullName();
        boolean isCycle = visitedStructs.contains(structFullName);
        if (isCycle) {
            StringBuilder message = new StringBuilder("the following structures form a cycle by referencing:");
            for (String s : visitedStructs) {
                message.append(NEW_LINE);
                message.append(s);
            }
            Log.error(message.toString(), symbol.getSourcePosition());
            return false;
        }
        visitedStructs.add(structFullName);
        Collection<StructFieldDefinitionSymbol> fieldDefinitions = symbol.getStructFieldDefinitions();
        for (StructFieldDefinitionSymbol f : fieldDefinitions) {
            StructFieldTypeInfo fieldTypeInfo = f.getTypeInfo();
            if (fieldTypeInfo instanceof StructReferenceFieldType) {
                if (!checkReferenceFieldType((StructReferenceFieldType) fieldTypeInfo)) {
                    return false;
                }
            } else if (fieldTypeInfo instanceof VectorStructFieldType) {
                StructFieldTypeInfo typeOfElements = ((VectorStructFieldType) fieldTypeInfo).getTypeOfElements();
                if (typeOfElements instanceof StructReferenceFieldType) {
                    if (!checkReferenceFieldType((StructReferenceFieldType) typeOfElements)) {
                        return false;
                    }
                }
            }
        }
        visitedStructs.remove(structFullName);
        return true;
    }

    private boolean checkReferenceFieldType(StructReferenceFieldType referenceFieldType) {
        StructSymbol referencedStruct;
        try {
            referencedStruct = referenceFieldType.getReference().getReferencedSymbol();
        } catch (FailedLoadingSymbol e) {
            Log.error(e.getMessage());
            return false;
        }
        return referencedStruct != null && isValid(referencedStruct);
    }
}
