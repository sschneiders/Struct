package de.monticore.lang.monticar.struct._symboltable.validation;

import de.monticore.lang.monticar.struct.Utils;
import de.monticore.lang.monticar.struct._symboltable.StructSymbol;
import de.monticore.symboltable.Scope;
import de.se_rwth.commons.logging.Log;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AllStructReferencesAreRecursivelyResolvableCheckerTest {

    private Scope symTab;
    private AllStructReferencesAreRecursivelyResolvableChecker checker;

    @Before
    public void setup() {
        Log.getFindings().clear();
        Log.enableFailQuick(false);
        symTab = Utils.createSymTab("src/test/resources");
        checker = new AllStructReferencesAreRecursivelyResolvableChecker();
    }

    @Test
    public void testValidStructs() {
        String[] validStructs = new String[]{
                "test.symtable.MyFancyStruct1",
                "test.symtable.sub1.S1",
                "test.symtable.sub2.T1",
                "test.symtable.sub3.K1"
        };
        for (String structFullName : validStructs) {
            StructSymbol struct = symTab.<StructSymbol>resolve(structFullName, StructSymbol.KIND).orElse(null);
            Assert.assertNotNull(struct);
            Assert.assertTrue(checker.isValid(struct));
        }
    }

    @Test
    public void testSelfReferences() {
        for (int i = 1; i <= 4; i++) {
            String structFullName = String.format("test.symtable.ErrSelfReference%s", i);
            StructSymbol struct = symTab.<StructSymbol>resolve(structFullName, StructSymbol.KIND).orElse(null);
            Assert.assertNotNull(struct);
            Assert.assertFalse(checker.isValid(struct));
        }
    }

    @Test
    public void testCycles() {
        String[] structsWithCycles = new String[]{
                "test.symtable.sub1.ErrCycle1",
                "test.symtable.sub2.ErrCycle1",
                "test.symtable.sub3.ErrCycle1",
                "test.symtable.sub1.ErrCycle2",
                "test.symtable.sub2.ErrCycle2",
                "test.symtable.sub3.ErrCycle2"
        };
        for (String structFullName : structsWithCycles) {
            StructSymbol struct = symTab.<StructSymbol>resolve(structFullName, StructSymbol.KIND).orElse(null);
            Assert.assertNotNull(struct);
            Assert.assertFalse(checker.isValid(struct));
        }
    }

    @Test
    public void testNonExistentReferences() {
        String[] structsWithNonExistentReferences = new String[]{
                "test.symtable.ErrNonExistentReferences"
        };
        for (String structFullName : structsWithNonExistentReferences) {
            StructSymbol struct = symTab.<StructSymbol>resolve(structFullName, StructSymbol.KIND).orElse(null);
            Assert.assertNotNull(struct);
            Assert.assertFalse(checker.isValid(struct));
        }
    }
}
