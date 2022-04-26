package ua.goit.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class SkillDao {
    private int id_skill;
    private String language;
    private String level_skill;


}
