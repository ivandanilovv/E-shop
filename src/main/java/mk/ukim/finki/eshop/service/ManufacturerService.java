package mk.ukim.finki.eshop.service;

import mk.ukim.finki.eshop.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> listAllManufacturers();

    Manufacturer findById(Long id);

}
