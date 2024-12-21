package edu.cnam.nfe101.associations.producer.service;

import edu.cnam.nfe101.associations.producer.model.AssociationCsv;
import edu.cnam.nfe101.associations.producer.model.AssociationJson;

public class AssociationMapper {
    
    public static AssociationJson toJson(AssociationCsv csv) {
        AssociationJson json = new AssociationJson();

        json.setName(csv.getNom());
        json.setPostalCode(csv.getCodePostal());
        json.setCity(csv.getAdresseVille());
        json.setAct1(csv.getSecteurAct1());
        json.setAct2(csv.getSecteurAct2());
        json.setAct3(csv.getSecteurAct3());
        json.setActDomain(csv.getLibelleDomaineAct());
        json.setActSectorLabel(csv.getLibelleSecteurAct());
        json.setAudience(csv.getPublicVise());
        json.setGeoSector(csv.getSecteurGeo());
        json.setExternalId(csv.getIdPartenaire());

        return json;
    }
}
