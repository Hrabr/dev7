package ua.goit.convert;

import ua.goit.dao.SkillDao;
import ua.goit.dto.SkillDto;

public class ConverterSkill {

    public SkillDao to(SkillDto dto) {
        return SkillDao.builder()
                .id_skill(dto.getId_skill())
                .level_skill(dto.getLevel_skill())
                .language(dto.getLanguage())
                .build();
    }

    public SkillDto from(SkillDao dao) {
        return SkillDto.builder()
                .id_skill(dao.getId_skill())
                .level_skill(dao.getLevel_skill())
                .language(dao.getLanguage())
                .build();
    }

}
