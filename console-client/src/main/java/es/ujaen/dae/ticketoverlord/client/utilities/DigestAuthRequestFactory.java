package es.ujaen.dae.ticketoverlord.client.utilities;

import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.net.URI;
import java.util.Random;

public class DigestAuthRequestFactory extends HttpComponentsClientHttpRequestFactory {
    private HttpHost host;

    DigestAuthRequestFactory(HttpHost host, CloseableHttpClient client) {
        super(client);
        this.host = host;
    }

    @Override
    protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
        return createHttpContext();
    }

    private HttpContext createHttpContext() {
        AuthCache authCache = new BasicAuthCache();
        DigestScheme digestAuth = new DigestScheme();
        digestAuth.overrideParamter("realm", "TICKET_OVERLORD");
        digestAuth.overrideParamter("nonce", Long.toString(new Random().nextLong(), 36));
        authCache.put(host, digestAuth);

        BasicHttpContext localcontext = new BasicHttpContext();
        localcontext.setAttribute(HttpClientContext.AUTH_CACHE, authCache);
        return localcontext;
    }
}