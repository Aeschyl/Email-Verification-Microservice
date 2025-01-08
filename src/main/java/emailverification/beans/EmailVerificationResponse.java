package emailverification.beans;

public class EmailVerificationResponse {
    private String email;
    private boolean isValidSyntax;
    private boolean isValidDomain;
    private boolean isDisposable;

    public EmailVerificationResponse() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValidSyntax() {
        return isValidSyntax;
    }

    public void setIsValidSyntax(boolean isValid) {
        this.isValidSyntax = isValid;
    }

    public boolean isValidDomain() {
        return isValidDomain;
    }

    public void setIsValidDomain(boolean isReal) {
        this.isValidDomain = isReal;
    }

    public boolean isDisposable() {
        return isDisposable;
    }

    public void setIsDisposable(boolean isDisposable) {
        this.isDisposable = isDisposable;
    }
}
