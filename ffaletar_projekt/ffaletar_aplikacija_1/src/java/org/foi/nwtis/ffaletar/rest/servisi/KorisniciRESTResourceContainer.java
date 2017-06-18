/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.rest.servisi;

import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
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
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;
import org.foi.nwtis.ffaletar.podaci.Korisnik;

/**
 * REST Web Service
 *
 * @author Filip
 */
@Path("/korisniciREST")
public class KorisniciRESTResourceContainer {

    @Context
    private UriInfo context;

    private Date pocetakObrade;
    private Date krajObrade;
    private int trajanjeObrade;

    /**
     * Creates a new instance of KorisniciRESTResourceContainer
     */
    public KorisniciRESTResourceContainer() {
    }

    /**
     * Retrieves representation of an instance of
     * org.foi.nwtis.ffaletar.rest.servisi.KorisniciRESTResourceContainer
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        setPocetakObrade();
        KorisnikBaza korisnikBaza = new KorisnikBaza();
        List<Korisnik> korisnici = korisnikBaza.dajSveKorisnike();

        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Korisnik korisnik : korisnici) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", korisnik.getID());
            job.add("korisnickoIme", korisnik.getKorisnickoIme());
            job.add("ime", korisnik.getIme());
            job.add("prezime", korisnik.getPrezime());
            job.add("mail", korisnik.getMail());
            job.add("tipKorisnika", korisnik.getTipKorisnika());
            jab.add(job);
        }
        
        setKrajObrade();
        DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "KorisniciREST/azurirajKorisnika", KonfiguracijaHelper.getHost(), getTrajanjeObrade());
        
        
        return jab.build().toString();
    }

    /**
     * POST method for creating an instance of KorisniciRESTResource
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
    public KorisniciRESTResource getKorisniciRESTResource(@PathParam("id") String id) {
        return KorisniciRESTResource.getInstance(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{korisnickoIme}")
    public String dajKorisnika(@PathParam("korisnickoIme") String korisnickoIme) {
        setPocetakObrade();
        KorisnikBaza korisnikBaza = new KorisnikBaza();
        Korisnik korisnik = korisnikBaza.dajJednogKorisnika(korisnickoIme);

        if (korisnik != null) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", korisnik.getID());
            job.add("korisnickoIme", korisnik.getKorisnickoIme());
            job.add("ime", korisnik.getIme());
            job.add("prezime", korisnik.getPrezime());
            job.add("lozinka", korisnik.getLozinka());
            job.add("mail", korisnik.getMail());
            job.add("tipKorisnika", korisnik.getTipKorisnika());

            setKrajObrade();
            DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "KorisniciREST/azurirajKorisnika", KonfiguracijaHelper.getHost(), getTrajanjeObrade());

            return job.build().toString();
        } else {
            setKrajObrade();
            DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "KorisniciREST/azurirajKorisnika", KonfiguracijaHelper.getHost(), getTrajanjeObrade());

            return "";
        }

    }

    @POST
    @Path("/dodajKorisnika")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String dodajKorisnika(String content) {
        setPocetakObrade();
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject jo = reader.readObject();
        String korisnickoIme = jo.getString("korisnickoIme");
        String ime = jo.getString("ime");
        String prezime = jo.getString("prezime");
        String lozinka = jo.getString("lozinka");
        String mail = jo.getString("mail");

        KorisnikBaza korisnikBaza = new KorisnikBaza();
        boolean korisnikDodan = korisnikBaza.dodajKorisnika(korisnickoIme, ime, prezime, lozinka, mail);

        setKrajObrade();
        DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "KorisniciREST/azurirajKorisnika", KonfiguracijaHelper.getHost(), getTrajanjeObrade());

        if (korisnikDodan) {
            return "1";
        } else {
            return "0";
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/azurirajKorisnika")
    public String azurirajKorisnika(String content) {
        setPocetakObrade();

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject jo = reader.readObject();
        int id = jo.getInt("id");
        String korisnickoIme = jo.getString("korisnickoIme");
        String ime = jo.getString("ime");
        String prezime = jo.getString("prezime");
        String lozinka = jo.getString("lozinka");
        String mail = jo.getString("mail");
        int tipKorisnika = jo.getInt("tipKorisnika");

        KorisnikBaza korisnikBaza = new KorisnikBaza();
        boolean korisnikAzuriran = korisnikBaza.azurirajKorisnika(id, korisnickoIme, ime, prezime, lozinka, mail, tipKorisnika);

        setKrajObrade();
        DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "KorisniciREST/azurirajKorisnika", KonfiguracijaHelper.getHost(), getTrajanjeObrade());

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
