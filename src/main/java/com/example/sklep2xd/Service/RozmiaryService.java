package com.example.sklep2xd.Service;

import com.example.sklep2xd.Dto.RozmiaryDto;

import java.util.List;

public interface RozmiaryService {

    RozmiaryDto findByIdRozmiary(int id);

    List<RozmiaryDto> findByProduktuId(int id);

    List<RozmiaryDto> findByAllRozmiary();
}
