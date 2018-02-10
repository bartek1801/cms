package pl.com.bottega.cms.domain.repositories;

public interface Repository<T> {

    void save(T t);

    T get(Long id);
}
