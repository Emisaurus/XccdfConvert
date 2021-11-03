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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *  This abstract itemType represents the basic data
 *                 shared by all <xccdf:Group>, <xccdf:Rule> and <xccdf:Value>
 *                 elements. All elements in an itemType are optional, although each element that
 *                 builds on the itemType may add its own elements, some of which will be required for
 *                 that element. 
 * 
 * <p>Java class for itemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="itemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://checklists.nist.gov/xccdf/1.2}status" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dc-status" type="{http://checklists.nist.gov/xccdf/1.2}dc-statusType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="version" type="{http://checklists.nist.gov/xccdf/1.2}versionType" minOccurs="0"/>
 *         &lt;element name="title" type="{http://checklists.nist.gov/xccdf/1.2}textWithSubType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="description" type="{http://checklists.nist.gov/xccdf/1.2}htmlTextWithSubType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="warning" type="{http://checklists.nist.gov/xccdf/1.2}warningType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="question" type="{http://checklists.nist.gov/xccdf/1.2}textType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reference" type="{http://checklists.nist.gov/xccdf/1.2}referenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="metadata" type="{http://checklists.nist.gov/xccdf/1.2}metadataType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="abstract" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="cluster-id" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="extends" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="hidden" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="prohibitChanges" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemType", propOrder = {
    "status",
    "dcStatus",
    "version",
    "title",
    "description",
    "warning",
    "question",
    "reference",
    "metadata"
})
@XmlSeeAlso({
    ValueType.class,
    SelectableItemType.class
})
public abstract class ItemType {

    protected List<Status> status;
    @XmlElement(name = "dc-status")
    protected List<DcStatusType> dcStatus;
    protected VersionType version;
    protected List<TextWithSubType> title;
    protected List<HtmlTextWithSubType> description;
    protected List<WarningType> warning;
    protected List<TextType> question;
    protected List<ReferenceType> reference;
    protected List<MetadataType> metadata;
    @XmlAttribute(name = "abstract")
    protected Boolean _abstract;
    @XmlAttribute(name = "cluster-id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String clusterId;
    @XmlAttribute(name = "extends")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String _extends;
    @XmlAttribute(name = "hidden")
    protected Boolean hidden;
    @XmlAttribute(name = "prohibitChanges")
    protected Boolean prohibitChanges;

    /**
     * Status of the item and date at which it
     *                         attained that status. <xccdf:Benchmark> authors may use this element
     *                         to record the maturity or consensus level for elements in the
     *                         <xccdf:Benchmark>. If an item does not have an explicit
     *                         <xccdf:status> given, then its status is that of its
     *                         parent.Gets the value of the status property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the status property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Status }
     * 
     * 
     */
    public List<Status> getStatus() {
        if (status == null) {
            status = new ArrayList<Status>();
        }
        return this.status;
    }

    /**
     * Gets the value of the dcStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dcStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDcStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DcStatusType }
     * 
     * 
     */
    public List<DcStatusType> getDcStatus() {
        if (dcStatus == null) {
            dcStatus = new ArrayList<DcStatusType>();
        }
        return this.dcStatus;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link VersionType }
     *     
     */
    public VersionType getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link VersionType }
     *     
     */
    public void setVersion(VersionType value) {
        this.version = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the title property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTitle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextWithSubType }
     * 
     * 
     */
    public List<TextWithSubType> getTitle() {
        if (title == null) {
            title = new ArrayList<TextWithSubType>();
        }
        return this.title;
    }

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HtmlTextWithSubType }
     * 
     * 
     */
    public List<HtmlTextWithSubType> getDescription() {
        if (description == null) {
            description = new ArrayList<HtmlTextWithSubType>();
        }
        return this.description;
    }

    /**
     * Gets the value of the warning property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the warning property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWarning().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WarningType }
     * 
     * 
     */
    public List<WarningType> getWarning() {
        if (warning == null) {
            warning = new ArrayList<WarningType>();
        }
        return this.warning;
    }

    /**
     * Gets the value of the question property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the question property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuestion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * 
     * 
     */
    public List<TextType> getQuestion() {
        if (question == null) {
            question = new ArrayList<TextType>();
        }
        return this.question;
    }

    /**
     * Gets the value of the reference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceType }
     * 
     * 
     */
    public List<ReferenceType> getReference() {
        if (reference == null) {
            reference = new ArrayList<ReferenceType>();
        }
        return this.reference;
    }

    /**
     * Gets the value of the metadata property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadata property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadata().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetadataType }
     * 
     * 
     */
    public List<MetadataType> getMetadata() {
        if (metadata == null) {
            metadata = new ArrayList<MetadataType>();
        }
        return this.metadata;
    }

    /**
     * Gets the value of the abstract property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isAbstract() {
        if (_abstract == null) {
            return false;
        } else {
            return _abstract;
        }
    }

    /**
     * Sets the value of the abstract property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAbstract(Boolean value) {
        this._abstract = value;
    }

    /**
     * Gets the value of the clusterId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClusterId() {
        return clusterId;
    }

    /**
     * Sets the value of the clusterId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClusterId(String value) {
        this.clusterId = value;
    }

    /**
     * Gets the value of the extends property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtends() {
        return _extends;
    }

    /**
     * Sets the value of the extends property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtends(String value) {
        this._extends = value;
    }

    /**
     * Gets the value of the hidden property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isHidden() {
        if (hidden == null) {
            return false;
        } else {
            return hidden;
        }
    }

    /**
     * Sets the value of the hidden property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHidden(Boolean value) {
        this.hidden = value;
    }

    /**
     * Gets the value of the prohibitChanges property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isProhibitChanges() {
        if (prohibitChanges == null) {
            return false;
        } else {
            return prohibitChanges;
        }
    }

    /**
     * Sets the value of the prohibitChanges property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProhibitChanges(Boolean value) {
        this.prohibitChanges = value;
    }

}
