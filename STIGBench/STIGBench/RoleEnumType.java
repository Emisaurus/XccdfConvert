//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.09.06 at 04:58:26 PM CDT 
//


package STIGBench;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for roleEnumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="roleEnumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="full"/>
 *     &lt;enumeration value="unscored"/>
 *     &lt;enumeration value="unchecked"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "roleEnumType")
@XmlEnum
public enum RoleEnumType {


    /**
     * If the <xccdf:Rule> is selected, then
     *                         check it and let the result contribute to the score and appear in reports
     *                         (default). 
     * 
     */
    @XmlEnumValue("full")
    FULL("full"),

    /**
     * If the <xccdf:Rule> is selected, then
     *                         check it and include it in the test report, but give the result a status of
     *                         informational and do not use the result in score computations.
     *                     
     * 
     */
    @XmlEnumValue("unscored")
    UNSCORED("unscored"),

    /**
     * Do not check the <xccdf:Rule>; just force
     *                         the result status to notchecked. 
     * 
     */
    @XmlEnumValue("unchecked")
    UNCHECKED("unchecked");
    private final String value;

    RoleEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RoleEnumType fromValue(String v) {
        for (RoleEnumType c: RoleEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
