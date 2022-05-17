package ua.goit.service;


import ua.goit.base_service.SkillBase;
import ua.goit.convert.ConverterSkill;
import ua.goit.dao.DeveloperDao;
import ua.goit.dao.SkillDao;
import ua.goit.dto.SkillDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class SkillsService {

    private final SkillBase skillsBase;

    public SkillsService(SkillBase skillsBase) {
        this.skillsBase = skillsBase;
    }

    public List<SkillDto> getAll() {
        ConverterSkill converterSkill = new ConverterSkill();
        List<SkillDao> all = skillsBase.getAll();
        return all.stream().map(s -> converterSkill.from(s)).collect(Collectors.toList());
    }

    public Optional<SkillDao> get(int id) {
        return skillsBase.get(id);
    }

    public void update(SkillDao skills) {
        skillsBase.update(skills);
    }

    public void create(SkillDao skills, DeveloperDao developer) {

        List<DeveloperDao> developerDao = new ArrayList<>();
        developerDao.add(developer);
        skills.setDevelopers(developerDao);
        skillsBase.create(skills);
    }

    public void delete(SkillDao skills) {
        skillsBase.delete(skills);
    }

    public void updateSkill(SkillDao dao) {

        Optional<SkillDao> skill = skillsBase.get(dao.getId_skill());
        SkillDao skillDao = skill.get();
        skillDao.setLevel_skill(dao.getLevel_skill());
        skillDao.setLanguage(dao.getLanguage());
        skillsBase.update(skillDao);
    }
}
