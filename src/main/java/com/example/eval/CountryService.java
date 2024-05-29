package com.example.eval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Optional<Country> findById(Integer id) {
        return countryRepository.findById(id);
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
