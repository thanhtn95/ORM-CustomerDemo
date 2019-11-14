package customer.repository;

import java.util.List;

public interface GeneralRepository<E> {
    List<E> findAll();

    void save(E model);

    E findByID(int id);

    void remove(int id);
}
