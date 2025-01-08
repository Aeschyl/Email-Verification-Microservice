package com.example.demo.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Service
public class DisposableEmailChecker {
    @Cacheable("domains")
    public Set<String> getDisposableDomains() {
        Set<String> domains = new HashSet<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/disposable_domains.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String domain = line.trim().toLowerCase();
                if (!domain.isEmpty()) {
                    domains.add(domain);
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to get typical disposable domain names: " + e.getMessage());
        }
        return domains;
    }
    
    public boolean isDisposable(String domain) {
        if (domain == null || domain.isEmpty()) {
            return false;
        }
        return getDisposableDomains().contains(domain.toLowerCase());
    }
}
