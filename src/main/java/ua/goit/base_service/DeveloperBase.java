package ua.goit.base_service;



import ua.goit.dao.DeveloperDao;
import ua.goit.dao.ProjectDao;
import ua.goit.dao.SkillDao;

import java.util.ArrayList;

public class DeveloperBase extends AbstractBase<DeveloperDao> {
    public DeveloperBase() {
        super(DeveloperDao.class);
    }

    @Override
    public void delete(DeveloperDao entity) {
        entity = em.merge(entity);
        em.getTransaction().begin();
        for (ProjectDao project : entity.getProjectsDao()) {
            project.getDevelopers().remove(entity);
        }
        for (SkillDao skills : entity.getSkillDao()) {
            skills.getDevelopers().remove(entity);
        }
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public void create(DeveloperDao entity) {
        entity = em.merge(entity);
        em.getTransaction().begin();
        entity.setProjectsDao(new ArrayList<>());
        entity.setSkillDao(new ArrayList<>());
        em.persist(entity);
        em.getTransaction().commit();
    }


}
