package ua.goit.service;


import ua.goit.base_service.DeveloperBase;
import ua.goit.convert.ConverterDevelopers;
import ua.goit.dao.DeveloperDao;
import ua.goit.dto.DeveloperDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeveloperService {

    private final DeveloperBase developersBase;

    public DeveloperService(DeveloperBase developersBase) {
        this.developersBase = developersBase;
    }

    public List<DeveloperDto> getAll() {
        ConverterDevelopers converterDevelopers = new ConverterDevelopers();

        List<DeveloperDao> all = developersBase.getAll();
        return all.stream().map(c -> converterDevelopers.from(c)).collect(Collectors.toList());
    }

    public Optional<DeveloperDao> get(int id) {
        return developersBase.get(id);
    }

    public void update(DeveloperDao developers) {
        developersBase.update(developers);
    }

    public void create(DeveloperDao developers) {
        developersBase.create(developers);
    }

    public void delete(DeveloperDao developers) {
        developersBase.delete(developers);
    }

    public void updateDevelopers( DeveloperDao dao) {
        Optional<DeveloperDao> developer = developersBase.get(dao.getId_developers());
        DeveloperDao developerDao = developer.get();
        developerDao.setSalary(dao.getSalary());
        developerDao.setGender(dao.getGender());
        developerDao.setAge(dao.getAge());
        developerDao.setName(dao.getName());
        developersBase.update(developerDao);
    }
}
