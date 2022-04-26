package ua.goit.convert;

import ua.goit.dao.DevelopersDao;
import ua.goit.dto.DevelopersDto;

import java.util.stream.Collectors;

public class ConverterDevelopers {

    public DevelopersDao to(DevelopersDto dto) {
        return DevelopersDao.builder().id_developers(dto.getId_developers()).age(dto.getAge())
                .name(dto.getName()).gender(dto.getGender()).salary(dto.getSalary()).build();
    }

    public DevelopersDto from(DevelopersDao dao) {
        ConverterSkill converterSkill= new ConverterSkill();
        ConverterProjects converterProjects= new ConverterProjects();
        return DevelopersDto.builder().id_developers(dao.getId_developers()).age(dao.getAge())
                .name(dao.getName()).gender(dao.getGender()).salary(dao.getSalary()).skillDto(dao.getSkillDao().stream().map(
                      p->converterSkill.from(p)).collect(Collectors.toList())).
                projectsDto(dao.getProjectsDao().stream().map(p->converterProjects.from(p)).collect(Collectors.toList())).build();

    }
public DevelopersDto fromt(DevelopersDao dao){

    return DevelopersDto.builder().id_developers(dao.getId_developers()).age(dao.getAge())
            .name(dao.getName()).gender(dao.getGender()).salary(dao.getSalary()).build();
}
}
