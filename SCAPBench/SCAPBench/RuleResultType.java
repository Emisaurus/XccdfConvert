//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.09.06 at 04:58:26 PM CDT 
//


package SCAPBench;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import STIGBench.CheckType;
import xccdfClasses.IdType;
import xccdfClasses.SeverityEnumType;


/**
 * Type for the <xccdf:rule-result> element within
 *                 an <xccdf:TestResult>. An <xccdf:rule-result> holds the result of
 *                 applying an <xccdf:Rule> from the <xccdf:Benchmark> to a target system
 *                 or component of a target system. 
 * 
 * <p>Java class for ruleResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ruleResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://checklists.nist.gov/xccdf/1.2}resultEnumType"/>
 *         &lt;element name="override" type="{http://checklists.nist.gov/xccdf/1.2}overrideType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ident" type="{http://checklists.nist.gov/xccdf/1.2}identType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="metadata" type="{http://checklists.nist.gov/xccdf/1.2}metadataType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="message" type="{http://checklists.nist.gov/xccdf/1.2}messageType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="instance" type="{http://checklists.nist.gov/xccdf/1.2}instanceResultType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fix" type="{http://checklists.nist.gov/xccdf/1.2}fixType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="check" type="{http://checklists.nist.gov/xccdf/1.2}checkType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="complex-check" type="{http://checklists.nist.gov/xccdf/1.2}complexCheckType" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="idref" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="role" type="{http://checklists.nist.gov/xccdf/1.2}roleEnumType" />
 *       &lt;attribute name="severity" type="{http://checklists.nist.gov/xccdf/1.2}severityEnumType" />
 *       &lt;attribute name="time" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="weight" type="{http://checklists.nist.gov/xccdf/1.2}weightType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ruleResultType", propOrder = {
    "result",
    "ident",
    "fix",
    "check"
})
public class RuleResultType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ResultEnumType result;
    protected List<IdentType> ident;
    protected IdType fix;
    protected List<CheckType> check;
    
    @XmlAttribute(name = "idref", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String idref;
    @XmlAttribute(name = "severity")
    protected SeverityEnumType severity;
    @XmlAttribute(name = "time")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar time;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "weight")
    protected BigDecimal weight;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link ResultEnumType }
     *     
     */
    public ResultEnumType getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultEnumType }
     *     
     */
    public void setResult(ResultEnumType value) {
        this.result = value;
    }

    /**
     * Gets the value of the ident property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ident property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentType }
     * 
     * 
     */
    public List<IdentType> getIdent() {
        if (ident == null) {
            ident = new ArrayList<IdentType>();
        }
        return this.ident;
    }

    /**
     * Gets the value of the fix property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fix property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFix().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FixType }
     * 
     * 
     */
    public IdType getFix() {
        if (fix == null) {
            fix = new IdType();
        }
        return this.fix;
    }

    /**
     * Gets the value of the check property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the check property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCheck().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CheckType }
     * 
     * 
     */
    public List<CheckType> getCheck() {
        if (check == null) {
            check = new ArrayList<CheckType>();
        }
        return this.check;
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
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
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

}
