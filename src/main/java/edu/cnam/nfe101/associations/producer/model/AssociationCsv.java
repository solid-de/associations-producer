package edu.cnam.nfe101.associations.producer.model;

import com.opencsv.bean.CsvBindByName;

public class AssociationCsv {

    @CsvBindByName(column = "pr_nom_statutaire")
    private String nom;
    @CsvBindByName(column = "cp_adresse_code_postal")
    private String codePostal;
    @CsvBindByName(column = "cp_adresse_ville")
    private String adresseVille;
    @CsvBindByName(column = "sa_secteur_d_activit_1")
    private String secteurAct1;
    @CsvBindByName(column = "sa_secteur_d_activit_2")
    private String secteurAct2;
    @CsvBindByName(column = "sa_secteur_d_activit_3")
    private String secteurAct3;
    @CsvBindByName(column = "sa_libell_domaine_d_activit")
    private String libelleDomaineAct;
    @CsvBindByName(column = "sa_libell_secteur_d_activit")
    private String libelleSecteurAct;
    @CsvBindByName(column = "pv_public_vis")
    private String publicVise;
    @CsvBindByName(column = "sg_secteur_gographique")
    private String secteurGeo;
    @CsvBindByName(column = "cl_id_partenaire")
    private Integer idPartenaire;
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    public String getAdresseVille() {
        return adresseVille;
    }
    public void setAdresseVille(String adresseVille) {
        this.adresseVille = adresseVille;
    }
    public String getSecteurAct1() {
        return secteurAct1;
    }
    public void setSecteurAct1(String secteurAct1) {
        this.secteurAct1 = secteurAct1;
    }
    public String getSecteurAct2() {
        return secteurAct2;
    }
    public void setSecteurAct2(String secteurAct2) {
        this.secteurAct2 = secteurAct2;
    }
    public String getSecteurAct3() {
        return secteurAct3;
    }
    public void setSecteurAct3(String secteurAct3) {
        this.secteurAct3 = secteurAct3;
    }
    public String getLibelleDomaineAct() {
        return libelleDomaineAct;
    }
    public void setLibelleDomaineAct(String libelleDomaineAct) {
        this.libelleDomaineAct = libelleDomaineAct;
    }
    public String getLibelleSecteurAct() {
        return libelleSecteurAct;
    }
    public void setLibelleSecteurAct(String libelleSecteurAct) {
        this.libelleSecteurAct = libelleSecteurAct;
    }
    public String getPublicVise() {
        return publicVise;
    }
    public void setPublicVise(String publicVise) {
        this.publicVise = publicVise;
    }
    public String getSecteurGeo() {
        return secteurGeo;
    }
    public void setSecteurGeo(String secteurGeo) {
        this.secteurGeo = secteurGeo;
    }
    public Integer getIdPartenaire() {
        return idPartenaire;
    }
    public void setIdPartenaire(Integer idPartenaire) {
        this.idPartenaire = idPartenaire;
    }


    
}
