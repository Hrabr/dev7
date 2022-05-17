package ua.goit.base_service;


import ua.goit.config.PersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

abstract public class AbstractBase<T> {
    private final Class<T> entityType;
    protected EntityManager em = PersistenceProvider.getEntityManager();

    public AbstractBase(Class<T> entityType) {
        this.entityType = entityType;
    }

    public AbstractBase() {
        Type daoType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) daoType).getActualTypeArguments();
        this.entityType = ((Class<T>) params[0]);
    }


    public void create(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();

    }


    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }


    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }


    public List<T> getAll() {
        em.clear();
        TypedQuery<T> getAll = em.createQuery(
                "from " + entityType.getSimpleName(), entityType);
        return getAll.getResultList();
    }

    public Optional<T> get(int id) {
        T entity = em.find(entityType, id);
        return Optional.of(entity);
    }


}
