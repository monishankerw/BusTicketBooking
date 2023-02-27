package com.TravelEasy.service.serviceimpl;

import com.TravelEasy.Exception.ResourceNotFoundException;
import com.TravelEasy.entities.Bus;
import com.TravelEasy.entities.Route;
import com.TravelEasy.payload.RouteDTO;
import com.TravelEasy.repositories.BusRepository;
import com.TravelEasy.repositories.RouteRepository;
import com.TravelEasy.service.RouteService;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    private RouteRepository routeRepository;
    private BusRepository busRepository;

    public RouteServiceImpl(RouteRepository routeRepository, BusRepository busRepository) {
        this.routeRepository = routeRepository;
        this.busRepository = busRepository;
    }


    @Override
    public RouteDTO createRoute(long busId, RouteDTO routeDTO) {

        Route route=mapToEntity(routeDTO);
        // retrieve post entity by id
        Bus bus = busRepository.findById(busId).orElseThrow(
                () -> new ResourceNotFoundException("Bus", "id", busId)
        );

route.setBus(bus);

        // comment entity to DB
       Route route1 =  routeRepository.save(route);

        return mapToDTO(route1);
    }

    private RouteDTO mapToDTO(Route comment){

        RouteDTO routeDTO = new RouteDTO();
       routeDTO.setId(routeDTO.getId());
       routeDTO.setOrigin(routeDTO.getOrigin());
       routeDTO.setDestination(routeDTO.getDestination());
        return  routeDTO;
    }

    private Route mapToEntity(RouteDTO routeDTO){

        Route route = new Route();
Route route1=routeRepository.save(route);

        return  route;
    }

}