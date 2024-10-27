package com.apiexamples.service;

import com.apiexamples.entity.Registration;
import com.apiexamples.payload.RegistrationDto;
import com.apiexamples.repository.RegistrationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService{
    private RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        Registration registration=mapToEntity(registrationDto);
        Registration savedEntity=registrationRepository.save(registration);
        RegistrationDto dto=mapToDto(savedEntity);
        dto.setMessage("Registration created successfully");
        return dto;
    }

    @Override
    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public RegistrationDto updateRegistration(RegistrationDto registrationDto, long id) {
        Optional<Registration> byId = registrationRepository.findById(id);
        Registration registration=byId.get();
        registration.setName(registrationDto.getName());
        registration.setPhone(registrationDto.getPhone());
        registration.setEmail(registrationDto.getEmail());
        Registration savedEntity=registrationRepository.save(registration);
        RegistrationDto dto=mapToDto(registration);
        return dto;
    }

    @Override
    public List<RegistrationDto> getRegistration(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable=PageRequest.of(pageNo, pageSize, sort);
        Page<Registration> all = registrationRepository.findAll(pageable);
        List<Registration> registrations = all.getContent();
        List<RegistrationDto> registrationDtos=registrations.stream().map(registration -> mapToDto(registration)).collect(Collectors.toList());
        return registrationDtos;
    }


    Registration mapToEntity(RegistrationDto dto){
        Registration entity=new Registration();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        return entity;
    }
    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto=new RegistrationDto();
        dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setPhone(registration.getPhone());
        dto.setEmail(registration.getEmail());
        return dto;
    }
}
