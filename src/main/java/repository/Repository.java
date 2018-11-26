package repository;

import javax.persistence.criteria.Expression;
import java.util.List;
import javax.persistence.criteria.Predicate;

public interface Repository<T> {
    public List<T> list();

    public T findBy(String propertyName, Object value);

    public T get(int id);

    public void delete(T item);

    public T create(T item);

    public T update(T item);
}
