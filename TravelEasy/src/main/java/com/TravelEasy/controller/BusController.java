package com.TravelEasy.controller;

import com.TravelEasy.payload.BusDTO;
import com.TravelEasy.payload.BusResponce;
import com.TravelEasy.service.BusService;
import com.TravelEasy.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    private BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    // create blog post rest api
    @PostMapping
    public <BBusDTO> ResponseEntity<BusDTO> createBus(@RequestBody BusDTO busDTO){

        return new ResponseEntity<>(busService.createBus(busDTO),HttpStatus.CREATED);
    }
    // get all posts rest api
    @GetMapping
    public BusResponce getAllBuses(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false)  int pageNo,
                                   @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                   @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                     @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ){
        return busService.getAllBuses(pageNo,pageSize,sortBy,sortDir);
    }

    // get post by id
    @GetMapping("/{id}")
    public ResponseEntity<BusDTO> getBusById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(busService.getBusById(id));
    }
    // update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<BusDTO> updateBus(@RequestBody BusDTO busDTO, @PathVariable(name = "id") long id){

      BusDTO busResponse = busService.updateBus(busDTO, id);

        return new ResponseEntity<>(busResponse, HttpStatus.OK);
    }

    // delete post rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable(name = "id") long id){

        busService.deleteBusById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
}