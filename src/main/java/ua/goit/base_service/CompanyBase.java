package ua.goit.base_service;

import ua.goit.dao.CompanyDao;



public class CompanyBase extends AbstractBase<CompanyDao> {
    public CompanyBase() {
        super(CompanyDao.class);
    }

    public void updateNullProject(int id) {
        em.getTransaction().begin();
        em.createQuery("update ProjectDao set company_id=null where id_projects=:id")
                .setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
    }
}
