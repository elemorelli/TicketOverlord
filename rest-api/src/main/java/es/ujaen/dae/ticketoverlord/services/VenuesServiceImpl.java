package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.daos.VenueDAO;
import es.ujaen.dae.ticketoverlord.dtos.VenueDTO;
import es.ujaen.dae.ticketoverlord.dtos.ZoneDTO;
import es.ujaen.dae.ticketoverlord.exceptions.services.venues.NoVenueFoundException;
import es.ujaen.dae.ticketoverlord.models.Venue;
import es.ujaen.dae.ticketoverlord.models.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("VenuesService")
public class VenuesServiceImpl implements VenuesService {
    @Autowired
    private VenueDAO venuesDAO;

    @Override
    public List<VenueDTO> getVenues() {

        List<VenueDTO> venueDTOs = new ArrayList<>();
        for (Venue venue : venuesDAO.selectAllVenues()) {
            venueDTOs.add(new VenueDTO(venue));
        }
        return venueDTOs;
    }

    @Override
    public VenueDTO getVenue(Integer venueId) {
        return new VenueDTO(venuesDAO.selectVenueById(venueId));
    }

    @Override
    public VenueDTO addNewVenue(VenueDTO venueDTO) {

        Venue venue = new Venue();
        fillVenueFromDTO(venueDTO, venue);
        venuesDAO.insertVenue(venue);
        return new VenueDTO(venue);
    }

    @Override
    public VenueDTO modifyVenue(VenueDTO venueDTO) {
        Venue venue = venuesDAO.selectVenueById(venueDTO.getVenueId());
        if (venue != null) {
            fillVenueFromDTO(venueDTO, venue);
            venuesDAO.updateVenue(venue);
            return new VenueDTO(venue);
        } else {
            throw new NoVenueFoundException();
        }
    }

    @Override
    public void deleteVenue(VenueDTO venueDTO) {
        Venue venue = venuesDAO.selectVenueById(venueDTO.getVenueId());
        if (venue != null) {
            venuesDAO.deleteVenue(venue);
        } else {
            throw new NoVenueFoundException();
        }
    }

    private void fillVenueFromDTO(VenueDTO venueDTO, Venue venue) {
        venue.setName(venueDTO.getName());
        venue.setCity(venueDTO.getCity());
        venue.setAddress(venueDTO.getAddress());

        List<ZoneDTO> zones = venueDTO.getZones();
        for (ZoneDTO zoneDTO : zones) {
            Zone zone = new Zone();
            zone.setName(zoneDTO.getName());
            zone.setSeats(zoneDTO.getSeats());
            venue.addZone(zone);
        }
    }
}
