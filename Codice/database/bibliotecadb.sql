DROP database IF exists bibliotecadb;
CREATE DATABASE bibliotecadb;
USE bibliotecadb;

DROP TABLE IF EXISTS socio;

CREATE TABLE socio (
num_tessera char(6) primary key,
pass char(10) NOT NULL,
nome_S varchar(20) NOT NULL,
cognome_S varchar(20) NOT NULL,
data_nascita_S varchar(10) NOT NULL,
indirizzo varchar(60) NOT NULL
);


DROP TABLE IF EXISTS prestito;


CREATE TABLE prestito (
CodicePR int NOT NULL AUTO_INCREMENT primary key,
data_inizio date not null,
data_fine date not null,
cod_S char(6) not null,
FOREIGN KEY (cod_S) REFERENCES socio(num_tessera) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE IF EXISTS libro;

CREATE TABLE libro (
ISBN char(13) primary key,
titolo varchar(50) NOT NULL,
lingua varchar(20) NOT NULL,
anno_pubblicazione int NOT NULL,
categoria varchar(30) NOT NULL,
autore varchar(20) NOT NULL,
casa_editrice varchar(20) NOT NULL,
cod_pr int default null,
FOREIGN KEY (cod_pr) REFERENCES prestito(CodicePR) ON DELETE SET NULL ON UPDATE CASCADE
);




load data local infile 'C:/Users/Andrea/Desktop/progetto/5.creazione_database/datisocio.sql'
into table socio fields terminated by ',';

load data local infile 'C:/Users/Andrea/Desktop/progetto/5.creazione_database/datiprestito.sql'
into table prestito fields terminated by ',';

load data local infile 'C:/Users/Andrea/Desktop/progetto/5.creazione_database/datilibro.sql'
into table libro fields terminated by ',';


