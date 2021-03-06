
package org.foi.nwtis.ffaletar.soap.klijenti;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for meteoPodaciUVremenskomRazdoblju complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="meteoPodaciUVremenskomRazdoblju"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IoTUredjaj" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="pocetak" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="kraj" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "meteoPodaciUVremenskomRazdoblju", propOrder = {
    "ioTUredjaj",
    "pocetak",
    "kraj"
})
public class MeteoPodaciUVremenskomRazdoblju {

    @XmlElement(name = "IoTUredjaj")
    protected int ioTUredjaj;
    protected long pocetak;
    protected long kraj;

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
     * Gets the value of the pocetak property.
     * 
     */
    public long getPocetak() {
        return pocetak;
    }

    /**
     * Sets the value of the pocetak property.
     * 
     */
    public void setPocetak(long value) {
        this.pocetak = value;
    }

    /**
     * Gets the value of the kraj property.
     * 
     */
    public long getKraj() {
        return kraj;
    }

    /**
     * Sets the value of the kraj property.
     * 
     */
    public void setKraj(long value) {
        this.kraj = value;
    }

}
