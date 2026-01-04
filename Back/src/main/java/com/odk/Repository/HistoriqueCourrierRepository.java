package com.odk.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.odk.Entity.HistoriqueCourrier;

public interface HistoriqueCourrierRepository extends JpaRepository<HistoriqueCourrier,Long> {
    
    List<HistoriqueCourrier> findByCourrierId(Long courrierId); //Suivre toutes les modifications sur un courrier donné ...
    List<HistoriqueCourrier> findByEntiteId(Long entiteId);     //Toutes les actions liées à une entité ...

    // Vérifie si l’utilisateur a déjà agi sur ce courrier
    static boolean existsByCourrierIdAndUtilisateurId(Long courrierId, Long utilisateurId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'existsByCourrierIdAndUtilisateurId'");}
    // Historique complet d’un courrier
    List<HistoriqueCourrier> findByCourrierIdOrderByDateActionAsc(Long courrierId);
}
