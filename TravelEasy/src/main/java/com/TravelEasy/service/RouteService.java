package com.TravelEasy.service;

import com.TravelEasy.payload.RouteDTO;

public interface RouteService {
    public RouteDTO createRoute(long busId,RouteDTO routeDTO);


}
