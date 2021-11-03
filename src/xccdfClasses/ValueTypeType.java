//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.09.06 at 04:58:26 PM CDT 
//


package xccdfClasses;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for valueTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="valueTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="number"/>
 *     &lt;enumeration value="string"/>
 *     &lt;enumeration value="boolean"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "valueTypeType")
@XmlEnum
public enum ValueTypeType {


    /**
     * A numeric value. This may be decimal or
     *                         integer.
     * 
     */
    @XmlEnumValue("number")
    NUMBER("number"),

    /**
     * Any character data
     * 
     */
    @XmlEnumValue("string")
    STRING("string"),

    /**
     * True/false
     * 
     */
    @XmlEnumValue("boolean")
    BOOLEAN("boolean");
    private final String value;

    ValueTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ValueTypeType fromValue(String v) {
        for (ValueTypeType c: ValueTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
