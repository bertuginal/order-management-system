package com.fmss;

import com.fmss.model.Customer;
import com.fmss.model.Invoice;
import com.fmss.model.Order;
import com.fmss.model.Product;
import com.fmss.repository.repositories.CustomerRepository;
import com.fmss.repository.repositories.InvoiceRepository;
import com.fmss.repository.repositories.OrderRepository;
import com.fmss.repository.repositories.ProductRepository;
import com.fmss.service.CustomerService;
import com.fmss.service.InvoiceService;
import com.fmss.service.OrderService;
import com.fmss.service.ProductService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManagementSystemApplication {

	public static void main(String[] args) {
		ProductService productService = new ProductService(new ProductRepository());
		InvoiceService invoiceService = new InvoiceService(new InvoiceRepository());
		OrderService orderService = new OrderService(new OrderRepository());
		CustomerService customerService = new CustomerService(new CustomerRepository());

		Scanner input = new Scanner(System.in);
		boolean isExit = false;

		while (!isExit) {
			System.out.println("***************************** ORDER MANAGEMENT SYSTEM ******************************");
			System.out.println("Please choose a model!");
			System.out.println("1 -> Customer");
			System.out.println("2 -> Product");
			System.out.println("3 -> Order");
			System.out.println("4 -> Invoice");
			System.out.println("****************");
			System.out.println("0 -> Exit");
			System.out.println("****************");
			int chosen = input.nextInt();

			switch (chosen) {
				case 1 -> customerOperations(customerService, orderService,input);
				case 2 -> productOperations(productService, input);
				case 3 -> orderOperations(orderService, productService, customerService, invoiceService, input);
				case 4 -> invoiceOperations(invoiceService, input);
				default -> isExit = true;
			}

			if (chosen == 0) { isExit = true; }

		}
	}

	private static void customerOperations(CustomerService customerService, OrderService orderService, Scanner input) {
		boolean isBack = false;

		while (!isBack) {
			System.out.println("****************************************************************************");
			System.out.println("Please select an action: ");
			System.out.println("0 -> Return to Main Menu");
			System.out.println("1 -> Add Customer");
			System.out.println("2 -> Delete Customer");
			System.out.println("3 -> Get Customer By ID");
			System.out.println("4 -> List of Customers");
			System.out.println("5 -> List of all customer's full names containing the letter 'C'");
			System.out.println("6 -> Total Amounts of Invoices Created by Customers in June");
			System.out.println("7 -> List of Names of Customers with Invoices Under 500 TL");
			System.out.println("*****************************************************************************");
			int select = input.nextInt();

			switch (select) {
				case 1:
					System.out.println("**************************************** CREATE A CUSTOMER ***************************************");
					System.out.println("Customer name: ");
					String firstName = input.next();

					System.out.println("Customer surname: ");
					String lastName = input.next();

					System.out.println("Customer email: ");
					String email = input.next();
					if (!email.contains("@")) {
						System.out.println("Invalid e-mail address! Please try again.");
						break;
					}
					System.out.println("Customer password: ");
					String password = input.next();


					boolean isContinue = false;
					List<Order> orderList = new ArrayList<>();

					while (!isContinue) {
						System.out.println("************************************ ORDER LIST **************************************");
						orderService.findAll();
						System.out.println("************************************ Add Order to Customer or Save Customer **************************************");
						System.out.println("Please select an action: ");
						System.out.println("1 -> Add Order");
						System.out.println("2 -> Save Customer");

						int chosen = input.nextInt();
						input.nextLine();

						if (chosen == 1) {
							System.out.println("Please add the order ID: ");
							Long id = input.nextLong();
							orderList.add(orderService.findById(id));
						}

						if (chosen == 2) {
							isContinue = true;
						}
					}

					Customer customer = new Customer(firstName,lastName, email, password, LocalDate.now(), orderList);
					customerService.save(customer);
					break;
				case 2:
					System.out.println("************************************ CUSTOMER LIST **************************************");
					customerService.findAll();
					System.out.println("**************************************** DELETE CUSTOMER ********************************");
					System.out.println("Please enter a customer ID: ");
					Long deleteCustomerId = input.nextLong();

					Customer deleteCustomer = customerService.findById(deleteCustomerId);
					customerService.delete(deleteCustomer);
					break;
				case 3:
					System.out.println("***************************************** Get Customer By ID ********************************");
					System.out.println("Please enter a customer ID: ");
					Long findCustomerId = input.nextLong();

					Customer foundCustomer = customerService.findById(findCustomerId);
					System.out.println(foundCustomer);
					break;
				case 4:
					System.out.println("*************************************** CUSTOMER LIST ****************************************");
					customerService.findAll();
					break;
				case 5:
					System.out.println("************************ List of all customer's full names containing the letter 'C' **************************");
					customerService.filterByFullNameContainingLetter("C");
					break;
				case 6:
					System.out.println("********************* Total Amounts of Invoices Created by Customers in June *********************");
					customerService.totalInvoiceAmountsForEnrolledInJune();
					break;
				case 7:
					System.out.println("***************** List of Names of Customers with Invoices Under 500TL *****************");
					customerService.filterNamesWithInvoicesUnderAmount(500.0);
					break;
				default:
					isBack = true;
			}

			if (select == 0) isBack = true;
		}
	}

	private static void productOperations(ProductService productService, Scanner input) {
		boolean isBack = false;

		while (!isBack) {
			System.out.println("************************************");
			System.out.println("Please select action: ");
			System.out.println("0 -> Return to Main Menu");
			System.out.println("1 -> Add Product");
			System.out.println("2 -> Delete Product");
			System.out.println("3 -> Get Product By ID");
			System.out.println("4 -> List of Products");
			System.out.println("*************************************");
			int select = input.nextInt();

			switch (select) {
				case 1:
					System.out.println("********************************** CREATE PRODUCT *********************************");
					System.out.println("Product name: ");
					String name = input.next();

					System.out.println("Product category: ");
					String category = input.next();

					System.out.println("Product price: ");
					Double price = input.nextDouble();

					System.out.println("Product stock: ");
					Integer stock = input.nextInt();

					Product product = new Product(name, category, price, stock);
					productService.save(product);
					break;
				case 2:
					System.out.println("******************************* PRODUCT LIST *******************************");
					productService.findAll();
					System.out.println("******************************* DELETE PRODUCT *******************************");
					System.out.println("Please enter a product ID: ");
					Long deleteProductId = input.nextLong();

					Product deleteProduct = productService.findById(deleteProductId);
					productService.delete(deleteProduct);
					break;
				case 3:
					System.out.println("********************************* Get Product By ID ********************************");
					System.out.println("Please enter a product ID:");
					Long findProductId = input.nextLong();

					Product foundProduct = productService.findById(findProductId);
					System.out.println(foundProduct);
					break;
				case 4:
					System.out.println("******************************* PRODUCT LIST *******************************");
					productService.findAll();
					break;
				default:
					isBack = true;
			}

			if (select == 0) isBack = true;
		}
	}

	private static void orderOperations(OrderService orderService, ProductService productService, CustomerService customerService, InvoiceService invoiceService, Scanner input) {

		boolean isBack = false;

		while (!isBack) {
			System.out.println("************************************");
			System.out.println("Please select action: ");
			System.out.println("0 -> Return to Main Menu");
			System.out.println("1 -> Add Order");
			System.out.println("2 -> Delete Order");
			System.out.println("3 -> Get Order By ID");
			System.out.println("4 -> List of Orders");
			System.out.println("************************************");
			int select = input.nextInt();

			switch (select) {
				case 1:
					System.out.println("******************************** CREATE ORDER *******************************");
					System.out.println("******************************** CUSTOMER LIST ******************************");
					customerService.findAll();
					System.out.println("*****************************************************************************");
					System.out.println("Customer ID: ");
					Long customerId = input.nextLong();
					System.out.println("******************************** INVOICE LIST *******************************");
					invoiceService.findAll();
					System.out.println("*****************************************************************************");
					System.out.println("Invoice ID: ");
					Long invoiceId = input.nextLong();

					boolean isContinue = false;
					List<Product> productList = new ArrayList<>();

					while (!isContinue) {
						System.out.println("******************************* PRODUCT LIST *******************************");
						productService.findAll();
						System.out.println("***************************** Add Product to Order or Save Order ********************");
						System.out.println("Please select action: ");
						System.out.println("1 -> Add Product");
						System.out.println("2 -> Save Order");
						System.out.println("********************************************************************************");

						int chosen = input.nextInt();

						if (chosen == 1) {
							System.out.println("Please add the product ID: ");
							Long id = input.nextLong();
							productList.add(productService.findById(id));
						}

						if (chosen == 2) {
							isContinue = true;
						}
					}
					String generatedOrderCode = orderService.generateOrderCode();
					Order order = new Order(generatedOrderCode, customerId, invoiceId, productList);
					orderService.save(order);
					break;
				case 2:
					System.out.println("******************************** ORDER LIST ********************************");
					orderService.findAll();
					System.out.println("******************************** DELETE ORDER ********************************");
					System.out.println("Please enter an order ID: ");
					Long deleteOrderId = input.nextLong();

					Order deleteOrder = orderService.findById(deleteOrderId);
					orderService.delete(deleteOrder);
					break;
				case 3:
					System.out.println("****************************** Get Order By ID *******************************");
					System.out.println("Please enter an order ID: ");
					Long findOrderId = input.nextLong();

					Order foundOrder = orderService.findById(findOrderId);
					System.out.println(foundOrder);
					break;
				case 4:
					System.out.println("******************************* ORDER LIST *********************************");
					orderService.findAll();
					break;
				default:
					isBack = true;
			}

			if (select == 0) isBack = true;
		}
	}

	private static void invoiceOperations(InvoiceService invoiceService, Scanner input) {
		boolean isBack = false;

		while (!isBack) {
			System.out.println("*********************************************");
			System.out.println("Please select action: ");
			System.out.println("0 -> Return to Main Menu");
			System.out.println("1 -> Add Invoice");
			System.out.println("2 -> Delete Invoice");
			System.out.println("3 -> Get Invoice By ID");
			System.out.println("4 -> List Of Invoices");
			System.out.println("5 -> List of Invoices Above 1500 TL");
			System.out.println("6 -> Average of Over 1500 TL Invoices");
			System.out.println("*********************************************");
			int select = input.nextInt();

			switch (select) {
				case 1:
					System.out.println("********************************* CREATE INVOICE *******************************");
					System.out.println("Total invoice amount: ");
					Double amount = input.nextDouble();

					Invoice invoice = new Invoice(LocalDateTime.now(), amount);
					invoiceService.save(invoice);
					break;
				case 2:
					System.out.println("********************************** DELETE INVOICE *****************************");
					System.out.println("Please enter an invoice ID:");
					Long deleteInvoiceId = input.nextLong();

					Invoice deleteInvoice = invoiceService.findById(deleteInvoiceId);
					invoiceService.delete(deleteInvoice);
					break;
				case 3:
					System.out.println("****************************** Get Invoice By ID ********************************");
					System.out.println("Please enter an invoice ID: ");
					Long findInvoiceId = input.nextLong();

					Invoice foundInvoice = invoiceService.findById(findInvoiceId);
					System.out.println(foundInvoice);
					break;
				case 4:
					System.out.println("****************************** INVOICE LIST ********************************");
					invoiceService.findAll();
					break;
				case 5:
					System.out.println("************************** List of Invoices Above 1500 TL *************************");
					invoiceService.filterHighValueInvoices(1500.0);
					break;
				case 6:
					System.out.println("************************* Average of Over 1500 TL Invoices ************************");
					System.out.println(invoiceService.calculateAverageOfHighValueInvoices());
					break;
				default:
					isBack = true;
			}

			if (select == 0) {
				isBack = true;
			}
		}
	}


}
