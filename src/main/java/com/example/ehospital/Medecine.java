//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.09 at 06:43:20 PM CAT 
//


package com.example.ehospital;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Medecine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Medecine"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="medName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="medPrice" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Medecine", propOrder = {
    "medName",
    "medPrice",
    "expirationDate"
})
public class Medecine {

    @XmlElement(required = true)
    protected String medName;
    @XmlElement(required = true)
    protected String medPrice;
    @XmlElement(required = true)
    protected String expirationDate;

    /**
     * Gets the value of the medName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedName() {
        return medName;
    }

    /**
     * Sets the value of the medName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedName(String value) {
        this.medName = value;
    }

    /**
     * Gets the value of the medPrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedPrice() {
        return medPrice;
    }

    /**
     * Sets the value of the medPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedPrice(String value) {
        this.medPrice = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpirationDate(String value) {
        this.expirationDate = value;
    }

}