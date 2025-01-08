package emailverification.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;
import java.util.regex.Pattern;

@Service
public class EmailVerifier {

    // I looked this up haha - "pattern matcher for email regex"
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public boolean isValidDomain(String domain) {

        // Debug the domain validation
        boolean isDomainValid = checkDomain(domain);
        System.out.println("Is domain valid for email domain '" + domain + "': " + isDomainValid);

        // Debug the mail server availability
        boolean isMailServerValid = isMailServerAvailable(domain, 1000);
        System.out.println("Is mail server available for domain '" + domain + "': " + isMailServerValid);

        return isDomainValid && isMailServerValid;
    }

    public boolean isValidSyntax(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        System.out.println("Validating email: " + email);

        return pattern.matcher(email).matches();
    }

    public boolean checkDomain(String domain) {
        try {
            InetAddress.getByName(domain);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }

    public boolean isMailServerAvailable(String domain, int timeout) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(domain, 80), timeout);
            return true;
        } catch (UnknownHostException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
