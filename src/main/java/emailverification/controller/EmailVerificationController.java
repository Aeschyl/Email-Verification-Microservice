package emailverification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emailverification.beans.EmailVerificationRequest;
import emailverification.beans.EmailVerificationResponse;
import emailverification.service.DisposableEmailChecker;
import emailverification.service.EmailVerifier;

@RestController
@RequestMapping("/api/email")
public class EmailVerificationController {

    @Autowired
    private EmailVerifier emailVerifier;

    @Autowired
    private DisposableEmailChecker disposableEmailChecker;

    @PostMapping("/verify")
    public EmailVerificationResponse verifyEmail(@RequestBody EmailVerificationRequest request) {
        String email = request.getEmail();
        String domain = email.substring(email.indexOf("@") + 1);
        System.out.println("Extracted domain: " + domain); // Debug log
        EmailVerificationResponse response = new EmailVerificationResponse();
        response.setEmail(email);

        if (emailVerifier.isValidSyntax(email)) {
            response.setIsValidSyntax(true);
        }

        if (emailVerifier.isValidDomain(domain)) {
            response.setIsValidDomain(true);
        }

        if (disposableEmailChecker.isDisposable(domain)) {
            response.setIsDisposable(true);
        }

        return response;
    }
}
