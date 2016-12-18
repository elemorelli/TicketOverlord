package es.ujaen.dae.ticketoverlord.client.utilities;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;

abstract class ApiTemplate {
    final static String BASE_URL = "http://localhost:8080/ticketoverlord/api/v1/";
    private final static String ADMIN_USERNAME = "admin";
    private final static String ADMIN_PASSWORD = "admin";

    static HttpEntity<Object> getRequest(Object data) {
        return getRequest(data, ADMIN_USERNAME, ADMIN_PASSWORD);
    }

    static HttpEntity<Object> getRequest(Object data, String user, String pass) {
        String plainCreds = user + ":" + pass;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.add("Content-Type", "application/json");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(data, headers);
    }
}
