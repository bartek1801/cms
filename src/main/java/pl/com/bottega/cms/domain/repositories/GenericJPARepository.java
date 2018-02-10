package pl.com.bottega.cms.domain.repositories;

import pl.com.bottega.cms.infrastructure.NoSuchEntityException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract  class GenericJPARepository<T> implements Repository<T> {


    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> clazz;

    protected GenericJPARepository() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    @Override
    public void save(T t) {
        entityManager.persist(t);
    }

    @Override
    public T get(Long id) {
        T t = entityManager.find(clazz, id);
        if (t == null)
            throw new NoSuchEntityException();
        return t;
    }
}
