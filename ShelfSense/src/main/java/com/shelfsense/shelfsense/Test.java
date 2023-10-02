package com.shelfsense.shelfsense;

import com.shelfsense.shelfsense.dao.implementations.ManagerDAOImp;
import com.shelfsense.shelfsense.dao.interfaces.ManagerDAO;
import com.shelfsense.shelfsense.model.Manager;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws SQLException {

        ManagerDAO managerDAO = new ManagerDAOImp();

        Manager manager = managerDAO.getWithId(3);

        if (manager == null) {
            System.out.println("Manager is null");
        }
        else {
            System.out.println("Manager exists");
        }

    }

}
