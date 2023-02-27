package com.TravelEasy.service.serviceimpl;

import com.TravelEasy.Exception.ResourceNotFoundException;
import com.TravelEasy.entities.Bus;
import com.TravelEasy.payload.BusDTO;
import com.TravelEasy.payload.BusResponce;
import com.TravelEasy.repositories.BusRepository;
import com.TravelEasy.service.BusService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService {

    private BusRepository busRepository;
    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository=busRepository;
    }
    @Override
    public BusDTO createBus(BusDTO busDTO) {
        // convert DTO to entity

        Bus bus = mapToEntity(busDTO);
       Bus newBus = busRepository.save(bus);
        // convert entity to DTO
        BusDTO  busResponse = mapToDTO(newBus);
        return busResponse;
    }

    @Override
    public BusResponce getAllBuses(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);//overloaded method
       Page<Bus> buses = busRepository.findAll(pageable);
////1.Pageable:In Java, the Pageable interface is used to represent query results that are split into pages,
//where each page contains a subset of the total results.
//2.The interface defines methods to specify the page number, page size, and sort order,
        List<Bus> content = buses.getContent();//convert into list
        List<BusDTO>contents=content.stream().map(bus -> mapToDTO(bus)).collect(Collectors.toList());
        BusResponce busResponce=new BusResponce();
        busResponce.setContent(contents);
        busResponce.setPageNo(buses.getNumber());
        busResponce.setPageSize(buses.getSize());
        busResponce.setTotalPages(buses.getTotalPages());
        busResponce.setTotalElements(buses.getTotalElements());
        busResponce.setLast(buses.isLast());
        return  busResponce;
    }


    @Override
    public BusDTO getBusById(long id) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bus", "id", id));
        return mapToDTO(bus);
    }

    @Override
    public BusDTO updateBus(BusDTO busDTO, long id) {
        // get post by id from the database
        Bus bus = busRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        bus.setId(busDTO.getId());
       bus.setName(busDTO.getName());
       bus.setCapacity(busDTO.getCapacity());
       Bus busupdate=busRepository.save(bus);
        return mapToDTO(busupdate);
    }

    @Override
    public void deleteBusById(long id) {
// get post by id from the database
        Bus bus = busRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bus", "id", id));
        busRepository.delete(bus);
    }




    private Bus mapToEntity(BusDTO busDTO) {
        Bus bus=new Bus();
        bus.setName(busDTO.getName());
        bus.setCapacity(busDTO.getCapacity());
        return bus;
    }

    private BusDTO mapToDTO(Bus newBus) {
        BusDTO busDTO=new BusDTO();
        busDTO.setName(newBus.getName());
        busDTO.setId(newBus.getId());
        busDTO.setCapacity(newBus.getCapacity());
        return busDTO;
    }

}

