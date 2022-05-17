package ua.goit.convert;

import ua.goit.dao.DeveloperDao;
import ua.goit.dto.DeveloperDto;

import java.util.stream.Collectors;

public class ConverterDevelopers {

    public DeveloperDao to(DeveloperDto dto) {
        return DeveloperDao.builder()
                .id_developers(dto.getId_developers())
                .age(dto.getAge())
                .name(dto.getName())
                .gender(dto.getGender())
                .salary(dto.getSalary())
                .build();
    }

    public DeveloperDto from(DeveloperDao dao) {
        ConverterSkill converterSkill = new ConverterSkill();
        ConverterProjects converterProjects = new ConverterProjects();
        return DeveloperDto.builder()
                .id_developers(dao.getId_developers())
                .age(dao.getAge())
                .name(dao.getName())
                .gender(dao.getGender())
                .salary(dao.getSalary())
                .skillDto(dao.getSkillDao().stream().map(p -> converterSkill.from(p)).collect(Collectors.toList())).
                projectsDto(dao.getProjectsDao().stream().map(p -> converterProjects.from(p)).collect(Collectors.toList()))
                .build();

    }
}
