
package org.foi.nwtis.ffaletar.soap.klijenti;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IoT_Master", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IoTMaster {


    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obrisiSveUredjajeGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.ObrisiSveUredjajeGrupe")
    @ResponseWrapper(localName = "obrisiSveUredjajeGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.ObrisiSveUredjajeGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/obrisiSveUredjajeGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/obrisiSveUredjajeGrupeResponse")
    public Boolean obrisiSveUredjajeGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ucitajSveUredjajeGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.UcitajSveUredjajeGrupe")
    @ResponseWrapper(localName = "ucitajSveUredjajeGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.UcitajSveUredjajeGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/ucitajSveUredjajeGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/ucitajSveUredjajeGrupeResponse")
    public boolean ucitajSveUredjajeGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param idUredjaj
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns org.foi.nwtis.ffaletar.soap.klijenti.StatusUredjaja
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dajStatusUredjajaGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.DajStatusUredjajaGrupe")
    @ResponseWrapper(localName = "dajStatusUredjajaGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.DajStatusUredjajaGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/dajStatusUredjajaGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/dajStatusUredjajaGrupeResponse")
    public StatusUredjaja dajStatusUredjajaGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "idUredjaj", targetNamespace = "")
        int idUredjaj);

    /**
     * 
     * @param idUredjaj
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "blokirajUredjajGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.BlokirajUredjajGrupe")
    @ResponseWrapper(localName = "blokirajUredjajGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.BlokirajUredjajGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/blokirajUredjajGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/blokirajUredjajGrupeResponse")
    public boolean blokirajUredjajGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "idUredjaj", targetNamespace = "")
        int idUredjaj);

    /**
     * 
     * @param idUredjaj
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "aktivirajUredjajGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.AktivirajUredjajGrupe")
    @ResponseWrapper(localName = "aktivirajUredjajGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.AktivirajUredjajGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/aktivirajUredjajGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/aktivirajUredjajGrupeResponse")
    public boolean aktivirajUredjajGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "idUredjaj", targetNamespace = "")
        int idUredjaj);

    /**
     * 
     * @param idUredjaj
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obrisiUredjajGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.ObrisiUredjajGrupe")
    @ResponseWrapper(localName = "obrisiUredjajGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.ObrisiUredjajGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/obrisiUredjajGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/obrisiUredjajGrupeResponse")
    public boolean obrisiUredjajGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "idUredjaj", targetNamespace = "")
        int idUredjaj);

    /**
     * 
     * @param odabraniUredjaji
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "aktivirajOdabraneUredjajeGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.AktivirajOdabraneUredjajeGrupe")
    @ResponseWrapper(localName = "aktivirajOdabraneUredjajeGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.AktivirajOdabraneUredjajeGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/aktivirajOdabraneUredjajeGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/aktivirajOdabraneUredjajeGrupeResponse")
    public boolean aktivirajOdabraneUredjajeGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "odabraniUredjaji", targetNamespace = "")
        List<Integer> odabraniUredjaji);

    /**
     * 
     * @param odabraniUredjaji
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "blokirajOdabraneUredjajeGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.BlokirajOdabraneUredjajeGrupe")
    @ResponseWrapper(localName = "blokirajOdabraneUredjajeGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.BlokirajOdabraneUredjajeGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/blokirajOdabraneUredjajeGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/blokirajOdabraneUredjajeGrupeResponse")
    public boolean blokirajOdabraneUredjajeGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "odabraniUredjaji", targetNamespace = "")
        List<Integer> odabraniUredjaji);

    /**
     * 
     * @param odabraniUredjaji
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obrisiOdabraneUredjajeGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.ObrisiOdabraneUredjajeGrupe")
    @ResponseWrapper(localName = "obrisiOdabraneUredjajeGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.ObrisiOdabraneUredjajeGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/obrisiOdabraneUredjajeGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/obrisiOdabraneUredjajeGrupeResponse")
    public boolean obrisiOdabraneUredjajeGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "odabraniUredjaji", targetNamespace = "")
        List<Integer> odabraniUredjaji);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "autenticirajGrupuIoT", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.AutenticirajGrupuIoT")
    @ResponseWrapper(localName = "autenticirajGrupuIoTResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.AutenticirajGrupuIoTResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/autenticirajGrupuIoTRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/autenticirajGrupuIoTResponse")
    public Boolean autenticirajGrupuIoT(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "aktivirajGrupuIoT", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.AktivirajGrupuIoT")
    @ResponseWrapper(localName = "aktivirajGrupuIoTResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.AktivirajGrupuIoTResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/aktivirajGrupuIoTRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/aktivirajGrupuIoTResponse")
    public Boolean aktivirajGrupuIoT(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns org.foi.nwtis.ffaletar.soap.klijenti.StatusKorisnika
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dajStatusGrupeIoT", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.DajStatusGrupeIoT")
    @ResponseWrapper(localName = "dajStatusGrupeIoTResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.DajStatusGrupeIoTResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/dajStatusGrupeIoTRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/dajStatusGrupeIoTResponse")
    public StatusKorisnika dajStatusGrupeIoT(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param iotUredjaj
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dodajUredjajGrupi", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.DodajUredjajGrupi")
    @ResponseWrapper(localName = "dodajUredjajGrupiResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.DodajUredjajGrupiResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/dodajUredjajGrupiRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/dodajUredjajGrupiResponse")
    public Boolean dodajUredjajGrupi(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "iotUredjaj", targetNamespace = "")
        Uredjaj iotUredjaj);

    /**
     * 
     * @param adresaUredjaj
     * @param nazivUredjaj
     * @param idUredjaj
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dodajNoviUredjajGrupi", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.DodajNoviUredjajGrupi")
    @ResponseWrapper(localName = "dodajNoviUredjajGrupiResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.DodajNoviUredjajGrupiResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/dodajNoviUredjajGrupiRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/dodajNoviUredjajGrupiResponse")
    public Boolean dodajNoviUredjajGrupi(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "idUredjaj", targetNamespace = "")
        int idUredjaj,
        @WebParam(name = "nazivUredjaj", targetNamespace = "")
        String nazivUredjaj,
        @WebParam(name = "adresaUredjaj", targetNamespace = "")
        String adresaUredjaj);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.util.List<org.foi.nwtis.ffaletar.soap.klijenti.Uredjaj>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dajSveUredjajeGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.DajSveUredjajeGrupe")
    @ResponseWrapper(localName = "dajSveUredjajeGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.DajSveUredjajeGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/dajSveUredjajeGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/dajSveUredjajeGrupeResponse")
    public List<Uredjaj> dajSveUredjajeGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "registrirajGrupuIoT", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.RegistrirajGrupuIoT")
    @ResponseWrapper(localName = "registrirajGrupuIoTResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.RegistrirajGrupuIoTResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/registrirajGrupuIoTRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/registrirajGrupuIoTResponse")
    public Boolean registrirajGrupuIoT(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deregistrirajGrupuIoT", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.DeregistrirajGrupuIoT")
    @ResponseWrapper(localName = "deregistrirajGrupuIoTResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.DeregistrirajGrupuIoTResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/deregistrirajGrupuIoTRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/deregistrirajGrupuIoTResponse")
    public Boolean deregistrirajGrupuIoT(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "blokirajGrupuIoT", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.BlokirajGrupuIoT")
    @ResponseWrapper(localName = "blokirajGrupuIoTResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.ffaletar.soap.klijenti.BlokirajGrupuIoTResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/blokirajGrupuIoTRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/IoT_Master/blokirajGrupuIoTResponse")
    public Boolean blokirajGrupuIoT(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

}