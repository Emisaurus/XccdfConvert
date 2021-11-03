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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 *  This abstract item type represents the basic data
 *                 shared by all <xccdf:Group> and <xccdf:Rule> elements.
 *             
 * 
 * <p>Java class for selectableItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="selectableItemType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://checklists.nist.gov/xccdf/1.2}itemType">
 *       &lt;sequence>
 *         &lt;element name="rationale" type="{http://checklists.nist.gov/xccdf/1.2}htmlTextWithSubType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="platform" type="{http://checklists.nist.gov/xccdf/1.2}overrideableCPE2idrefType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requires" type="{http://checklists.nist.gov/xccdf/1.2}idrefListType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="conflicts" type="{http://checklists.nist.gov/xccdf/1.2}idrefType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="selected" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="weight" type="{http://checklists.nist.gov/xccdf/1.2}weightType" default="1.0" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "selectableItemType", propOrder = {
    "rationale",
    "platform",
    "requires",
    "conflicts"
})
@XmlSeeAlso({
    GroupType.class,
    RuleType.class
})
public abstract class SelectableItemType
    extends ItemType
{

    protected List<HtmlTextWithSubType> rationale;
    protected List<OverrideableCPE2IdrefType> platform;
    protected List<IdrefListType> requires;
    protected List<IdrefType> conflicts;
    @XmlAttribute(name = "selected")
    protected Boolean selected;
    @XmlAttribute(name = "weight")
    protected BigDecimal weight;

    /**
     * Gets the value of the rationale property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rationale property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRationale().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HtmlTextWithSubType }
     * 
     * 
     */
    public List<HtmlTextWithSubType> getRationale() {
        if (rationale == null) {
            rationale = new ArrayList<HtmlTextWithSubType>();
        }
        return this.rationale;
    }

    /**
     * Gets the value of the platform property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the platform property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlatform().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OverrideableCPE2IdrefType }
     * 
     * 
     */
    public List<OverrideableCPE2IdrefType> getPlatform() {
        if (platform == null) {
            platform = new ArrayList<OverrideableCPE2IdrefType>();
        }
        return this.platform;
    }

    /**
     * Gets the value of the requires property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requires property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequires().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdrefListType }
     * 
     * 
     */
    public List<IdrefListType> getRequires() {
        if (requires == null) {
            requires = new ArrayList<IdrefListType>();
        }
        return this.requires;
    }

    /**
     * Gets the value of the conflicts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conflicts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConflicts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdrefType }
     * 
     * 
     */
    public List<IdrefType> getConflicts() {
        if (conflicts == null) {
            conflicts = new ArrayList<IdrefType>();
        }
        return this.conflicts;
    }

    /**
     * Gets the value of the selected property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSelected() {
        if (selected == null) {
            return true;
        } else {
            return selected;
        }
    }

    /**
     * Sets the value of the selected property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSelected(Boolean value) {
        this.selected = value;
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
        if (weight == null) {
            return new BigDecimal("1.0");
        } else {
            return weight;
        }
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

}
