package ua.goit.base_service;

import ua.goit.dao.CustomerDao;

public class CustomerBase extends AbstractBase<CustomerDao> {
    public CustomerBase() {
        super(CustomerDao.class);
    }

    public void updateNullProject(int id) {
        em.getTransaction().begin();
        em.createQuery("update ProjectDao set customer_id=null where id_projects=:id")
                .setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
    }
}
