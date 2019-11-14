package customer.controller;

import customer.model.Customer;
import customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView("/customer/index");
        return modelAndView;
    }

    @GetMapping("/listCustomer")
    public ModelAndView getCustomerList() {
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/createCustomer")
    public ModelAndView getCustomerCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create", "customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/doCreateCustomer")
    public ModelAndView doCreateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/listCustomer");
        return modelAndView;
    }

    @GetMapping("/editCustomer/{id}")
    public ModelAndView showEditForm(@PathVariable int id) {
        Customer customer = customerService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer",customer);
            return modelAndView;
    }

    @PostMapping("/doEditCustomer")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/listCustomer");
        return modelAndView;
    }

    @GetMapping("/deleteCustomer/{id}")
    public ModelAndView deleteCustomer(@PathVariable int id){
        customerService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/listCustomer");
        return modelAndView;
    }

}
