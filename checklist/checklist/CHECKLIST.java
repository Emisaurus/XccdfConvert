//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.09.02 at 06:54:38 PM CDT 
//


package checklist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import xccdfClasses.IdType;


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
 *         &lt;element ref="{}ASSET"/>
 *         &lt;element ref="{}STIGS"/>
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
    "asset",
    "stigs"
})
@XmlRootElement(name = "CHECKLIST")
public class CHECKLIST {

    @XmlElement(name = "ASSET", required = true)
    protected ASSET asset;
    @XmlElement(name = "STIGS", required = true)
    protected STIGS stigs;

    public CHECKLIST() {
    	asset = new ASSET();
    	stigs = new STIGS();
	}

	/**
     * Gets the value of the asset property.
     * 
     * @return
     *     possible object is
     *     {@link ASSET }
     *     
     */
    public ASSET getASSET() {
    	if (asset == null) {
            asset = new ASSET();
        }
        return this.asset;
    }

    /**
     * Sets the value of the asset property.
     * 
     * @param value
     *     allowed object is
     *     {@link ASSET }
     *     
     */
    public void setASSET(ASSET value) {
        this.asset = value;
    }

    /**
     * Gets the value of the stigs property.
     * 
     * @return
     *     possible object is
     *     {@link STIGS }
     *     
     */
    public STIGS getSTIGS() {
    	if (stigs == null) {
    		stigs = new STIGS();
        }
        return this.stigs;
    }

    /**
     * Sets the value of the stigs property.
     * 
     * @param value
     *     allowed object is
     *     {@link STIGS }
     *     
     */
    public void setSTIGS(STIGS value) {
        this.stigs = value;
    }

}
