package edu.iu.c322.customerservice.controller;
import edu.iu.c322.customerservice.model.Customer;
import edu.iu.c322.customerservice.repository.CustomerRepository;
import edu.iu.c322.customerservice.repository.InMemoryCustomerRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerRepository repository;
    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }
    // Get http://localhost:8080/customers
    @GetMapping
    public List<Customer> findAll(){
        return repository.findAll();
    }
    @PostMapping
    public int create(@Valid @RequestBody Customer customer){
        return repository.save(customer).getId();
    }

    // PUT http://localhost:8080/customers/2
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Customer customer, @PathVariable int id){
        customer.setId(id);
        repository.save(customer);
    }

    // DELETE http://localhost:8080/customers/2
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Customer c = new Customer();
        c.setId(id);
        repository.delete(c);
    }
}
