//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.09.06 at 04:58:26 PM CDT 
//


package junk;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for interfaceHintType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="interfaceHintType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="choice"/>
 *     &lt;enumeration value="textline"/>
 *     &lt;enumeration value="text"/>
 *     &lt;enumeration value="date"/>
 *     &lt;enumeration value="datetime"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "interfaceHintType")
@XmlEnum
public enum InterfaceHintType {


    /**
     * Multiple choice
     * 
     */
    @XmlEnumValue("choice")
    CHOICE("choice"),

    /**
     * Multiple lines of text
     * 
     */
    @XmlEnumValue("textline")
    TEXTLINE("textline"),

    /**
     * Single line of text
     * 
     */
    @XmlEnumValue("text")
    TEXT("text"),

    /**
     * Date
     * 
     */
    @XmlEnumValue("date")
    DATE("date"),

    /**
     * Date and time
     * 
     */
    @XmlEnumValue("datetime")
    DATETIME("datetime");
    private final String value;

    InterfaceHintType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InterfaceHintType fromValue(String v) {
        for (InterfaceHintType c: InterfaceHintType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}