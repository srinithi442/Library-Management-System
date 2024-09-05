create database library;
show databases;
use library;

CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    quantity INT NOT NULL
);

CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    member_id INT NOT NULL,
    date_borrowed DATE NOT NULL,
    date_returned DATE,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (member_id) REFERENCES members(id)
);

INSERT INTO books (title, author, isbn, quantity) VALUES
('To Kill a Mockingbird', 'Harper Lee', '9780061120084', 5),
('1984', 'George Orwell', '9780451524935', 8),
('The Great Gatsby', 'F. Scott Fitzgerald', '9780743273565', 6),
('Pride and Prejudice', 'Jane Austen', '9780141439518', 4),
('The Catcher in the Rye', 'J.D. Salinger', '9780316769488', 7),
('Animal Farm', 'George Orwell', '9780451526342', 3),
('Lord of the Flies', 'William Golding', '9780399501487', 9),
('Brave New World', 'Aldous Huxley', '9780060850524', 5),
('The Hobbit', 'J.R.R. Tolkien', '9780547928227', 10),
('The Little Prince', 'Antoine de Saint-Exupéry', '9780156012195', 12),
('The Alchemist', 'Paulo Coelho', '9780061122415', 6),
('Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', '9780590353427', 15),
('A Tale of Two Cities', 'Charles Dickens', '9781853260391', 8),
('The Chronicles of Narnia', 'C.S. Lewis', '9780064404990', 7),
('Moby', 'Herman Melville', '9781853260087', 5),
('The Da Vinci Code', 'Dan Brown', '9780307474278', 10),
('The Picture of Dorian Gray', 'Oscar Wilde', '9780486278070', 6),
('Frankenstein', 'Mary Shelley', '9780141439471', 4),
('The Lord of the Rings', 'J.R.R. Tolkien', '9780544003415', 9),
('Alice''s Adventures in Wonderland', 'Lewis Carroll', '9781509899425', 7);

INSERT INTO members (name, email) VALUES
('John Smith', 'john@gmail.com'),
('Alice Johnson', 'alice@gmail.com'),
('Michael Brown', 'michael@gmail.com'),
('Emma Davis', 'emma@gmail.com'),
('Christopher Wilson', 'christopher@gmail.com'),
('Jessica Martinez', 'jessica@gmail.com'),
('Matthew Taylor', 'matthew@gmail.com'),
('Emily Anderson', 'emily@gmail.com'),
('Daniel Thomas', 'daniel@gmail.com'),
('Olivia White', 'olivia@gmail.com'),
('Andrew Harris', 'andrew@gmail.com'),
('Sophia Martin', 'sophia@gmail.com'),
('William Garcia', 'william@gmail.com'),
('Isabella Rodriguez', 'isabella@gmail.com'),
('James Lee', 'james@gmail.com'),
('Amelia Lopez', 'amelia@gmail.com'),
('Benjamin Moore', 'benjamin@gmail.com'),
('Ava Clark', 'ava@gmail.com'),
('Alexander Hall', 'alexander@gmail.com'),
('Ryan Adams', 'ryan@gmail.com');

INSERT INTO transactions (book_id, member_id, date_borrowed, date_returned) VALUES
(1, 1, '2024-04-01', NULL),
(2, 2, '2024-04-02', '2024-04-05'),
(3, 3, '2024-04-03', '2024-04-07'),
(4, 4, '2024-04-04', NULL),
(5, 5, '2024-04-05', NULL),
(6, 6, '2024-04-06', '2024-04-09'),
(7, 7, '2024-04-07', '2024-04-11'),
(8, 8, '2024-04-08', NULL),
(9, 9, '2024-04-09', NULL),
(10, 10, '2024-04-10', '2024-04-13'),
(11, 11, '2024-04-11', '2024-04-15'),
(12, 12, '2024-04-12', NULL),
(13, 13, '2024-04-13', NULL),
(14, 14, '2024-04-14', '2024-04-17'),
(15, 15, '2024-04-15', '2024-04-19'),
(16, 16, '2024-04-16', NULL),
(17, 17, '2024-04-17', NULL),
(18, 18, '2024-04-18', '2024-04-21'),
(19, 19, '2024-04-19', NULL),
(20, 20, '2024-04-20', NULL);

INSERT INTO books (title, author, isbn, quantity) VALUES ('The Castle', 'Robert Frost', '9781234567890', 10);

INSERT INTO members (name, email) VALUES ('angeline', 'angeline@gmail.com');

INSERT INTO transactions (book_id, member_id, date_borrowed) VALUES (1, 1, '2024-04-25');

SELECT * FROM books;

SELECT * FROM members;

SELECT * FROM transactions;

UPDATE books SET quantity = 12 WHERE id = 1;

UPDATE members SET email = 'jo@gmail.com' WHERE id = 1;

UPDATE transactions SET date_returned = '2024-04-30' WHERE id = 1;

DELETE FROM books WHERE id = 46;

SELECT DISTINCT title, author, isbn, quantity
FROM books;






















