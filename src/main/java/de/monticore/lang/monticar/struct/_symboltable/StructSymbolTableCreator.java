package de.monticore.lang.monticar.struct._symboltable;

import de.monticore.lang.monticar.struct._ast.ASTStructCompilationUnit;
import de.monticore.lang.monticar.struct._ast.ASTStructFieldDefinition;
import de.monticore.lang.monticar.struct._cocos.StructCoCoChecker;
import de.monticore.lang.monticar.struct.coco.DefaultStructCoCoChecker;
import de.monticore.lang.monticar.struct.model.type.StructFieldTypeInfo;
import de.monticore.symboltable.ArtifactScope;
import de.monticore.symboltable.ImportStatement;
import de.monticore.symboltable.MutableScope;
import de.monticore.symboltable.ResolvingConfiguration;
import de.se_rwth.commons.Names;

import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class StructSymbolTableCreator extends StructSymbolTableCreatorTOP {
    private final StructCoCoChecker coCoChecker = DefaultStructCoCoChecker.create();

    public StructSymbolTableCreator(ResolvingConfiguration resolvingConfig, MutableScope enclosingScope) {
        super(resolvingConfig, enclosingScope);
    }

    public StructSymbolTableCreator(ResolvingConfiguration resolvingConfig, Deque<MutableScope> scopeStack) {
        super(resolvingConfig, scopeStack);
    }

    @Override
    public void visit(ASTStructCompilationUnit node) {
        coCoChecker.checkAll(node);
        String packageQualifiedName = Names.getQualifiedName(node.getPackage());
        List<ImportStatement> imports = node.getImportStatements()
                .stream()
                .map(imprt -> {
                    String qualifiedImport = Names.getQualifiedName(imprt.getImportList());
                    return new ImportStatement(qualifiedImport, imprt.isStar());
                })
                .collect(Collectors.toList());
        ArtifactScope artifactScope = new ArtifactScope(packageQualifiedName, imports);
        putOnStack(artifactScope);
    }

    @Override
    protected void initialize_StructFieldDefinition(StructFieldDefinitionSymbol structFieldDefinition, ASTStructFieldDefinition ast) {
        StructFieldTypeInfo typeInfo = StructFieldTypeInfo.tryRepresentASTType(ast.getType(), currentScope().orElse(null));
        structFieldDefinition.setTypeInfo(typeInfo);
    }
}
