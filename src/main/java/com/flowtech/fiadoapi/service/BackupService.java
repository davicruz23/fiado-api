package com.flowtech.fiadoapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BackupService {

    @Value("${app.db.path}")
    private String dbPath;

    @Value("${app.backup.dir}")
    private String backupDir;

    public String createBackup(MultipartFile file, String empresaNome, String userId) throws Exception {

        File dir = new File(backupDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

        String safeEmpresa = empresaNome != null ? sanitize(empresaNome) : "sem_nome";

        String fileName = "backup_" +
                safeEmpresa + "_" +
                userId + "_" +
                timestamp + ".db";

        Path target = Paths.get(backupDir, fileName);

        System.out.println("SALVANDO EM: " + target);

        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    private String sanitize(String text) {
        if (text == null) return "empresa";

        return text.toLowerCase()
                .replace(" ", "_")
                .replaceAll("[^a-z0-9_]", "")
                .replaceAll("_+", "_");
    }
}