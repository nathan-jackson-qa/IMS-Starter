package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class CustomerController implements CrudController<Customer> {

	public static final Logger LOGGER = LogManager.getLogger();

	private CustomerDAO customerDAO;
	private Utils utils;

	public CustomerController(CustomerDAO customerDAO, Utils utils) {
		super();
		this.customerDAO = customerDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Customer> readAll() {
		List<Customer> customers = customerDAO.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer.toString());
		}
		return customers;
	}

	@Override
	public Customer read() {
		LOGGER.info("Please enter the ID of the customer you wish to view");
		long id = utils.getLong();
		Customer customer = customerDAO.read(id);
		if(customer != null) 
		{
			LOGGER.info(customer.toString() + "\n");
			return customer;
		}
		else
		{
			LOGGER.info("This ID does not match that of a customer in the database so could not be retrieved\n");
			return null;
		}
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Customer create() {
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a last name");
		String surname = utils.getString();
		Customer customer = customerDAO.create(new Customer(firstName, surname));
		LOGGER.info("Customer created");
		return customer;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Customer update() {
		LOGGER.info("Please enter the id of the customer you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a last name");
		String surname = utils.getString();
		Customer customer = customerDAO.update(new Customer(id, firstName, surname));
		if(customer!=null) 
		{
			LOGGER.info("Customer Updated!\n");
			return customer;
		}
		else
		{
			LOGGER.info("Update could not be carried out due to the ID not matching a customer in the database\n");
			return null;
		}
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = utils.getLong();
		int returned = customerDAO.delete(id);
		if(returned == 0)
		{
			LOGGER.info("Customer could not be deleted as that customer ID does not exist\n");
		}
		else
		{
			LOGGER.info("Customer deleted!\n");
		}
		return returned;
	}


}
