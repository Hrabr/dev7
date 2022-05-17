package ua.goit.convert;

import ua.goit.dao.CustomerDao;
import ua.goit.dto.CustomerDto;

import java.util.stream.Collectors;

public class ConverterCustomer {
    public CustomerDao to(CustomerDto dto) {
        return CustomerDao.builder()
                .id(dto.getId_customers())
                .name_customers(dto.getName_customers())
                .country_customers(dto.getCountry_customers())
                .build();
    }

    public CustomerDto from(CustomerDao dao) {
        ConverterProjects converterProjects = new ConverterProjects();
        return CustomerDto.builder()
                .id_customers(dao.getId())
                .name_customers(dao.getName_customers())
                .country_customers(dao.getCountry_customers())
                .projectsDto(dao.getProjectsDao().stream()
                        .map(p -> converterProjects.from(p))
                        .collect(Collectors.toList())).build();
    }


}
