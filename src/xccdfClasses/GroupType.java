//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.09.06 at 04:58:26 PM CDT 
//


package xccdfClasses;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 *  Data type for the <xccdf:Group> element. A
 *                 <xccdf:Group> element contains descriptive information about a portion of an
 *                 <xccdf:Benchmark>, as well as <xccdf:Rule>, <xccdf:Value>, and/or
 *                 other <xccdf:Group> elements
 * 
 * <p>Java class for groupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="groupType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://checklists.nist.gov/xccdf/1.2}selectableItemType">
 *       &lt;sequence>
 *         &lt;element ref="{http://checklists.nist.gov/xccdf/1.2}Value" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{http://checklists.nist.gov/xccdf/1.2}Group"/>
 *           &lt;element ref="{http://checklists.nist.gov/xccdf/1.2}Rule"/>
 *         &lt;/choice>
 *         &lt;element name="signature" type="{http://checklists.nist.gov/xccdf/1.2}signatureType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "groupType", propOrder = {
    "value",
    "groupOrRule",
    "signature"
})
public class GroupType
    extends SelectableItemType
{

    @XmlElement(name = "Value")
    protected List<ValueType> value;
    @XmlElements({
        @XmlElement(name = "Group", type = GroupType.class),
        @XmlElement(name = "Rule", type = RuleType.class)
    })
    protected List<SelectableItemType> groupOrRule;
    protected SignatureType signature;

    /**
     * <xccdf:Value> elements that
     *                                 belong to this <xccdf:Group>. Gets the value of the value property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the value property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValueType }
     * 
     * 
     */
    public List<ValueType> getValue() {
        if (value == null) {
            value = new ArrayList<ValueType>();
        }
        return this.value;
    }

    /**
     * Gets the value of the groupOrRule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groupOrRule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroupOrRule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GroupType }
     * {@link RuleType }
     * 
     * 
     */
    public List<SelectableItemType> getGroupOrRule() {
        if (groupOrRule == null) {
            groupOrRule = new ArrayList<SelectableItemType>();
        }
        return this.groupOrRule;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

}
