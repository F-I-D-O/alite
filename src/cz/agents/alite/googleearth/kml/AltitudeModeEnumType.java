//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.03.11 at 05:27:40 odp. CET 
//

package cz.agents.alite.googleearth.kml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for altitudeModeEnumType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name=&quot;altitudeModeEnumType&quot;&gt;
 *   &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}string&quot;&gt;
 *     &lt;enumeration value=&quot;clampToGround&quot;/&gt;
 *     &lt;enumeration value=&quot;relativeToGround&quot;/&gt;
 *     &lt;enumeration value=&quot;absolute&quot;/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "altitudeModeEnumType")
@XmlEnum
public enum AltitudeModeEnumType implements java.io.Serializable {

	@XmlEnumValue("clampToGround")
	CLAMP_TO_GROUND("clampToGround"), @XmlEnumValue("relativeToGround")
	RELATIVE_TO_GROUND("relativeToGround"), @XmlEnumValue("absolute")
	ABSOLUTE("absolute");
	private final String value;

	AltitudeModeEnumType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static AltitudeModeEnumType fromValue(String v) {
		for (AltitudeModeEnumType c : AltitudeModeEnumType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}