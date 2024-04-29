package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.RozmiaryDto;
import com.example.sklep2xd.Models.RozmiaryEntity;
import com.example.sklep2xd.Repositories.RozmiaryRep;
import com.example.sklep2xd.Service.RozmiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class RozmiaryServiceimpl  implements RozmiaryService {

    private RozmiaryRep rozmiaryRep;

    @Autowired
    public RozmiaryServiceimpl(RozmiaryRep rozmiaryRep){
        this.rozmiaryRep = rozmiaryRep;
    }
    private RozmiaryDto mapToRozmiaryDto(RozmiaryEntity rozmiar){
        RozmiaryDto rozmiaryDto = RozmiaryDto.builder()
                .idRozmiaru(rozmiar.getIdRozmiaru())
                .rozmiar(rozmiar.getRozmiar())
                .produkt(rozmiar.getProdukt())
                .build();
        return rozmiaryDto;
    }
    @Override
    public List<RozmiaryDto> findByAllRozmiary(){
        List<RozmiaryEntity> rozmiary = rozmiaryRep.findAll();
        return rozmiary.stream().map((rozmiar) -> mapToRozmiaryDto(rozmiar)).collect(Collectors.toList());
    }


    @Override
    public RozmiaryDto findByIdRozmiary(int id) {
        RozmiaryEntity rozmiar = rozmiaryRep.findById(id).orElse(null);
        if (rozmiar != null) {
            return mapToRozmiaryDto(rozmiar);
        }
        return null;
    }

    @Override
    public List<RozmiaryDto> findByProduktuId(int id) {
        List<RozmiaryEntity> rozmiary = rozmiaryRep.findByProdukt_IdProduktu(id);
        return rozmiary.stream().map(this::mapToRozmiaryDto).collect(Collectors.toList());
    }


}
