# --- !Ups

CREATE TABLE hackers (
    hacker_GUID                 CHAR(16)        NOT NULL UNIQUE,
    hacker_photo_path           VARCHAR(255)    NOT NULL,
    hacker_name                 VARCHAR(255)    NOT NULL,
    hacker_date_of_birth        DATE            NOT NULL,
    hacker_sex                  VARCHAR(6)      NOT NULL,
    hacker_hacker_space         CHAR(16)        NOT NULL,
    hacker_date_joined          DATE            NOT NULL,
    hacker_membership_status    VARCHAR(255)    NOT NULL,

    PRIMARY KEY (hacker_GUID),
    INDEX (hacker_name)
);

CREATE TABLE hackerSpace (
    hackerSpace_GUID                 CHAR(16)        NOT NULL UNIQUE,
    hackerSpace_logo_path            VARCHAR(255)    NOT NULL,
    hackerSpace_name                 VARCHAR(255)    NOT NULL,
    hackerSpace_location             VARCHAR(255)    NOT NULL,
    hackerSpace_GPS                  VARCHAR(255)    NOT NULL,
    hackerSpace_date_founded         DATE            NOT NULL,

    PRIMARY KEY (hackerSpace_GUID),
    INDEX (hackerSpace_name)
);

CREATE TABLE eventProject (
    eventProject_GUID                 CHAR(16)        NOT NULL UNIQUE,
    eventProject_logo_path            VARCHAR(255)    NOT NULL,
    eventProject_name                 VARCHAR(255)    NOT NULL,
    eventProject_URL                  VARCHAR(255)    NOT NULL,
    eventProject_date                 DATE            NOT NULL,
    hackerSpace_GUID                  VARCHAR(255)    NOT NULL,

    PRIMARY KEY (eventProject_GUID),
    INDEX (eventProject_name),
    FOREIGN KEY (hackerSpace_GUID) REFERENCES hackerSpace(hackerSpace_GUID)
);

CREATE TABLE hackersEventProject (
    hackerSpace_GUID                  CHAR(16)        NOT NULL UNIQUE,
    eventProject_GUID                 CHAR(16)        NOT NULL UNIQUE,
    hackersEventProject_signature     VARCHAR(255)    NOT NULL,

    PRIMARY KEY (hackerSpace_GUID, eventProject_GUID),
    FOREIGN KEY (hackerSpace_GUID) REFERENCES hackerSpace(hackerSpace_GUID),
    FOREIGN KEY (eventProject_GUID) REFERENCES eventProject(eventProject_GUID)
);

# --- !Downs

DROP DATABASE passport;