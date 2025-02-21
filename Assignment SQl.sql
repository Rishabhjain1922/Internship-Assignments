CREATE DATABASE e_commerce;
use e_commerce;
CREATE TABLE Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50),
    mobile VARCHAR(15)
);

CREATE TABLE Products (
    id INT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(200),
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(50)
);

show tables

ALTER TABLE Customers 
MODIFY COLUMN name VARCHAR(50) NOT NULL,
MODIFY COLUMN email VARCHAR(50) NOT NULL,
ADD CONSTRAINT unique_email UNIQUE (email),
ADD COLUMN age INT;

ALTER TABLE Products 
CHANGE COLUMN id product_id INT AUTO_INCREMENT PRIMARY KEY,
MODIFY COLUMN description TEXT;




CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    product_id INT,
    quantity INT NOT NULL,
    order_date DATE NOT NULL,
    status ENUM('Pending', 'Success', 'Cancel'),
    payment_method ENUM('Credit', 'Debit', 'UPI'),
    total_amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);



select * from Orders;




-- Since order is the reserve keyword so we cannot create the table name order so i create the table name orders and rename it again to orders

ALTER TABLE Orders RENAME TO Orders;

ALTER TABLE Orders
MODIFY COLUMN status ENUM('Pending', 'Success', 'Cancel') DEFAULT 'Pending',
MODIFY COLUMN payment_method ENUM('Credit', 'Debit', 'UPI', 'COD');



INSERT INTO Customers (name, email, mobile, age) VALUES
('Amit Sharma', 'amit.sharma@gmail.com', '9876543210', 30),
('Priya Verma', 'priya.verma@yahoo.com', '9865321470', 28),
('Rajesh Gupta', 'rajesh.gupta@rediffmail.com', '9823456789', 35),
('Sneha Reddy', 'sneha.reddy@gmail.com', '9812345678', 27),
('Vikram Singh', 'vikram.singh@hotmail.com', '9876543120', 32),
('Neha Jain', 'neha.jain@gmail.com', '9786543210', 26),
('Rohit Mehta', 'rohit.mehta@yahoo.com', '9845123789', 29),
('Anjali Nair', 'anjali.nair@outlook.com', '9856231478', 24),
('Arjun Patel', 'arjun.patel@gmail.com', '9832456789', 31),
('Kavita Yadav', 'kavita.yadav@hotmail.com', '9821456789', 33),
('Deepak Kumar', 'deepak.kumar@gmail.com', '9813456789', 29),
('Sanya Malhotra', 'sanya.malhotra@yahoo.com', '9876547890', 25),
('Manoj Joshi', 'manoj.joshi@rediffmail.com', '9852147896', 37),
('Radhika Sen', 'radhika.sen@gmail.com', '9845876321', 27),
('Varun Saxena', 'varun.saxena@gmail.com', '9812365478', 30),
('Pooja Choudhary', 'pooja.choudhary@gmail.com', '9874563210', 28),
('Gaurav Thakur', 'gaurav.thakur@hotmail.com', '9865324789', 34),
('Meera Dutta', 'meera.dutta@yahoo.com', '9823147896', 26),
('Siddharth Mishra', 'siddharth.mishra@gmail.com', '9812674358', 32),
('Tanya Kapoor', 'tanya.kapoor@outlook.com', '9803124587', 29);

select * from Customers

INSERT INTO Products (name, description, price, category) VALUES
('Samsung Galaxy S23', 'Latest Samsung smartphone with 5G', 79999.99, 'Electronics'),
('iPhone 14', 'Apple flagship smartphone with A16 chip', 89999.99, 'Electronics'),
('Sony Bravia 55 Inch TV', '4K Ultra HD Smart LED TV', 64999.99, 'Electronics'),
('HP Pavilion Laptop', 'Intel i7 12th Gen, 16GB RAM, 512GB SSD', 75999.99, 'Computers'),
('Dell XPS 13', 'Ultra-thin laptop with i7 processor', 114999.99, 'Computers'),
('Nike Running Shoes', 'Lightweight and comfortable sports shoes', 4999.99, 'Footwear'),
('Adidas Sneakers', 'Stylish and durable sneakers for casual wear', 5999.99, 'Footwear'),
('Ray-Ban Aviator Sunglasses', 'Classic sunglasses with UV protection', 7999.99, 'Accessories'),
('Fossil Smartwatch', 'Wearable smartwatch with fitness tracking', 12999.99, 'Accessories'),
('Samsung 256GB SSD', 'Fast NVMe SSD for performance boost', 6499.99, 'Computers'),
('Sony Bluetooth Headphones', 'Noise-canceling wireless headphones', 12999.99, 'Electronics'),
('Apple iPad Air', 'Lightweight tablet with A14 chip', 54999.99, 'Tablets'),
('Boat Airdopes 441', 'Truly wireless earbuds with deep bass', 2499.99, 'Electronics'),
('Puma Sports T-shirt', 'Breathable cotton t-shirt for workouts', 1299.99, 'Apparel'),
('Levi’s Jeans', 'Slim fit denim jeans for men', 3499.99, 'Apparel'),
('Rolex Watch', 'Luxury automatic watch for men', 799999.99, 'Accessories'),
('OnePlus Nord 2', '5G smartphone with MediaTek processor', 29999.99, 'Electronics'),
('Redmi Note 12 Pro', 'Affordable smartphone with AMOLED display', 25999.99, 'Electronics'),
('Lenovo ThinkPad', 'Business laptop with i5 processor', 84999.99, 'Computers'),
('Woodland Leather Shoes', 'Durable and stylish formal shoes', 5999.99, 'Footwear');


select * from Products


INSERT INTO Orders (customer_id, product_id, quantity, order_date, status, payment_method, total_amount) VALUES
(1, 2, 1, '2024-07-15', 'Success', 'Credit', 89999.99),
(2, 5, 1, '2024-07-12', 'Pending', 'UPI', 114999.99),
(3, 7, 2, '2024-07-10', 'Success', 'Debit', 11999.98),
(4, 10, 1, '2024-07-08', 'Cancel', 'Credit', 6499.99),
(5, 12, 1, '2024-07-06', 'Pending', 'COD', 54999.99),
(6, 15, 1, '2024-07-04', 'Success', 'UPI', 3499.99),
(7, 18, 2, '2024-07-02', 'Pending', 'Debit', 51999.98),
(8, 1, 1, '2024-06-30', 'Success', 'Credit', 79999.99),
(9, 4, 1, '2024-06-28', 'Cancel', 'UPI', 75999.99),
(10, 6, 3, '2024-06-26', 'Pending', 'COD', 14999.97),
(11, 8, 1, '2024-06-24', 'Success', 'Debit', 7999.99),
(12, 9, 1, '2024-06-22', 'Pending', 'UPI', 12999.99),
(13, 11, 2, '2024-06-20', 'Success', 'Credit', 25999.98),
(14, 13, 1, '2024-06-18', 'Cancel', 'UPI', 2499.99),
(15, 14, 1, '2024-06-16', 'Pending', 'COD', 1299.99),
(16, 16, 1, '2024-06-14', 'Success', 'Credit', 799999.99),
(17, 17, 1, '2024-06-12', 'Pending', 'Debit', 29999.99),
(18, 19, 1, '2024-06-10', 'Success', 'UPI', 84999.99),
(19, 20, 1, '2024-06-08', 'Cancel', 'Credit', 5999.99),
(20, 3, 1, '2024-06-06', 'Success', 'COD', 64999.99);
select * from Orders;



-- Count the number of products as product_count in each category.

SELECT category, COUNT(*) AS product_count
FROM Products
GROUP BY category;


 -- Retrieve all products that belong to the 'Electronics' category, have a price between ₹5000 and ₹20000, and whose name contains the letter 'a'.
 
 SELECT * 
FROM Products 
WHERE category = 'Electronics' 
AND price BETWEEN 5000 AND 20000
AND name LIKE '%a%';










-- Get the top 5 most expensive products in the 'Electronics' category, skipping the first 2.
SELECT * 
FROM Products 
WHERE category = 'Electronics' 
ORDER BY price DESC 
LIMIT 5 OFFSET 2;







-- Retrieve customers who have not placed any orders.

SELECT * 
FROM Customers 
WHERE customer_id NOT IN (SELECT DISTINCT customer_id FROM Orders);


-- Find the average total amount spent by each customer.

SELECT o.customer_id, c.name, AVG(o.total_amount) AS avg_spent
FROM Orders o
JOIN Customers c ON o.customer_id = c.customer_id
GROUP BY o.customer_id, c.name;

-- Get the products that have a price less than the average price of all products.

SELECT * 
FROM Products 
WHERE price < (SELECT AVG(price) FROM Products);


-- Calculate the total quantity of products ordered by each customer.

SELECT o.customer_id, c.name, SUM(o.quantity) AS total_quantity
FROM Orders o
JOIN Customers c ON o.customer_id = c.customer_id
GROUP BY o.customer_id, c.name;



-- List all orders along with customer name and product name.

SELECT o.order_id, c.name AS customer_name, p.name AS product_name, o.quantity, o.order_date, o.total_amount
FROM Orders o
JOIN Customers c ON o.customer_id = c.customer_id
JOIN Products p ON o.product_id = p.product_id;

-- Find products that have never been ordered.

SELECT * 
FROM Products 
WHERE product_id NOT IN (SELECT DISTINCT product_id FROM Orders);




































