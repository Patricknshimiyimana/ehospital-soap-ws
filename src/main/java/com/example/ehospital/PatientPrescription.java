//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.10 at 10:57:16 PM CAT 
//


package com.example.ehospital;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientPrescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientPrescription"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PatientUsername" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="diseaseName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Medecine" type="{http://example.com/ehospital}MedecineDetails"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientPrescription", propOrder = {
    "patientUsername",
    "diseaseName",
    "medecine"
})
public class PatientPrescription {

    @XmlElement(name = "PatientUsername", required = true)
    protected String patientUsername;
    @XmlElement(required = true)
    protected String diseaseName;
    @XmlElement(name = "Medecine", required = true)
    protected MedecineDetails medecine;

    /**
     * Gets the value of the patientUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientUsername() {
        return patientUsername;
    }

    /**
     * Sets the value of the patientUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientUsername(String value) {
        this.patientUsername = value;
    }

    /**
     * Gets the value of the diseaseName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiseaseName() {
        return diseaseName;
    }

    /**
     * Sets the value of the diseaseName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiseaseName(String value) {
        this.diseaseName = value;
    }

    /**
     * Gets the value of the medecine property.
     * 
     * @return
     *     possible object is
     *     {@link MedecineDetails }
     *     
     */
    public MedecineDetails getMedecine() {
        return medecine;
    }

    /**
     * Sets the value of the medecine property.
     * 
     * @param value
     *     allowed object is
     *     {@link MedecineDetails }
     *     
     */
    public void setMedecine(MedecineDetails value) {
        this.medecine = value;
    }

}
