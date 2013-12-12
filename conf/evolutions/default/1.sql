# --- !Ups
CREATE TABLE passport.hackers (
    hacker_UUID                 CHAR(36)        NOT NULL UNIQUE,
    hacker_photoPath            VARCHAR(255)    NOT NULL,
    hacker_name                 VARCHAR(255)    NOT NULL,
    hacker_dateOfBirth          DATE            NOT NULL,
    hacker_sex                  VARCHAR(6)      NOT NULL,
    hacker_hackerSpace          CHAR(36)        NOT NULL,
    hacker_dateJoined           DATE            NOT NULL,
    hacker_membershipStatus     VARCHAR(255)    NOT NULL,

    PRIMARY KEY (hacker_UUID),
    INDEX (hacker_name)
);

CREATE TABLE passport.hackerSpace (
    hackerSpace_UUID                CHAR(36)        NOT NULL UNIQUE,
    hackerSpace_logoPath            VARCHAR(255)    NOT NULL,
    hackerSpace_name                VARCHAR(255)    NOT NULL,
    hackerSpace_location            VARCHAR(255)    NOT NULL,
    hackerSpace_GPS                 VARCHAR(255)    NOT NULL,
    hackerSpace_dateFounded         DATE            NOT NULL,
    hackerSpace_publicKey           VARHCAR(255)    NOT NULL,
    
    PRIMARY KEY (hackerSpace_UUID),
    INDEX (hackerSpace_name)
);

CREATE TABLE passport.eventProject (
    eventProject_UUID                 CHAR(36)        NOT NULL UNIQUE,
    eventProject_logoPath             VARCHAR(255)    NOT NULL,
    eventProject_name                 VARCHAR(255)    NOT NULL,
    eventProject_URL                  VARCHAR(255)    NOT NULL,
    eventProject_date                 DATE            NOT NULL,
    hackerSpace_UUID                  CHAR(36)        NOT NULL,

    PRIMARY KEY (eventProject_UUID),
    INDEX (eventProject_name),
    FOREIGN KEY (hackerSpace_UUID) REFERENCES passport.hackerSpace(hackerSpace_UUID)
);

CREATE TABLE passport.hackersEventProject (
    hackerSpace_UUID                  CHAR(36)        NOT NULL UNIQUE,
    eventProject_UUID                 CHAR(36)        NOT NULL UNIQUE,
    hackersEventProject_signature     VARCHAR(255)    NOT NULL,

    PRIMARY KEY (hackerSpace_UUID, eventProject_UUID),
    FOREIGN KEY (hackerSpace_UUID) REFERENCES passport.hackerSpace(hackerSpace_UUID),
    FOREIGN KEY (eventProject_UUID) REFERENCES passport.eventProject(eventProject_UUID)
);

# --- !Downs

DROP TABLE passport.hackers, passport.hackerSpace, passport.eventProject, passport.hackersEventProject;