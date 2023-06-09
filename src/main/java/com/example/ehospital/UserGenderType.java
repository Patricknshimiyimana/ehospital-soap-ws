//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.10 at 10:57:16 PM CAT 
//


package com.example.ehospital;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserGenderType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="UserGenderType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Male"/&gt;
 *     &lt;enumeration value="Female"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "UserGenderType")
@XmlEnum
public enum UserGenderType {

    @XmlEnumValue("Male")
    MALE("Male"),
    @XmlEnumValue("Female")
    FEMALE("Female");
    private final String value;

    UserGenderType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UserGenderType fromValue(String v) {
        for (UserGenderType c: UserGenderType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
