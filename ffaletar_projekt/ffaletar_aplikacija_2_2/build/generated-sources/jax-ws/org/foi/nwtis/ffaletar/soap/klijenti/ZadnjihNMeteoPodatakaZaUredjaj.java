
package org.foi.nwtis.ffaletar.soap.klijenti;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for zadnjihNMeteoPodatakaZaUredjaj complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zadnjihNMeteoPodatakaZaUredjaj"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IoTUredjaj" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="brojMeteoPodataka" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zadnjihNMeteoPodatakaZaUredjaj", propOrder = {
    "ioTUredjaj",
    "brojMeteoPodataka"
})
public class ZadnjihNMeteoPodatakaZaUredjaj {

    @XmlElement(name = "IoTUredjaj")
    protected int ioTUredjaj;
    protected int brojMeteoPodataka;

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

    /**
     * Gets the value of the brojMeteoPodataka property.
     * 
     */
    public int getBrojMeteoPodataka() {
        return brojMeteoPodataka;
    }

    /**
     * Sets the value of the brojMeteoPodataka property.
     * 
     */
    public void setBrojMeteoPodataka(int value) {
        this.brojMeteoPodataka = value;
    }

}
