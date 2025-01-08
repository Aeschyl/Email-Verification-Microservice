# Email Verification Microservice  

This is a straightforward microservice that returns three markers which can be used to determine whether an email is valid. Extremely useful for the sign up stage of any application.

## Using the service
#### The base URL for accessing the email verification service is:
```sh
https://email-verification-microservice.onrender.com/api/email/verify
```

(This is render free tier so it can take >50s if the instance is inactive!, however response should be blazing fast afterwards)

#### Send a POST request to the server using cURL or Postman:
```sh
curl -X POST https://email-verification-microservice.onrender.com -H "Content-Type: application/json" -d "{\"email\": \"test@example.com\"}"
```

It is by defualt configured to run on port 8080 when [running locally](#running-locally).
&nbsp;
&nbsp;
&nbsp;
&nbsp;
## Understanding the response
Returns a JSON formatted as such:
```sh
{
    "email": "test@example.com",
    "validDomain": true,
    "disposable": false,
    "validSyntax": true
}
```

- validDomain checks hostname lookup and uses socket to establish a connection with the domain. Returns true if these two tests are a success.
- disposable checks a comprehensive list of disposable email domain names to determine whether the domain is used for disposable email addresses.
- validSyntax implements a basic regex check to ensure the email is formatted correctly.




## Running locally
To run the server on your local machine:
```sh
mvn spring-boot:run
```
