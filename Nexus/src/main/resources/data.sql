-- Insert statements for Contract entities
INSERT INTO contract (contract_id, client_name) VALUES
(1, 'Mikes'),
(2, 'Jane'),
(3, 'Steak');

-- Insert statements for Business entities
INSERT INTO business (business_id, business_name, description, email, phone_number, contract_id) VALUES
(1, 'Mikes Lemonade', 'tasty beverages','business1@example.com', 1234567891, 1),
(2, 'Expresso Delight', 'coffee','business2@example.com', 1234547891, 2),
(3, 'Steak House Subs', 'Grilled food', 'business3@example.com',1234567893, 3);

-- Insert statements for Customer entities
INSERT INTO customer (customer_id, first_name, last_name, description, email, phone_number, contract_id) VALUES
(1, 'John', 'Doe', 'Customer 1', 'customer1@example.com', 1234567897, 1),
(2, 'Jane', 'Smith', 'Customer 2', 'customer2@example.com', 1876543210, 2),
(3, 'Mike', 'Johnson', 'Customer 3', 'customer3@example.com', 1678901234, 3);
