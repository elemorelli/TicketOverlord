package es.ujaen.dae.ticketoverlord.resources;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class APIRedirector {
    private static final String API = "/api";
    private static final String VERSION = "/v1";

    @RequestMapping(value = "/*")
    public void redirectToVersionedAPI(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Add the API version number
        String requestURL = request.getRequestURL().toString();
        requestURL = requestURL.replaceAll(API, API + VERSION);

        // Appends the query string
        String queryString = request.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            requestURL += queryString;
        }

        response.setStatus(HttpServletResponse.SC_FOUND);
        response.sendRedirect(requestURL);
    }
}
