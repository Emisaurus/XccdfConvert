//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.09.02 at 06:54:38 PM CDT 
//


package checklist;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}STIG_INFO"/>
 *         &lt;element ref="{}VULN" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "stiginfo",
    "vuln"
})
@XmlRootElement(name = "iSTIG")
public class ISTIG {

    @XmlElement(name = "STIG_INFO", required = true)
    protected STIGINFO stiginfo;
    @XmlElement(name = "VULN", required = true)
    protected List<VULN> vuln;
    
    public ISTIG()
    {
    	stiginfo = new STIGINFO();
    	vuln = new ArrayList<VULN>();
    }

    /**
     * Gets the value of the stiginfo property.
     * 
     * @return
     *     possible object is
     *     {@link STIGINFO }
     *     
     */
    public STIGINFO getSTIGINFO() {
        return stiginfo;
    }

    /**
     * Sets the value of the stiginfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link STIGINFO }
     *     
     */
    public void setSTIGINFO(STIGINFO value) {
        this.stiginfo = value;
    }

    /**
     * Gets the value of the vuln property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vuln property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVULN().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VULN }
     * 
     * 
     */
    public List<VULN> getVULN() {
        if (vuln == null) {
            vuln = new ArrayList<VULN>();
        }
        return this.vuln;
    }

}
