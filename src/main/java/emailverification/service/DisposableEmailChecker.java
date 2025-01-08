package emailverification.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Service
public class DisposableEmailChecker {

    @Cacheable("domains")
    private Set<String> loadDisposableDomains(String filePath) {
        Set<String> domains = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(filePath)))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String domain = line.trim().toLowerCase();
                if (!domain.isEmpty()) {
                    domains.add(domain);
                }
            }

        } catch (IOException e) {
            System.err.println("Unable to load disposable domains: " + e.getMessage());
        }

        return domains;
    }

    public boolean isDisposable(String domain) {
        if (domain == null || domain.isEmpty()) {
            return false;
        }
        return loadDisposableDomains("/disposable_domains.txt").contains(domain.toLowerCase());
    }
}
