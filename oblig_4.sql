-- Names in SQL must begin with a letter (a-z) or underscore (_).
-- Subsequent characters in a name can be letters, digits (0-9), or underscores.

-- Dette eksemplet innholder en 1:N-forbindelse mellom entitetstypene ansatt og avdeling.

-- Sletter hele sulamitten og oppretter på nytt.
DROP SCHEMA IF EXISTS oblig_4 CASCADE;
CREATE SCHEMA oblig_4;
SET search_path TO oblig_4;
--

CREATE TABLE paameldingForm (
  mobil CHARACTER (8) PRIMARY KEY,
  hash CHARACTER (64) NOT NULL,
  salt CHARACTER (32) NOT NULL,
  fornavn CHARACTER VARYING (20),
  etternavn CHARACTER VARYING (20),
  kjonn CHARACTER CHECK ( kjonn='mann' or kjonn='kvinne')
);

--

INSERT INTO
    paameldingForm(mobil, hash, salt, fornavn, etternavn, kjonn)
VALUES
    ('12345678', 'aaa', 'aaa', 'aaa', 'aaa', 'mann')
;