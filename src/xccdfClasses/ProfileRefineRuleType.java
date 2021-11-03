//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.09.06 at 04:58:26 PM CDT 
//


package xccdfClasses;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *  Type for the <xccdf:refine-rule> element in an
 *                 <xccdf:Profile>. A <xccdf:refine-rule> element allows the author to
 *                 select <xccdf:check> statements and override the @weight, @severity, and @role
 *                 of an <xccdf:Rule>, <xccdf:Group>, or cluster of <xccdf:Rule> and
 *                 <xccdf:Group> elements. Despite the name, this selector does apply for
 *                 <xccdf:Group> elements and for clusters that include <xccdf:Group>
 *                 elements, but it only affects their @weight attribute. 
 * 
 * <p>Java class for profileRefineRuleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="profileRefineRuleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="remark" type="{http://checklists.nist.gov/xccdf/1.2}textType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="idref" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="weight" type="{http://checklists.nist.gov/xccdf/1.2}weightType" />
 *       &lt;attribute name="selector" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="severity" type="{http://checklists.nist.gov/xccdf/1.2}severityEnumType" />
 *       &lt;attribute name="role" type="{http://checklists.nist.gov/xccdf/1.2}roleEnumType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "profileRefineRuleType", propOrder = {
    "remark"
})
public class ProfileRefineRuleType {

    protected List<TextType> remark;
    @XmlAttribute(name = "idref", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String idref;
    @XmlAttribute(name = "weight")
    protected BigDecimal weight;
    @XmlAttribute(name = "selector")
    protected String selector;
    @XmlAttribute(name = "severity")
    protected SeverityEnumType severity;
    @XmlAttribute(name = "role")
    protected RoleEnumType role;

    /**
     * Gets the value of the remark property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the remark property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRemark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * 
     * 
     */
    public List<TextType> getRemark() {
        if (remark == null) {
            remark = new ArrayList<TextType>();
        }
        return this.remark;
    }

    /**
     * Gets the value of the idref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdref() {
        return idref;
    }

    /**
     * Sets the value of the idref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdref(String value) {
        this.idref = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeight(BigDecimal value) {
        this.weight = value;
    }

    /**
     * Gets the value of the selector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelector() {
        return selector;
    }

    /**
     * Sets the value of the selector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelector(String value) {
        this.selector = value;
    }

    /**
     * Gets the value of the severity property.
     * 
     * @return
     *     possible object is
     *     {@link SeverityEnumType }
     *     
     */
    public SeverityEnumType getSeverity() {
        return severity;
    }

    /**
     * Sets the value of the severity property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeverityEnumType }
     *     
     */
    public void setSeverity(SeverityEnumType value) {
        this.severity = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link RoleEnumType }
     *     
     */
    public RoleEnumType getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoleEnumType }
     *     
     */
    public void setRole(RoleEnumType value) {
        this.role = value;
    }

}