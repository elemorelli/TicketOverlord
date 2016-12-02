package es.ujaen.dae.ticketoverlord.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/")
public class APIRedirector {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public void redirectToLastApi() throws NoSuchMethodException {
    }
}
