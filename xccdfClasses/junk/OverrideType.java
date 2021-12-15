//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.09.06 at 04:58:26 PM CDT 
//


package junk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 *  Type for an <xccdf:override> element in an
 *                 <xccdf:rule-result>. This element is used to record manual modification or
 *                 annotation of a particular <xccdf:rule-result>. All attributes and child
 *                 elements are required. It will not always be the case that the
 *                 <xccdf:new-result> value will differ from the <xccdf:old-result> value.
 *                 They might match if an authority wished to make a remark on the result without
 *                 changing it. If <xccdf:new-result> and <xccdf:old-result> differ, the
 *                 <xccdf:result> element of the enclosing <xccdf:rule-result> must match
 *                 the <xccdf:new-result> value.
 * 
 * <p>Java class for overrideType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="overrideType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="old-result" type="{http://checklists.nist.gov/xccdf/1.2}resultEnumType"/>
 *         &lt;element name="new-result" type="{http://checklists.nist.gov/xccdf/1.2}resultEnumType"/>
 *         &lt;element name="remark" type="{http://checklists.nist.gov/xccdf/1.2}textType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="time" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="authority" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "overrideType", propOrder = {
    "oldResult",
    "newResult",
    "remark"
})
public class OverrideType {

    @XmlElement(name = "old-result", required = true)
    @XmlSchemaType(name = "string")
    protected ResultEnumType oldResult;
    @XmlElement(name = "new-result", required = true)
    @XmlSchemaType(name = "string")
    protected ResultEnumType newResult;
    @XmlElement(required = true)
    protected String remark;
    @XmlAttribute(name = "time", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar time;
    @XmlAttribute(name = "authority", required = true)
    protected String authority;

    /**
     * Gets the value of the oldResult property.
     * 
     * @return
     *     possible object is
     *     {@link ResultEnumType }
     *     
     */
    public ResultEnumType getOldResult() {
        return oldResult;
    }

    /**
     * Sets the value of the oldResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultEnumType }
     *     
     */
    public void setOldResult(ResultEnumType value) {
        this.oldResult = value;
    }

    /**
     * Gets the value of the newResult property.
     * 
     * @return
     *     possible object is
     *     {@link ResultEnumType }
     *     
     */
    public ResultEnumType getNewResult() {
        return newResult;
    }

    /**
     * Sets the value of the newResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultEnumType }
     *     
     */
    public void setNewResult(ResultEnumType value) {
        this.newResult = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * Gets the value of the authority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Sets the value of the authority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthority(String value) {
        this.authority = value;
    }

}
