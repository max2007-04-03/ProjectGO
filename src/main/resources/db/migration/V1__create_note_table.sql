CREATE TABLE note (
         id IDENTITY PRIMARY KEY,
         title VARCHAR(200) NOT NULL CHECK (length(title) >= 3),
         content VARCHAR(2000)
);