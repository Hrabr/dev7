package ua.goit.convert;

import ua.goit.dao.CustomersDao;
import ua.goit.dto.CustomersDto;

import java.util.stream.Collectors;

public class ConverterCustomer {
    public CustomersDao to(CustomersDto dto) {
        return CustomersDao.builder().id_customers(dto.getId_customers())
                .name_customers(dto.getName_customers()).country_customers(dto.getCountry_customers()).build();
    }

    public CustomersDto from(CustomersDao dao) {
        ConverterProjects converterProjects = new ConverterProjects();
        return CustomersDto.builder().id_customers(dao.getId_customers())
                .name_customers(dao.getName_customers()).country_customers(dao.getCountry_customers())
                .projectsDto(dao.getProjectsDao().stream().map(p -> converterProjects.from(p))
                        .collect(Collectors.toList())).build();
    }

    public CustomersDto fromWithoutProjects(CustomersDao dao) {
        return CustomersDto.builder().id_customers(dao.getId_customers()).name_customers(dao.getName_customers())
                .country_customers(dao.getCountry_customers()).build();

    }
}
