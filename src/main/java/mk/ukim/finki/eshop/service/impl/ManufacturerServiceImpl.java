package mk.ukim.finki.eshop.service.impl;

import mk.ukim.finki.eshop.model.Manufacturer;
import mk.ukim.finki.eshop.repository.ManufacturerRepository;
import mk.ukim.finki.eshop.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }


    @Override
    public List<Manufacturer> listAllManufacturers() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer findById(Long id) {
        return this.manufacturerRepository.findById(id).orElse(null);
    }
}
