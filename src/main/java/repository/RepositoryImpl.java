package repository;

import model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;

public class RepositoryImpl<T extends Serializable> implements Repository<T> {
    private EntityManager entityManager;
    protected Class<T> entityClass;

    public RepositoryImpl(Class<T> entityClass){
        var factory = Persistence.createEntityManagerFactory("Messenger");
        entityManager = factory.createEntityManager();

       // var parameterizedType = (ParameterizedType)getClass().getGenericSuperclass();
        this.entityClass = entityClass;//(Class<T>)parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public List<T> list() {
        var query =entityManager.getCriteriaBuilder().createQuery(entityClass); //createNamedQuery("SELECT * FROM "+ entityClass.toString(),entityClass);
        Root<T> root = query.from(entityClass);
        query.select(root);
        List<T> list = entityManager.createQuery(query).getResultList();
        return list;
    }

    @Override
    public T get(int id) {
        return this.entityManager.find(entityClass, id);
    }

    @Override
    public void delete(T item) {
        entityManager.getTransaction().begin();
        entityManager.remove(item);
        entityManager.getTransaction().commit();
    }

    @Override
    public T findBy(String propertyName, Object value){
        entityManager.getTransaction().begin();
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(entityClass);
        var root = query.from(entityClass);

        query.where(criteriaBuilder.equal(root.get(propertyName),value));

        var result = entityManager.createQuery(query).getSingleResult();

        entityManager.getTransaction().commit();
        return result;
    }

    @Override
    public T create(T item) {
        entityManager.getTransaction().begin();

        entityManager.persist(item);
        entityManager.getTransaction().commit();
        return item;
    }

    @Override
    public T update(T item) {
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
        return item;
    }
}
