package com.TravelEasy.service;

import com.TravelEasy.payload.BusDTO;
import com.TravelEasy.payload.BusResponce;

import java.util.List;

public interface BusService {
    public BusDTO createBus(BusDTO busDTO);

    public BusResponce getAllBuses(int pageNo, int pageSize,String sortBy,String sortDir );

    public BusDTO getBusById(long id);

    public BusDTO updateBus(BusDTO busDTO, long id);

    public void deleteBusById(long id);

}

