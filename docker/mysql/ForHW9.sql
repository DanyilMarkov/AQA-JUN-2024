CREATE TABLE IF NOT EXISTS ForTestAllo (
    ProductID INT UNIQUE AUTO_INCREMENT NOT NULL,
    ProductName VARCHAR(255) NOT NULL,
    ProductPrice VARCHAR(255) NOT NULL,
    PRIMARY KEY (ProductID)
);

INSERT INTO ForTestAllo (ProductID, ProductName, ProductPrice)
VALUES
    (1, 'Айфон 15', '0.00')
ON DUPLICATE KEY UPDATE
    ProductName = VALUES(ProductName),
    ProductPrice = VALUES(ProductPrice);