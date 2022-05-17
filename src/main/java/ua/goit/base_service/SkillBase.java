package ua.goit.base_service;

import ua.goit.dao.DeveloperDao;
import ua.goit.dao.SkillDao;

public class SkillBase extends AbstractBase<SkillDao> {

    public SkillBase() {
        super(SkillDao.class);
    }

    @Override
    public void delete(SkillDao entity) {
        entity = em.merge(entity);
        em.getTransaction().begin();
        for (DeveloperDao developer : entity.getDevelopers()) {
            if (!developer.getSkillDao().isEmpty()) {
                developer.getSkillDao().remove(entity);
            }
        }
        em.remove(entity);
        em.getTransaction().commit();
    }
}
