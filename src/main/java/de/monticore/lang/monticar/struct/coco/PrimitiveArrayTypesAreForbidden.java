package de.monticore.lang.monticar.struct.coco;

import de.monticore.lang.monticar.types2._ast.ASTPrimitiveArrayType;
import de.monticore.lang.monticar.types2._cocos.Types2ASTPrimitiveArrayTypeCoCo;
import de.se_rwth.commons.logging.Log;

public class PrimitiveArrayTypesAreForbidden implements Types2ASTPrimitiveArrayTypeCoCo {
    @Override
    public void check(ASTPrimitiveArrayType node) {
        Log.error(
                "Primitive array types are forbidden",
                node.get_SourcePositionStart()
        );
    }
}
