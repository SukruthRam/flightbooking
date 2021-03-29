create database flightdb;
use flightdb;

create table flightdb.flights(
   id INT NOT NULL AUTO_INCREMENT,
   flight_id VARCHAR(100) NOT NULL,
   airline VARCHAR(100) NOT NULL,
   flight_description VARCHAR(100) NOT NULL,
   flight_source VARCHAR(100) NOT NULL,
   destination VARCHAR(100) NOT NULL,
   cost INT NOT NULL,
   PRIMARY KEY (id,  flight_description)
);
