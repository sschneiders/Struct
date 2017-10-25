package de.monticore.lang.monticar.struct.coco;

import de.monticore.lang.monticar.types2._ast.ASTSimpleReferenceType;
import de.monticore.lang.monticar.types2._cocos.Types2ASTSimpleReferenceTypeCoCo;
import de.se_rwth.commons.logging.Log;

public class GenericsAreForbidden implements Types2ASTSimpleReferenceTypeCoCo {
    @Override
    public void check(ASTSimpleReferenceType node) {
        if (node.getTypeArguments().isPresent()) {
            Log.error(
                    "Generics are forbidden",
                    node.getTypeArguments().get().get_SourcePositionStart()
            );
        }
    }
}
