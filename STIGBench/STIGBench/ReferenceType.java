//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.09.06 at 04:58:26 PM CDT 
//


package STIGBench;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 *  This element provides supplementary descriptive text
 *                 for a XCCDF elements. When used, it has either a simple string value or a value
 *                 consisting of simple Dublin Core elements. If a bare string appears, then it is
 *                 taken to be the string content for a Dublin Core title element. Multiple
 *                 <xccdf:reference> elements may appear; a document generation processing tool
 *                 may concatenate them, or put them into a reference list, and may choose to number
 *                 them. 
 * 
 * <p>Java class for referenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="referenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;any processContents='lax' namespace='http://purl.org/dc/elements/1.1/' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="override" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "referenceType", namespace = "http://purl.org/dc/elements/1.1/", propOrder = {
    "title",
    "publisher",
    "type",
    "subject",
    "identifier",
    "source"
})
public class ReferenceType {
	@XmlElement
    protected String title;
    protected String publisher;
    protected String type;
    protected String subject;
    protected String identifier;
    protected String source;

    public String getTitle() {
    	return (title == null? "" : this.title);
    }
    public String getPublisher() {
    	return (publisher == null? "" : this.publisher);
    }
    public String getType() {
    	return (type == null? "" : this.type);
    }
    public String getSubject() {
    	return (subject == null? "" : this.subject);
    }
    public String getIdentifier() {
    	return (identifier == null? "" : this.identifier);
    }
    public String getSource() {
    	return (source == null? "" : this.source);
    }

}
