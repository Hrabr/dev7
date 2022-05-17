package ua.goit.convert;

import ua.goit.dao.CompanyDao;
import ua.goit.dto.CompanyDto;

import java.util.stream.Collectors;

public class ConverterCompanies {
    public CompanyDao to(CompanyDto dto) {
        ConverterProjects converterProjects = new ConverterProjects();
        return CompanyDao.builder().
                id(dto.getId_companies())
                .name_companies(dto.getName_companies())
                .country_companies(dto.getCountry_companies())
                .projectsDao(dto.getProjectsDto().stream()
                        .map(pi->converterProjects.to(pi))
                        .collect(Collectors.toList())).build();
    }

    public CompanyDto from(CompanyDao dao) {
        ConverterProjects converterProjects = new ConverterProjects();
        return CompanyDto.builder()
                .id_companies(dao.getId())
                .name_companies(dao.getName_companies())
                .country_companies(dao.getCountry_companies())
                .projectsDto(dao.getProjectsDao().stream()
                        .map(p->converterProjects.from(p))
                        .collect(Collectors.toList())).build();
    }


}
