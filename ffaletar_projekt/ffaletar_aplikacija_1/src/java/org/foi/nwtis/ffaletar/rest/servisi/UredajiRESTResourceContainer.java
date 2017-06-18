/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.rest.servisi;

import java.io.StringReader;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.foi.nwtis.ffaletar.baza.DnevnikBaza;
import org.foi.nwtis.ffaletar.baza.KorisnikBaza;
import org.foi.nwtis.ffaletar.baza.UredajiBaza;
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;
import org.foi.nwtis.ffaletar.podaci.Korisnik;
import org.foi.nwtis.ffaletar.podaci.Uredjaj;

/**
 * REST Web Service
 *
 * @author Filip
 */
@Path("/uredajiREST")
public class UredajiRESTResourceContainer {

    @Context
    private UriInfo context;

    private Date pocetakObrade;
    private Date krajObrade;
    private int trajanjeObrade;

    /**
     * Creates a new instance of UredajiRESTResourceContainer
     */
    public UredajiRESTResourceContainer() {
    }

    /**
     * Retrieves representation of an instance of
     * org.foi.nwtis.ffaletar.rest.servisi.UredajiRESTResourceContainer
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        setPocetakObrade();
        UredajiBaza uredajiBaza = new UredajiBaza();
        List<Uredjaj> uredaji = uredajiBaza.dohvatiSveUredjaje();

        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Uredjaj uredjaj : uredaji) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", uredjaj.getId());
            job.add("naziv", uredjaj.getNaziv());
            job.add("latitude", uredjaj.getGeoloc().getLatitude());
            job.add("longitude", uredjaj.getGeoloc().getLongitude());
            jab.add(job);
        }
        
        
        setKrajObrade();
        DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "KorisniciREST/azurirajKorisnika", KonfiguracijaHelper.getHost(), getTrajanjeObrade(),0);

        return jab.build().toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String dajUredaj(@PathParam("id") int id) {
        setPocetakObrade();
        UredajiBaza uredajiBaza = new UredajiBaza();
        Uredjaj uredjaj = uredajiBaza.dohvatiJedanUredjaj(id);

        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", uredjaj.getId());
        job.add("naziv", uredjaj.getNaziv());
        job.add("latitude", uredjaj.getGeoloc().getLatitude());
        job.add("longitude", uredjaj.getGeoloc().getLongitude());
        job.add("status", uredjaj.getStatus());
        job.add("vrijemePromjene", uredjaj.getVrijemePromjene());
        job.add("vrijemeKreiranja", uredjaj.getVrijemeKreiranja());

        
        setKrajObrade();
        DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "KorisniciREST/azurirajKorisnika", KonfiguracijaHelper.getHost(), getTrajanjeObrade(),0);

        
        return job.build().toString();
    }

    /**
     * POST method for creating an instance of UredajiRESTResource
     *
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public UredajiRESTResource getUredajiRESTResource(@PathParam("id") String id) {
        return UredajiRESTResource.getInstance(id);
    }

    @PUT
    @Path("/dodajUredaj")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String dodajUredaj(String content) {
        setPocetakObrade();
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject jo = reader.readObject();
        String naziv = jo.getString("naziv");
        String latitude = jo.getString("latitude");
        String longitude = jo.getString("longitude");

        UredajiBaza uredajiBaza = new UredajiBaza();
        boolean uredjajDodan = uredajiBaza.dodajUredaj(naziv, latitude, longitude);

        
        setKrajObrade();
        DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "KorisniciREST/azurirajKorisnika", KonfiguracijaHelper.getHost(), getTrajanjeObrade(),0);

        
        if (uredjajDodan) {
            return "1";
        } else {
            return "0";
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/azurirajUredaj")
    public String azurirajUredaj(String content) {
        setPocetakObrade();
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject jo = reader.readObject();
        int id = jo.getInt("id");
        String naziv = jo.getString("naziv");
        String latitude = jo.getString("latitude");
        String longitude = jo.getString("longitude");

        UredajiBaza uredajiBaza = new UredajiBaza();
        boolean korisnikAzuriran = uredajiBaza.azurirajKorisnika(id, naziv, latitude, longitude);

        
        setKrajObrade();
        DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "KorisniciREST/azurirajKorisnika", KonfiguracijaHelper.getHost(), getTrajanjeObrade(),0);

        
        if (korisnikAzuriran) {
            return "1";
        } else {
            return "0";
        }
    }

    public Date getPocetakObrade() {
        return pocetakObrade;
    }

    public void setPocetakObrade() {
        this.pocetakObrade = new Date();
    }

    public Date getKrajObrade() {
        return krajObrade;
    }

    public void setKrajObrade() {
        this.krajObrade = new Date();
    }

    public int getTrajanjeObrade() {
        trajanjeObrade = (int) (getKrajObrade().getTime() - getPocetakObrade().getTime());
        return trajanjeObrade;
    }

    public void setTrajanjeObrade(int trajanjeObrade) {
        this.trajanjeObrade = trajanjeObrade;
    }
    
}
