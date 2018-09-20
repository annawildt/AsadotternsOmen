CREATE TABLE Scenario (
ID nvarchar(25) PRIMARY KEY,
ScenID nvarchar(25) NOT NULL FOREIGN KEY REFERENCES [dbo].[Scen]([ID])
);