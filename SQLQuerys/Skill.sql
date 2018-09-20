CREATE TABLE Skill (
ID int NOT NULL PRIMARY KEY,
Attribute varchar(20) NOT NULL,
Name nvarchar(25) NOT NULL,
Description nvarchar(255) NOT NULL,
Value int NOT NULL,
Unlocked int NOT NULL
);