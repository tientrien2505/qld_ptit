USE [LapTrinhWeb]
GO

/****** Object:  Table [dbo].[subContent]    Script Date: 04/08/2019 08:42:51 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[subContent](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[h1] [nvarchar](max) NOT NULL,
	[h2] [nvarchar](max) NOT NULL,
	[subContent] [nvarchar](max) NOT NULL,
	[time] [date] NOT NULL,
	[link] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_subContent] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


USE [LapTrinhWeb]
GO

/****** Object:  Table [dbo].[news]    Script Date: 04/08/2019 08:42:46 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[news](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[link] [nvarchar](max) NOT NULL,
	[image] [nvarchar](max) NOT NULL,
	[title] [nvarchar](max) NOT NULL,
	[contents] [nvarchar](max) NOT NULL,
	[time] [date] NOT NULL,
 CONSTRAINT [PK_news] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


USE [LapTrinhWeb]
GO

/****** Object:  Table [dbo].[giaovu]    Script Date: 04/08/2019 08:42:38 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[giaovu](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[link] [nvarchar](max) NOT NULL,
	[time] [nvarchar](50) NOT NULL,
	[title] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_giaovu] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO






