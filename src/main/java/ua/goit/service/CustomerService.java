package ua.goit.service;


import ua.goit.base_service.CustomerBase;
import ua.goit.convert.ConverterCustomer;
import ua.goit.dao.CustomerDao;
import ua.goit.dto.CustomerDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerService {
    private final CustomerBase customersBase;

    public CustomerService(CustomerBase customersBase) {
        this.customersBase = customersBase;
    }

    public List<CustomerDto> getAll() {

        ConverterCustomer converterCustomer = new ConverterCustomer();
        List<CustomerDao> all = customersBase.getAll();
        return all.stream()
                .map(c -> converterCustomer.from(c))
                .collect(Collectors.toList());
    }

    public Optional<CustomerDao> get(int id) {
        return customersBase.get(id);
    }

    public void update(CustomerDao customers) {
        customersBase.update(customers);
    }

    public void create(CustomerDao customers) {

        customersBase.create(customers);
    }

    public void delete(CustomerDao customers) {
        customersBase.delete(customers);
    }

    public void updateNullProject(int id) {
        customersBase.updateNullProject(id);
    }

    public void updateCustomer(CustomerDao dao) {

        Optional<CustomerDao> customer = customersBase.get(dao.getId());
        CustomerDao customerDao = customer.get();
        customerDao.setName_customers(dao.getName_customers());
        customerDao.setCountry_customers(dao.getCountry_customers());
        customersBase.update(customerDao);
    }
}
