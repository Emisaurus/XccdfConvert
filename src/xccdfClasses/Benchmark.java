package xccdfClasses;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <Benchmark>
<status>
<title>
<description>
<notice>
<front-matter> - not used
<rear-matter> - not used
<reference>
	<publisher>
	<source>
<plain-text>
<plain-text>
<plain-text>
<version>
<metadata>
	<creator>
	<publisher>
	<contributor>
	<source>
<Profile>
	<title>
	<description>
	<select>
<Group>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "dcStatus",
    "title",
    "description",
    "notice",
    "frontMatter",
    "rearMatter",
    "reference",
    "plainText",
    "platform",
    "version",
    "metadata",
    "model",
    "profile",
    "value",
    "groupOrRule",
    "testResult",
    "signature"
})
@XmlRootElement(name = "Benchmark")
public class Benchmark {

    @XmlElement(required = true)
    protected Status status;
    @XmlElement(name = "dc-status")
    protected List<DcStatusType> dcStatus;
    protected List<TextType> title;
    protected List<HtmlTextWithSubType> description;
    protected ArrayList<String> notice;
    @XmlElement(name = "front-matter")
    protected List<HtmlTextWithSubType> frontMatter;
    @XmlElement(name = "rear-matter")
    protected List<HtmlTextWithSubType> rearMatter;
    protected List<ReferenceType> reference;
    @XmlElement(name = "plain-text")
    protected List<PlainTextType> plainText;
    protected List<CPE2IdrefType> platform;
    @XmlElement(required = true)
    protected VersionType version;
    protected List<MetadataType> metadata;
    protected List<Model> model;
    @XmlElement(name = "Profile")
    protected List<ProfileType> profile;
    @XmlElement(name = "Value")
    protected List<ValueType> value;
    @XmlElements({
        @XmlElement(name = "Group", type = GroupType.class),
        @XmlElement(name = "Rule", type = RuleType.class)
    })
    protected List<SelectableItemType> groupOrRule;
    @XmlElement(name = "TestResult")
    protected List<TestResultType> testResult;
    protected SignatureType signature;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;
    @XmlAttribute(name = "resolved")
    protected Boolean resolved;
    @XmlAttribute(name = "style")
    protected String style;
    @XmlAttribute(name = "style-href")
    @XmlSchemaType(name = "anyURI")
    protected String styleHref;

    /**
     * Status of the <xccdf:Benchmark>
     *                             indicating its level of maturity or consensus. If more than one
     *                             <xccdf:status> element appears, the element's @date attribute
     *                             should be included.Gets the value of the status property.
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
     * {@link TextType }
     * 
     * 
     */
    public List<TextType> getTitle() {
        if (title == null) {
            title = new ArrayList<TextType>();
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
     * Gets the value of the notice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NoticeType }
     * 
     * 
     */
    public ArrayList<String> getNotice() {
        if (notice == null) {
            notice = new ArrayList<String>();
        }
        return this.notice;
    }

    /**
     * Gets the value of the frontMatter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frontMatter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrontMatter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HtmlTextWithSubType }
     * 
     * 
     */
    public List<HtmlTextWithSubType> getFrontMatter() {
        if (frontMatter == null) {
            frontMatter = new ArrayList<HtmlTextWithSubType>();
        }
        return this.frontMatter;
    }

    /**
     * Gets the value of the rearMatter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rearMatter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRearMatter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HtmlTextWithSubType }
     * 
     * 
     */
    public List<HtmlTextWithSubType> getRearMatter() {
        if (rearMatter == null) {
            rearMatter = new ArrayList<HtmlTextWithSubType>();
        }
        return this.rearMatter;
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
     * Gets the value of the plainText property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plainText property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlainText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlainTextType }
     * 
     * 
     */
    public List<PlainTextType> getPlainText() {
        if (plainText == null) {
            plainText = new ArrayList<PlainTextType>();
        }
        return this.plainText;
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
     * {@link CPE2IdrefType }
     * 
     * 
     */
    public List<CPE2IdrefType> getPlatform() {
        if (platform == null) {
            platform = new ArrayList<CPE2IdrefType>();
        }
        return this.platform;
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
     * URIs of suggested scoring models to be used
     *                             when computing a score for this <xccdf:Benchmark>. A suggested
     *                             list of scoring models and their URIs is provided in the XCCDF
     *                             specification.Gets the value of the model property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the model property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Model }
     * 
     * 
     */
    public List<Model> getModel() {
        if (model == null) {
            model = new ArrayList<Model>();
        }
        return this.model;
    }

    /**
     * <xccdf:Profile> elements that
     *                             reference and customize sets of items in the
     *                             <xccdf:Benchmark>.Gets the value of the profile property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the profile property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProfile().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProfileType }
     * 
     * 
     */
    public List<ProfileType> getProfile() {
        if (profile == null) {
            profile = new ArrayList<ProfileType>();
        }
        return this.profile;
    }

    /**
     * Parameter <xccdf:Value> elements that
     *                             support <xccdf:Rule> elements and descriptions in the
     *                             <xccdf:Benchmark>. Gets the value of the value property.
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
     * <xccdf:Benchmark> test result records
     *                             (one per <xccdf:Benchmark> run).Gets the value of the testResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TestResultType }
     * 
     * 
     */
    public List<TestResultType> getTestResult() {
        if (testResult == null) {
            testResult = new ArrayList<TestResultType>();
        }
        return this.testResult;
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

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the resolved property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isResolved() {
        if (resolved == null) {
            return false;
        } else {
            return resolved;
        }
    }

    /**
     * Sets the value of the resolved property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setResolved(Boolean value) {
        this.resolved = value;
    }

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyle(String value) {
        this.style = value;
    }

    /**
     * Gets the value of the styleHref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyleHref() {
        return styleHref;
    }

    /**
     * Sets the value of the styleHref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyleHref(String value) {
        this.styleHref = value;
    }

}
