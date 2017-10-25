package de.monticore.lang.monticar.struct.coco;

import de.monticore.lang.monticar.types2._ast.ASTComplexReferenceType;
import de.monticore.lang.monticar.types2._cocos.Types2ASTComplexReferenceTypeCoCo;
import de.se_rwth.commons.logging.Log;

public class ComplexTypesAreForbidden implements Types2ASTComplexReferenceTypeCoCo {
    @Override
    public void check(ASTComplexReferenceType node) {
        if (node.getSimpleReferenceTypes().size() != 1) {
            Log.error(
                    "Complex types are forbidden",
                    node.get_SourcePositionStart()
            );
        }
    }
}
