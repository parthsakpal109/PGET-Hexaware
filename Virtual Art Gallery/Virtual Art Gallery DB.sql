CREATE DATABASE VirtualArtGallery;
Use VirtualArtGallery;

CREATE TABLE Artist (
    ArtistID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Biography TEXT,
    BirthDate DATE,
    Nationality VARCHAR(100),
    Website VARCHAR(255),
    ContactInfo VARCHAR(255)
);

CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(100) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    FirstName VARCHAR(100),
    LastName VARCHAR(100),
    DateOfBirth DATE,
    ProfilePicture VARCHAR(255)
);

CREATE TABLE Artwork (
    ArtworkID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    Description TEXT,
    CreationDate DATE,
    Medium VARCHAR(100),
    ImageURL VARCHAR(255),
    ArtistID INT,
    FOREIGN KEY (ArtistID) REFERENCES Artist(ArtistID) ON DELETE SET NULL
);

CREATE TABLE User_Favorite_Artwork (
    UserID INT,
    ArtworkID INT,
    PRIMARY KEY (UserID, ArtworkID),
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE,
    FOREIGN KEY (ArtworkID) REFERENCES Artwork(ArtworkID) ON DELETE CASCADE
);

CREATE TABLE Gallery (
    GalleryID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Description TEXT,
    Location VARCHAR(255),
    CuratorID INT,
    OpeningHours VARCHAR(255),
    FOREIGN KEY (CuratorID) REFERENCES Artist(ArtistID) ON DELETE SET NULL
);

CREATE TABLE Artwork_Gallery (
    ArtworkID INT,
    GalleryID INT,
    PRIMARY KEY (ArtworkID, GalleryID),
    FOREIGN KEY (ArtworkID) REFERENCES Artwork(ArtworkID) ON DELETE CASCADE,
    FOREIGN KEY (GalleryID) REFERENCES Gallery(GalleryID) ON DELETE CASCADE
);

INSERT INTO Artist (Name, Biography, BirthDate, Nationality, Website, ContactInfo) VALUES
('Picasso', 'Spanish painter and sculptor.', '1881-10-25', 'Spanish', 'https://www.picasso.com', 'contact@picasso.com'),
('Da Vinci', 'Italian Renaissance artist.', '1452-04-15', 'Italian', 'https://www.davinci.com', 'contact@davinci.com'),
('Van Gogh', 'Dutch post-impressionist painter.', '1853-03-30', 'Dutch', 'https://www.vangogh.com', 'contact@vangogh.com'),
('Monet', 'French impressionist artist.', '1840-11-14', 'French', 'https://www.monet.com', 'contact@monet.com');

INSERT INTO User (Username, Password, Email, FirstName, LastName, DateOfBirth, ProfilePicture) VALUES
('artlover123', 'securepass', 'artlover@example.com', 'Alice', 'Johnson', '1995-07-21', 'alice_profile.jpg'),
('galleryfan89', 'mypassword', 'galleryfan@example.com', 'Bob', 'Smith', '1989-05-10', 'bob_profile.jpg'),
('vangoghfan', 'starrynight', 'vangoghfan@example.com', 'Charlie', 'Brown', '1992-11-30', 'charlie_profile.jpg');

INSERT INTO Artwork (Title, Description, CreationDate, Medium, ImageURL, ArtistID) VALUES
('Guernica', 'War scene painting.', '1937-06-04', 'Oil on Canvas', 'https://example.com/guernica.jpg', 1),
('Mona Lisa', 'Famous portrait painting.', '1503-01-01', 'Oil on Poplar Panel', 'https://example.com/monalisa.jpg', 2),
('Starry Night', 'Night sky with stars.', '1889-06-01', 'Oil on Canvas', 'https://example.com/starrynight.jpg', 3),
('Water Lilies', 'Beautiful nature painting.', '1915-01-01', 'Oil on Canvas', 'https://example.com/waterlilies.jpg', 4);

INSERT INTO Gallery (Name, Description, Location, CuratorID, OpeningHours) VALUES
('Louvre', 'Historic art museum.', 'Paris, France', 2, '9 AM - 6 PM'),
('The Met', 'Famous New York museum.', 'New York, USA', 1, '10 AM - 5 PM'),
('Van Gogh Museum', 'Dedicated to Van Gogh.', 'Amsterdam, Netherlands', 3, '9 AM - 6 PM');

INSERT INTO User_Favorite_Artwork (UserID, ArtworkID) VALUES
(1, 2), -- Alice loves Mona Lisa
(1, 3), -- Alice also loves Starry Night
(2, 1), -- Bob loves Guernica
(3, 3), -- Charlie loves Starry Night
(3, 4); -- Charlie also loves Water Lilies

INSERT INTO Artwork_Gallery (ArtworkID, GalleryID) VALUES
(1, 2), -- Guernica at The Met
(2, 1), -- Mona Lisa at Louvre
(3, 3), -- Starry Night at Van Gogh Museum
(4, 1), -- Water Lilies at Louvre
(4, 2); -- Water Lilies also at The Met