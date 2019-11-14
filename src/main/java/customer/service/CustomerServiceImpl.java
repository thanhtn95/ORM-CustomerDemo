package customer.service;

import customer.model.Customer;
import customer.repository.CustomerGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerGeneralRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findByID(id);
    }

    @Override
    public void remove(int id) {
        customerRepository.remove(id);
    }
}
