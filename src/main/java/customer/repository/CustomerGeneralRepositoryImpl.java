package customer.repository;

import customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class CustomerGeneralRepositoryImpl implements CustomerGeneralRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public void save(Customer model) {
        if(model.getId() != 0){
            em.merge(model);
        } else {
            em.persist(model);
        }
    }

    @Override
    public Customer findByID(int id) throws NoResultException {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c where id=:id", Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(int id) {
        Customer customer = findByID(id);
        if (customer != null){
            em.remove(customer);
        }
    }
}
