package patterns.loans.service.communication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class AccountServiceClient {
    private final RestTemplate restTemplate;
    private final String accountServiceDomen = "http://https://tomcat.sonya.jij.li/core";


    public boolean checkAccountForLoan(String accountId, String authHeader) {
        String url = accountServiceDomen + "/accounts/check/{" + accountId + "}" ;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Boolean> response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity, Boolean.class
            );
            return Boolean.TRUE.equals(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("account service error: " + e.getMessage());
        }
    }

    public void OperationAutodebitting(String accountId, int sum) {
        String url = UriComponentsBuilder.fromHttpUrl(accountServiceDomen + "/operations/autodebt/{accountId}")
                .queryParam("sum", sum)
                .buildAndExpand(accountId)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            restTemplate.exchange(url, HttpMethod.GET, requestEntity, Void.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("account service on autodebt error: " + e.getMessage());
        }
    }
}
