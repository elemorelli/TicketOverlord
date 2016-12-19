package es.ujaen.dae.ticketoverlord.client.utilities;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

abstract class ApiTemplate {
    private final static String LOCALHOST = "localhost";
    private final static Integer PORT = 8080;
    private final static String SCHEME = "http";
    final static String BASE_URL = SCHEME + "://" + LOCALHOST + ":" + PORT + "/ticketoverlord/api/v1/";

    static RestTemplate getTemplate(String user, String pass) {
        HttpHost host = new HttpHost(LOCALHOST, PORT, SCHEME);
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider(user, pass)).useSystemProperties().build();
        HttpComponentsClientHttpRequestFactory requestFactory = new DigestAuthRequestFactory(host, client);

        return new RestTemplate(requestFactory);
    }

    private static CredentialsProvider provider(String user, String pass) {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user, pass);
        provider.setCredentials(AuthScope.ANY, credentials);
        return provider;
    }
}
