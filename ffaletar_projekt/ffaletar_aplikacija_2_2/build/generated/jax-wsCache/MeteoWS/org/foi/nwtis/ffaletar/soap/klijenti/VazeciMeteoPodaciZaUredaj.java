
package org.foi.nwtis.ffaletar.soap.klijenti;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for vazeciMeteoPodaciZaUredaj complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vazeciMeteoPodaciZaUredaj"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IoTUredjaj" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vazeciMeteoPodaciZaUredaj", propOrder = {
    "ioTUredjaj"
})
public class VazeciMeteoPodaciZaUredaj {

    @XmlElement(name = "IoTUredjaj")
    protected int ioTUredjaj;

    /**
     * Gets the value of the ioTUredjaj property.
     * 
     */
    public int getIoTUredjaj() {
        return ioTUredjaj;
    }

    /**
     * Sets the value of the ioTUredjaj property.
     * 
     */
    public void setIoTUredjaj(int value) {
        this.ioTUredjaj = value;
    }

}
