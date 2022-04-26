package ua.goit.service;

import lombok.SneakyThrows;
import ua.goit.base_command.SkillCommand;
import ua.goit.convert.ConverterSkill;
import ua.goit.dao.SkillDao;
import ua.goit.dto.SkillDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SkillsService {
    private ConverterSkill converterSkill;
    private SkillCommand skillCommand;
    private DbHelper dbHelper;
private final String SAVE_SKILL="INSERT INTO skill (language,level_skill) VALUES (?,?);";
private final String SAVE_DEVELOPERS_SKILL="INSERT INTO developers_skill (developer_id,skill_id) VALUES(?,?);";
private  final String GET_SKILL="SELECT * FROM skill WHERE id_skill=?";
private final String UPDATE_SKILL="UPDATE skill SET language=?,level_skill=? WHERE id_skill=?;";
private final String DELETE_SKILL="DELETE FROM skill WHERE id_skill=?;";
private final String DELETE_DEVELOPER_SKILL ="DELETE FROM developers_skill WHERE developer_id=? AND skill_id=?;";
    public SkillsService() {
        this.skillCommand = new SkillCommand();
        this.converterSkill = new ConverterSkill();
        this.dbHelper = new DbHelper();
    }

    public List<SkillDto> getAll() {
        List<SkillDao> all = skillCommand.getAll();
        return all.stream().map(dao -> converterSkill.from(dao)).collect(Collectors.toList());
    }

    public Integer save(SkillDto dto) {
        SkillDao dao = converterSkill.to(dto);
        return dbHelper.getWithPreparedStatementWithId(SAVE_SKILL,ps->{
            try {
                ps.setString(1, dao.getLanguage());
                ps.setString(2, dao.getLevel_skill());
            }catch (SQLException e){
                e.printStackTrace();}
        });
    }
    public int saveDevelopersSkill(Integer idDev,Integer idSkill){
        return dbHelper.executeWithPreparedStatement(SAVE_DEVELOPERS_SKILL,ps->{
            try {
                ps.setInt(1, idDev);
                ps.setInt(2, idSkill);
            }catch (SQLException e){
                e.printStackTrace();}
        });
    }
    public Integer update(SkillDto dto){
        SkillDao dao = converterSkill.to(dto);
        return dbHelper.getWithPreparedStatementWithId(UPDATE_SKILL, ps -> {

            try {
                ps.setString(1, dao.getLanguage());
                ps.setString(2, dao.getLevel_skill());
                ps.setInt(3, dao.getId_skill());

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }
    @SneakyThrows
    public SkillDto get(int i){
        ResultSet withPreparedStatement = dbHelper.getWithPreparedStatement(GET_SKILL, ps -> {

            try {
                ps.setInt(1, i);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        while (withPreparedStatement.next()) {
            SkillDao skillDao = skillCommand.mapToEntity(withPreparedStatement);
            return converterSkill.from(skillDao);
        }
        return null;
    }
public int deleteSkill(String skillId){
    int number=Integer.parseInt(skillId);

    return dbHelper.executeWithPreparedStatement(DELETE_SKILL, ps -> {

        try {
            ps.setInt(1, number);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    });
}
public int deleteDeveloperSkill(String developer,String skill){
     int developerId = Integer.parseInt(developer);
     int skillId = Integer.parseInt(skill);

    return dbHelper.executeWithPreparedStatement(DELETE_DEVELOPER_SKILL, ps -> {

        try {
            ps.setInt(1, developerId);
            ps.setInt(2, skillId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    });
}
}
