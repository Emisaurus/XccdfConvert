//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.09.06 at 04:58:26 PM CDT 
//


package xccdfClasses;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3c.dom.Element;


/**
 *  Type for an <xccdf:profile-note> within an
 *                 <xccdf:Rule>. This element contains text that describes special aspects of an
 *                 <xccdf:Rule> relative to one or more <xccdf:Profile> elements. This
 *                 allows an author to document things within <xccdf:Rule> elements that are
 *                 specific to a given <xccdf:Profile>. This information might then be displayed
 *                 to a reader based on the selection of a particular <xccdf:Profile>. The body
 *                 text may include XHTML mark-up as well as <xccdf:sub> elements.
 *             
 * 
 * <p>Java class for profileNoteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="profileNoteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="sub" type="{http://checklists.nist.gov/xccdf/1.2}subType"/>
 *         &lt;any processContents='skip' namespace='http://www.w3.org/1999/xhtml'/>
 *       &lt;/choice>
 *       &lt;attribute name="tag" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "profileNoteType", propOrder = {
    "content"
})
public class ProfileNoteType {

    @XmlElementRef(name = "sub", namespace = "http://checklists.nist.gov/xccdf/1.2", type = JAXBElement.class, required = false)
    @XmlMixed
    @XmlAnyElement
    protected List<Object> content;
    @XmlAttribute(name = "tag", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String tag;

    /**
     *  Type for an <xccdf:profile-note> within an
     *                 <xccdf:Rule>. This element contains text that describes special aspects of an
     *                 <xccdf:Rule> relative to one or more <xccdf:Profile> elements. This
     *                 allows an author to document things within <xccdf:Rule> elements that are
     *                 specific to a given <xccdf:Profile>. This information might then be displayed
     *                 to a reader based on the selection of a particular <xccdf:Profile>. The body
     *                 text may include XHTML mark-up as well as <xccdf:sub> elements.
     *             Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * {@link JAXBElement }{@code <}{@link SubType }{@code >}
     * {@link String }
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

    /**
     * Gets the value of the tag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets the value of the tag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTag(String value) {
        this.tag = value;
    }

}
