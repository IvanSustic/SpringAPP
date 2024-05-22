CREATE TABLE vozila(
    ID INT GENERATED ALWAYS AS IDENTITY,
    maxPassengers INT NOT NULL,
    gearBox VARCHAR(30) NOT NULL,
    airConditioning BOOLEAN NOT NULL,
    doors INT NOT NULL,
    fuel VARCHAR(30) NOT NULL ,
    lastInspection DATE NOT NULL ,
    nextInspection DATE NOT NULL ,
    mileage INT NOT NULL ,
    registration VARCHAR(8) NOT NULL ,
    chassisNumber VARCHAR(17) NOT NULL ,
    PRIMARY KEY(ID)

);