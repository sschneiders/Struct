package de.monticore.lang.monticar.struct.coco;

import de.monticore.lang.monticar.types2._ast.ASTPrimitiveType;
import de.monticore.lang.monticar.types2._cocos.Types2ASTPrimitiveTypeCoCo;
import de.se_rwth.commons.logging.Log;

public class PrimitiveTypesAreForbidden implements Types2ASTPrimitiveTypeCoCo {
    @Override
    public void check(ASTPrimitiveType node) {
        Log.error(
                "Primitive types are forbidden",
                node.get_SourcePositionStart()
        );
    }
}
