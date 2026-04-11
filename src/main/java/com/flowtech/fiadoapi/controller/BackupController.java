package com.flowtech.fiadoapi.controller;

import com.flowtech.fiadoapi.service.BackupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/backup")
@AllArgsConstructor
public class BackupController {

    private final BackupService backupService;

    @PreAuthorize("hasAnyRole('CLIENTE')")
    @PostMapping("/sqlite")
    public ResponseEntity<?> generateBackup(
            @RequestParam("file") MultipartFile file,
            @RequestParam("empresaNome") String empresaNome,
            @RequestParam("userId") String userId
    ) {
        try {

            System.out.println("ARQUIVO RECEBIDO: " + file.getOriginalFilename());
            System.out.println("TAMANHO: " + file.getSize());

            String fileName = backupService.createBackup(file, empresaNome, userId);

            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "fileName", fileName
            ));

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "ERROR",
                    "message", "Falha ao gerar backup: " + e.getMessage()
            ));
        }
    }
}