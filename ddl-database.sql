-- Database: prueba

-- DROP DATABASE IF EXISTS prueba;

CREATE DATABASE prueba
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Ecuador.1252'
    LC_CTYPE = 'Spanish_Ecuador.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
	

-- Table: public.vehiculos

-- DROP TABLE IF EXISTS public.vehiculos;

CREATE TABLE IF NOT EXISTS public.vehiculos
(
    id bigint NOT NULL DEFAULT nextval('vehiculos_id_seq'::regclass),
    "año" character varying(255) COLLATE pg_catalog."default",
    fecha_de_compra date,
    modelo character varying(255) COLLATE pg_catalog."default",
    observaciones character varying(255) COLLATE pg_catalog."default",
    placa character varying(255) COLLATE pg_catalog."default",
    precio real,
    CONSTRAINT vehiculos_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.vehiculos
    OWNER to postgres;