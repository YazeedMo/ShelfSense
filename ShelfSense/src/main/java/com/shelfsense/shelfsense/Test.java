package com.shelfsense.shelfsense;

import com.shelfsense.shelfsense.dao.CustomerDAOImp;
import com.shelfsense.shelfsense.dao.EmployeeDAOImp;
import com.shelfsense.shelfsense.dao.UserDAOImp;
import com.shelfsense.shelfsense.model.Customer;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.model.User;
import com.shelfsense.shelfsense.services.LoginService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        CustomerDAOImp customerDAOImp = new CustomerDAOImp();

        try {
            Customer customer = customerDAOImp.getWithId(7);
            customer.setFirstName("Mikkkk");
            customerDAOImp.update(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            List<Customer> customerList = customerDAOImp.getAll();
            for (Customer customer : customerList) {
                System.out.println(customer.getFirstName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
