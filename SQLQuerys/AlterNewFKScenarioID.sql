ALTER TABLE Scen
ADD ScenarioID nvarchar(25),
FOREIGN KEY (ScenarioID) REFERENCES Scenario(ID);