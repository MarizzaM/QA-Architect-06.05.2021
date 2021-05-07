package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@RestController
public class CustomerController {

    private static List<Customer> m_customers = new ArrayList<>();

    static {
        m_customers.add( new Customer(1, "Person 1", "Address 1"));
        m_customers.add( new Customer(2, "Person 2", "Address 2"));
        m_customers.add( new Customer(3, "Person 3", "Address 3"));
    }

    @GetMapping("/customer")
    public List<Customer> getCustomers(){
        return m_customers;
    }


    @GetMapping("/customer/{id}")
    public Customer doGetCustomerById(@PathVariable("id") int id)
    {
        for(Customer c : m_customers)
        {
            if (c.getId() == id)
            {
                return c;
            }
        }
        return null;
    }

    @PostMapping("/customer")
    public String addCustomer(@RequestBody Customer c)
    {
        m_customers.add(c);
        return "Customer add";
    }

    @PutMapping("/customer/{id}")
    public String doUpdateCustomerByID(@PathVariable("id") int id,
                                     @RequestBody Customer sent)
    {
        for (Customer c : m_customers) {
            if (c.getId() == id){
                c.setId(sent.getId());
                c.setName(sent.getName());
                c.setAddress(sent.getAddress());
                return  "Customer updated";
            }
        }
        return "Customer not found";
    }

    @DeleteMapping ("/customer/{id}")
    public String deleteCustomerById(@PathVariable("id") int id)
    {
        Iterator<Customer> it = m_customers.iterator();
        while(it.hasNext()) {
            Customer i = it.next();
            if(i.getId() == id) {
                it.remove();
                return "Customer deleted";
            }
        }
        return "Customer not found";
    }
}
