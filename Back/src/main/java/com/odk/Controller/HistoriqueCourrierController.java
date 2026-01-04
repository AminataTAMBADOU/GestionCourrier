package com.odk.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.odk.Entity.HistoriqueCourrier;
import com.odk.Service.Interface.Service.HistoriqueCourrierService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/historique")
@RequiredArgsConstructor
public class HistoriqueCourrierController {

    private final HistoriqueCourrierService historiqueService;

    @GetMapping("/courrier/{courrierId}")
    public ResponseEntity<List<HistoriqueCourrierDTO>> getHistorique(
            @PathVariable Long courrierId,
            @RequestParam Long utilisateurId
    ) {
        List<HistoriqueCourrier> historiques =
                historiqueService.getHistoriqueCourrierAutorise(
                        courrierId, utilisateurId);

        List<HistoriqueCourrierDTO> dtos = historiques.stream()
                .map(this::mapToDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }
}
