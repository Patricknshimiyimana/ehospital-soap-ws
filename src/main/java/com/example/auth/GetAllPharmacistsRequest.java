//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.04 at 01:49:33 AM CAT 
//


package com.example.auth;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AuthorizationHeader" type="{http://example.com/auth}AuthorizationHeader"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "authorizationHeader"
})
@XmlRootElement(name = "GetAllPharmacistsRequest")
public class GetAllPharmacistsRequest {

    @XmlElement(name = "AuthorizationHeader", required = true)
    protected AuthorizationHeader authorizationHeader;

    /**
     * Gets the value of the authorizationHeader property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorizationHeader }
     *     
     */
    public AuthorizationHeader getAuthorizationHeader() {
        return authorizationHeader;
    }

    /**
     * Sets the value of the authorizationHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorizationHeader }
     *     
     */
    public void setAuthorizationHeader(AuthorizationHeader value) {
        this.authorizationHeader = value;
    }

}
