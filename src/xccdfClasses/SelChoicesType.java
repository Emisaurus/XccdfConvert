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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 *  The type of the <xccdf:choice> element, which
 *                 specifies a list of legal or suggested choices for an <xccdf:Value> object.
 *             
 * 
 * <p>Java class for selChoicesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="selChoicesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="choice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="complex-choice" type="{http://checklists.nist.gov/xccdf/1.2}complexValueType"/>
 *       &lt;/choice>
 *       &lt;attribute name="mustMatch" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="selector" type="{http://www.w3.org/2001/XMLSchema}string" default="" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "selChoicesType", propOrder = {
    "choiceOrComplexChoice"
})
public class SelChoicesType {

    @XmlElements({
        @XmlElement(name = "choice", type = String.class),
        @XmlElement(name = "complex-choice", type = ComplexValueType.class)
    })
    protected List<Object> choiceOrComplexChoice;
    @XmlAttribute(name = "mustMatch")
    protected Boolean mustMatch;
    @XmlAttribute(name = "selector")
    protected String selector;

    /**
     * Gets the value of the choiceOrComplexChoice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the choiceOrComplexChoice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChoiceOrComplexChoice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * {@link ComplexValueType }
     * 
     * 
     */
    public List<Object> getChoiceOrComplexChoice() {
        if (choiceOrComplexChoice == null) {
            choiceOrComplexChoice = new ArrayList<Object>();
        }
        return this.choiceOrComplexChoice;
    }

    /**
     * Gets the value of the mustMatch property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMustMatch() {
        return mustMatch;
    }

    /**
     * Sets the value of the mustMatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMustMatch(Boolean value) {
        this.mustMatch = value;
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
        if (selector == null) {
            return "";
        } else {
            return selector;
        }
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

}