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
 *         &lt;element ref="{}ROLE"/>
 *         &lt;element ref="{}ASSET_TYPE"/>
 *         &lt;element ref="{}HOST_NAME"/>
 *         &lt;element ref="{}HOST_IP"/>
 *         &lt;element ref="{}HOST_MAC"/>
 *         &lt;element ref="{}HOST_GUID" minOccurs="0"/>
 *         &lt;element ref="{}HOST_FQDN"/>
 *         &lt;element ref="{}TARGET_COMMENT" minOccurs="0"/>
 *         &lt;element ref="{}TECH_AREA"/>
 *         &lt;element ref="{}TARGET_KEY"/>
 *         &lt;element ref="{}STIG_GUID" minOccurs="0"/>
 *         &lt;element ref="{}WEB_OR_DATABASE"/>
 *         &lt;element ref="{}WEB_DB_SITE"/>
 *         &lt;element ref="{}WEB_DB_INSTANCE"/>
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
    "role",
    "assettype",
    "hostname",
    "hostip",
    "hostmac",
    "hostguid",
    "hostfqdn",
    "targetcomment",
    "techarea",
    "targetkey",
    "stigguid",
    "webordatabase",
    "webdbsite",
    "webdbinstance"
})
@XmlRootElement(name = "ASSET")
public class ASSET {

    @XmlElement(name = "ROLE", required = true)
    protected String role;
    @XmlElement(name = "ASSET_TYPE", required = true)
    protected String assettype;
    @XmlElement(name = "HOST_NAME", required = true)
    protected String hostname;
    @XmlElement(name = "HOST_IP", required = true)
    protected String hostip;
    @XmlElement(name = "HOST_MAC", required = true)
    protected String hostmac;
    @XmlElement(name = "HOST_GUID")
    protected String hostguid;
    @XmlElement(name = "HOST_FQDN", required = true)
    protected String hostfqdn;
    @XmlElement(name = "TARGET_COMMENT")
    protected String targetcomment;
    @XmlElement(name = "TECH_AREA", required = true)
    protected String techarea;
    @XmlElement(name = "TARGET_KEY", required = true)
    protected String targetkey;
    @XmlElement(name = "STIG_GUID")
    protected String stigguid;
    @XmlElement(name = "WEB_OR_DATABASE")
    protected boolean webordatabase;
    @XmlElement(name = "WEB_DB_SITE", required = true)
    protected String webdbsite;
    @XmlElement(name = "WEB_DB_INSTANCE", required = true)
    protected String webdbinstance;

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getROLE() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setROLE(String value) {
        this.role = value;
    }

    /**
     * Gets the value of the assettype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getASSETTYPE() {
        return assettype;
    }

    /**
     * Sets the value of the assettype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setASSETTYPE(String value) {
        this.assettype = value;
    }

    /**
     * Gets the value of the hostname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHOSTNAME() {
        return hostname;
    }

    /**
     * Sets the value of the hostname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHOSTNAME(String value) {
        this.hostname = value;
    }

    /**
     * Gets the value of the hostip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHOSTIP() {
        return hostip;
    }

    /**
     * Sets the value of the hostip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHOSTIP(String value) {
        this.hostip = value;
    }

    /**
     * Gets the value of the hostmac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHOSTMAC() {
        return hostmac;
    }

    /**
     * Sets the value of the hostmac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHOSTMAC(String value) {
        this.hostmac = value;
    }

    /**
     * Gets the value of the hostguid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHOSTGUID() {
        return hostguid;
    }

    /**
     * Sets the value of the hostguid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHOSTGUID(String value) {
        this.hostguid = value;
    }

    /**
     * Gets the value of the hostfqdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHOSTFQDN() {
        return hostfqdn;
    }

    /**
     * Sets the value of the hostfqdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHOSTFQDN(String value) {
        this.hostfqdn = value;
    }

    /**
     * Gets the value of the targetcomment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTARGETCOMMENT() {
        return targetcomment;
    }

    /**
     * Sets the value of the targetcomment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTARGETCOMMENT(String value) {
        this.targetcomment = value;
    }

    /**
     * Gets the value of the techarea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTECHAREA() {
        return techarea;
    }

    /**
     * Sets the value of the techarea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTECHAREA(String value) {
        this.techarea = value;
    }

    /**
     * Gets the value of the targetkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTARGETKEY() {
        return targetkey;
    }

    /**
     * Sets the value of the targetkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTARGETKEY(String value) {
        this.targetkey = value;
    }

    /**
     * Gets the value of the stigguid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTIGGUID() {
        return stigguid;
    }

    /**
     * Sets the value of the stigguid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTIGGUID(String value) {
        this.stigguid = value;
    }

    /**
     * Gets the value of the webordatabase property.
     * 
     */
    public boolean isWEBORDATABASE() {
        return webordatabase;
    }

    /**
     * Sets the value of the webordatabase property.
     * 
     */
    public void setWEBORDATABASE(boolean value) {
        this.webordatabase = value;
    }

    /**
     * Gets the value of the webdbsite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWEBDBSITE() {
        return webdbsite;
    }

    /**
     * Sets the value of the webdbsite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWEBDBSITE(String value) {
        this.webdbsite = value;
    }

    /**
     * Gets the value of the webdbinstance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWEBDBINSTANCE() {
        return webdbinstance;
    }

    /**
     * Sets the value of the webdbinstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWEBDBINSTANCE(String value) {
        this.webdbinstance = value;
    }

}