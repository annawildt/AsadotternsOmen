CREATE TABLE PrincessSkill (
ID int NOT NULL PRIMARY KEY,
SkillID int NOT NULL FOREIGN KEY REFERENCES [dbo].[Skill]([ID]),
PrincessID int NOT NULL FOREIGN KEY REFERENCES [dbo].[Princess]([ID])
);