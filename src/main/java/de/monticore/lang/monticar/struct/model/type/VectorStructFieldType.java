package de.monticore.lang.monticar.struct.model.type;

public class VectorStructFieldType implements StructFieldTypeInfo {
    private int dimensionality;
    private StructFieldTypeInfo typeOfElements;

    public int getDimensionality() {
        return dimensionality;
    }

    public void setDimensionality(int dimensionality) {
        this.dimensionality = dimensionality;
    }

    public StructFieldTypeInfo getTypeOfElements() {
        return typeOfElements;
    }

    public void setTypeOfElements(StructFieldTypeInfo typeOfElements) {
        this.typeOfElements = typeOfElements;
    }

    public static VectorStructFieldType of(StructFieldTypeInfo typeOfElements, int dimensionality) {
        VectorStructFieldType result = new VectorStructFieldType();
        result.setTypeOfElements(typeOfElements);
        result.setDimensionality(dimensionality);
        return result;
    }
}
