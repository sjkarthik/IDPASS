package org.idpass.lite;

public class FieldDesc {
    private String value;
    private boolean isMandatory;

    public FieldDesc() {
    }

    public FieldDesc(String value, boolean isMandatory) {
        this.value = value;
        this.isMandatory = isMandatory;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }
}
