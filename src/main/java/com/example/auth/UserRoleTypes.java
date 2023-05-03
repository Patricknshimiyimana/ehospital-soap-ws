//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.03 at 11:53:57 PM CAT 
//


package com.example.auth;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserRoleTypes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="UserRoleTypes"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Patient"/&gt;
 *     &lt;enumeration value="Physician"/&gt;
 *     &lt;enumeration value="Pharmacist"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "UserRoleTypes")
@XmlEnum
public enum UserRoleTypes {

    @XmlEnumValue("Patient")
    PATIENT("Patient"),
    @XmlEnumValue("Physician")
    PHYSICIAN("Physician"),
    @XmlEnumValue("Pharmacist")
    PHARMACIST("Pharmacist");
    private final String value;

    UserRoleTypes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UserRoleTypes fromValue(String v) {
        for (UserRoleTypes c: UserRoleTypes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}