//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.03 at 11:53:57 PM CAT 
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
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="User" type="{http://example.com/auth}PatientDetails"/&gt;
 *         &lt;element name="selectedPhysician" type="{http://example.com/auth}PhysicianDetails"/&gt;
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
    "message",
    "user",
    "selectedPhysician"
})
@XmlRootElement(name = "SelectPhysicianResponse")
public class SelectPhysicianResponse {

    @XmlElement(required = true)
    protected String message;
    @XmlElement(name = "User", required = true)
    protected PatientDetails user;
    @XmlElement(required = true)
    protected PhysicianDetails selectedPhysician;

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link PatientDetails }
     *     
     */
    public PatientDetails getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientDetails }
     *     
     */
    public void setUser(PatientDetails value) {
        this.user = value;
    }

    /**
     * Gets the value of the selectedPhysician property.
     * 
     * @return
     *     possible object is
     *     {@link PhysicianDetails }
     *     
     */
    public PhysicianDetails getSelectedPhysician() {
        return selectedPhysician;
    }

    /**
     * Sets the value of the selectedPhysician property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhysicianDetails }
     *     
     */
    public void setSelectedPhysician(PhysicianDetails value) {
        this.selectedPhysician = value;
    }

}