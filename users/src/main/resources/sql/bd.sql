CREATE TABLE IF NOT EXISTS LOCATION(
                                       ID UUID,
                                       ADRESS VARCHAR (50),
                                       COUNTRY VARCHAR (50),
                                       CITY VARCHAR (50),
                                       ZIPCODE VARCHAR (50),
                                       PRIMARY KEY (ID)
);


CREATE TABLE IF NOT EXISTS USERSTORE(
    AGE INT,
    ID VARCHAR (50),
    OCCUPATION VARCHAR (100),
    USERNAME VARCHAR (50),
    TYPEID VARCHAR (2),
    IDLOCATION UUID,
    PRIMARY KEY (ID),
    FOREIGN KEY (IDLOCATION) REFERENCES LOCATION(ID)
);


INSERT INTO LOCATION (ID, ADRESS, COUNTRY, CITY, ZIPCODE)
VALUES ('112e1e72-e408-1234-a040-b4baf2562fe8', 'Street 123', 'Colombia', 'Bogota','110211');
INSERT INTO USERSTORE (AGE, ID, OCCUPATION, USERNAME, TYPEID, IDLOCATION )
VALUES ('20','123', 'Developer', 'happyman', 'CC','112e1e72-e408-1234-a040-b4baf2562fe8');
