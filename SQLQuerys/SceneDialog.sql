CREATE TABLE SceneDialog (
ScenID nvarchar(25) NOT NULL FOREIGN KEY REFERENCES [dbo].[Scen](ID),
DialogID nvarchar(25) NOT NULL FOREIGN KEY REFERENCES [dbo].[Dialog]([ID])
);