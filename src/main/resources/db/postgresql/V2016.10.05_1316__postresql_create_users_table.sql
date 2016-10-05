CREATE TABLE public.users
(
    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    username character varying(200) COLLATE "default".pg_catalog NOT NULL,
    first_name character varying(70) COLLATE "default".pg_catalog DEFAULT NULL::character varying,
    last_name character varying(70) COLLATE "default".pg_catalog DEFAULT NULL::character varying,
    email character varying(70) COLLATE "default".pg_catalog NOT NULL,
    password character varying(70) COLLATE "default".pg_catalog DEFAULT NULL::character varying,
    uuid character varying(50) COLLATE "default".pg_catalog DEFAULT NULL::character varying,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to xvseorxagpkwwn;