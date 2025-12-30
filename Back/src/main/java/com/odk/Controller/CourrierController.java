package com.odk.Controller;



import java.io.IOException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.odk.Entity.Courrier;
import com.odk.Entity.Utilisateur;
import com.odk.Service.Interface.Service.CourrierService;
import com.odk.dto.CourrierDTO;

@RestController
@RequestMapping("/api/courriers")
public class CourrierController {

    private final CourrierService courrierService;

    public CourrierController(CourrierService courrierService) {
        this.courrierService = courrierService;
    }

    /* ======================================================
     *  PARTIE 1 : RÉCEPTION / ENREGISTREMENT DU COURRIER
     * ====================================================== */
    @PostMapping("/reception")
    public ResponseEntity<Courrier> receptionCourrier(
            @RequestParam String numero,
            @RequestParam String objet,
            @RequestParam String expediteur,
            @RequestParam Long directionId,
            @RequestParam(required = false) MultipartFile fichier
    ) throws IOException {

        CourrierDTO dto = new CourrierDTO();
        dto.setNumero(numero);
        dto.setObjet(objet);
        dto.setExpediteur(expediteur);
        dto.setDirectionId(directionId);
        dto.setFichier(fichier);

        Courrier courrier = courrierService.enregistrerCourrier(dto);
        return ResponseEntity.ok(courrier);
    }

    /* ======================================================
     *  PARTIE 2 : IMPUTATION PAR LE DIRECTEUR
     * ====================================================== */
    @PutMapping("/{id}/imputer")
    public ResponseEntity<Courrier> imputerCourrier(
            @PathVariable Long id,
<<<<<<< HEAD
            @RequestParam Long entiteCibleId
    ) {
        Courrier courrier = courrierService.imputerCourrier(id, entiteCibleId);
=======
            @RequestParam Long entiteCibleId,
            @RequestAttribute Utilisateur utilisateurCible
    ) {
        Courrier courrier = courrierService.imputerCourrier(id, entiteCibleId, utilisateurCible);
>>>>>>> c0b91f8 (ARCHIVE -3)
        return ResponseEntity.ok(courrier);
    }

    /* ======================================================
     *  PARTIE 3 : OUVERTURE / DÉBUT DE TRAITEMENT
     * ====================================================== */
  @GetMapping("/{id}/ouvrir")
   public ResponseEntity<InputStreamResource> ouvrirCourrier(
        @PathVariable Long id,
        @RequestAttribute Utilisateur utilisateur
      ) throws IOException {
    return courrierService.ouvrirCourrier(id, utilisateur);
    }

    /* ======================================================
     *  PARTIE 4 : ARCHIVAGE
     * ====================================================== */
    @PutMapping("/{id}/archiver")
    public ResponseEntity<Void> archiverCourrier(
            @PathVariable Long id,
            @RequestAttribute Utilisateur utilisateur
    ) {
        courrierService.archiverCourrier(id, utilisateur);
        return ResponseEntity.ok().build();
    }

    /* ======================================================
     *  PARTIE 5 : LISTE DES COURRIERS ACTIFS
     * ====================================================== */
    @GetMapping("/actifs/{entiteId}")
    public ResponseEntity<List<Courrier>> courriersActifs(@PathVariable Long entiteId) {
        List<Courrier> courriers = courrierService.courriersActifs(entiteId);
        return ResponseEntity.ok(courriers);
    }

    /* ======================================================
     *  PARTIE 6 : LISTE DES COURRIERS ARCHIVÉS
     * ====================================================== */
    @GetMapping("/archives/{entiteId}")
    public ResponseEntity<List<Courrier>> courriersArchives(@PathVariable Long entiteId) {
        List<Courrier> courriers = courrierService.courriersArchives(entiteId);
        return ResponseEntity.ok(courriers);
    }
}