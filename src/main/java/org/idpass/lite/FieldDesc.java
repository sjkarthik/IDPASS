package org.idpass.lite;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class FieldDesc {
    private String value;
    private boolean isMandatory;

    // addressLine1,addressLine2,addressLine3,region,province,postalCode

    public String from(JsonNode jnode)
            throws IOException
    {
        StringBuilder sb = new StringBuilder();
        String ret = null;

        for (String v : value.split(",")) {

            if (jnode.has(v)) {
                //ret = jnode.get(v).asText();
                sb.append(jnode.get(v).asText());
            }
        }

        ret = sb.toString();

        if (isMandatory) {
            if (ret == null || ret.isBlank() || ret.isEmpty()) {
                throw new IOException();
            }
        }

        return ret;
    }

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
