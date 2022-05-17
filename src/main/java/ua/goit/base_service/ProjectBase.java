package ua.goit.base_service;

import ua.goit.dao.DeveloperDao;
import ua.goit.dao.ProjectDao;

import javax.persistence.Query;
import java.util.List;


public class ProjectBase extends AbstractBase<ProjectDao> {

    public ProjectBase() {
        super(ProjectDao.class);
    }

    @Override
    public void delete(ProjectDao entity) {
        entity = em.merge(entity);
        em.getTransaction().begin();
        for (DeveloperDao developers : entity.getDevelopers()) {
            developers.getProjectsDao().remove(entity);
        }
        em.remove(entity);
        em.getTransaction().commit();
    }

    public List<ProjectDao> getProjectsWithNullableCompany() {
         Query namedQuery = em.createNativeQuery("SELECT * FROM projects WHERE company_id is Null", ProjectDao.class);

        return namedQuery.getResultList();


    }

    public List<ProjectDao> getProjectsWithNullableCustomer() {
        final Query namedQuery = em.createNativeQuery("SELECT * FROM projects WHERE customer_id is Null", ProjectDao.class);

        return namedQuery.getResultList();

    }

}
