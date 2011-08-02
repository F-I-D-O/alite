//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.03.11 at 05:27:40 odp. CET 
//

package cz.agents.alite.googleearth.kml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for MultiGeometryType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;MultiGeometryType&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://earth.google.com/kml/2.2}AbstractGeometryType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref=&quot;{http://earth.google.com/kml/2.2}AbstractGeometryGroup&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiGeometryType", propOrder = { "abstractGeometryGroup" })
public class MultiGeometryType extends AbstractGeometryType implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8019895675588430660L;
	@XmlElementRef(name = "AbstractGeometryGroup", namespace = "http://earth.google.com/kml/2.2", type = JAXBElement.class)
	protected List<JAXBElement<? extends AbstractGeometryType>> abstractGeometryGroup;

	/**
	 * Gets the value of the abstractGeometryGroup property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the abstractGeometryGroup property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAbstractGeometryGroup().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link JAXBElement }{@code <}{@link LinearRingType }{@code >}
	 * {@link JAXBElement }{@code <}{@link LineStringType }{@code >}
	 * {@link JAXBElement }{@code <}{@link MultiGeometryType }{@code >}
	 * {@link JAXBElement }{@code <}{@link PointType }{@code >}
	 * {@link JAXBElement }{@code <}{@link AbstractGeometryType }{@code >}
	 * {@link JAXBElement }{@code <}{@link PolygonType }{@code >}
	 * {@link JAXBElement }{@code <}{@link ModelType }{@code >}
	 * 
	 * 
	 */
	public List<JAXBElement<? extends AbstractGeometryType>> getAbstractGeometryGroup() {
		if (abstractGeometryGroup == null) {
			abstractGeometryGroup = new ArrayList<JAXBElement<? extends AbstractGeometryType>>();
		}
		return this.abstractGeometryGroup;
	}

}