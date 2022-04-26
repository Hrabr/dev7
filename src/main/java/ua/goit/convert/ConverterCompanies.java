package ua.goit.convert;

import ua.goit.dao.CompaniesDao;
import ua.goit.dto.CompaniesDto;

import java.util.stream.Collectors;

public class ConverterCompanies {
    public CompaniesDao to(CompaniesDto dto) {
        return CompaniesDao.builder().id_companies(dto.getId_companies()).name_companies(dto.getName_companies())
                .country_companies(dto.getCountry_companies()).build();
    }
    public CompaniesDto from(CompaniesDao dao) {
        ConverterProjects converterProjects=new ConverterProjects();
        return CompaniesDto.builder().id_companies(dao.getId_companies()).name_companies(dao.getName_companies())
                .country_companies(dao.getCountry_companies()).projectsDto(dao.getProjectsDao().stream().map(p->converterProjects.from(p))
                        .collect(Collectors.toList())).build();
    }
    public CompaniesDto fromt(CompaniesDao dao){
        return CompaniesDto.builder().id_companies(dao.getId_companies()).name_companies(dao.getName_companies())
                .country_companies(dao.getCountry_companies()).build();
    }
}
