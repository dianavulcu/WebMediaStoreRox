CREATE TABLE public.media
(
    id integer NOT NULL DEFAULT nextval('media_id_seq'::regclass),
    product_type character varying(10) COLLATE "default".pg_catalog NOT NULL,
    title character varying(50) COLLATE "default".pg_catalog NOT NULL,
    price double precision NOT NULL,
    code character varying(10) COLLATE "default".pg_catalog NOT NULL,
    genre character varying(20) COLLATE "default".pg_catalog DEFAULT NULL::character varying,
    artist character varying(50) COLLATE "default".pg_catalog DEFAULT NULL::character varying,
    directors character varying(50) COLLATE "default".pg_catalog DEFAULT NULL::character varying,
    production_label character varying(50) COLLATE "default".pg_catalog DEFAULT NULL::character varying,
    author character varying(50) COLLATE "default".pg_catalog DEFAULT NULL::character varying
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.media
    OWNER to xvseorxagpkwwn;