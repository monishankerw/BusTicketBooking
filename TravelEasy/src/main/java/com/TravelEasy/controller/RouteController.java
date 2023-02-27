package com.TravelEasy.controller;

import com.TravelEasy.payload.RouteDTO;
import com.TravelEasy.service.RouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class RouteController {

    private RouteService routeService;
    public RouteController(RouteService routeService){
        this.routeService=routeService;
    }
    @PostMapping("/routes/{busId}/routes")
    public ResponseEntity<RouteDTO> createComment(@PathVariable(value = "busId") long busId,
                                                  @RequestBody RouteDTO routeDTO){
        return new ResponseEntity<>(routeService.createRoute(busId, routeDTO), HttpStatus.CREATED);
    }
}
