-- Table: public.tdocument_indexes

-- DROP TABLE public.tdocument_indexes;

CREATE TABLE public.tdocument_indexes
(
  cdocid text,
  ctoken text,
  corder integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tdocument_indexes
  OWNER TO postgres;

-- Index: public.tdocument_indexes_cdocid_idx

-- DROP INDEX public.tdocument_indexes_cdocid_idx;

CREATE INDEX tdocument_indexes_cdocid_idx
  ON public.tdocument_indexes
  USING btree
  (cdocid COLLATE pg_catalog."default");

-- Table: public.tdocuments

-- DROP TABLE public.tdocuments;

CREATE TABLE public.tdocuments
(
  cdocid text,
  cdelete integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tdocuments
  OWNER TO postgres;

-- Index: public.tdocuments_cdocid_idx

-- DROP INDEX public.tdocuments_cdocid_idx;

CREATE INDEX tdocuments_cdocid_idx
  ON public.tdocuments
  USING btree
  (cdocid COLLATE pg_catalog."default");

-- Table: public.tquery_statistics

-- DROP TABLE public.tquery_statistics;

CREATE TABLE public.tquery_statistics
(
  cqueryid text,
  cstarttime bigint,
  cendtime bigint,
  cmatchedcnt integer,
  cdata text
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tquery_statistics
  OWNER TO postgres;

-- Table: public.tstatistics

-- DROP TABLE public.tstatistics;

CREATE TABLE public.tstatistics
(
  cdocid text,
  cstarttime bigint,
  cendtime bigint,
  cwordcnt integer,
  cfilesize bigint,
  ctype text
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tstatistics
  OWNER TO postgres;

-- Table: public.tusers

-- DROP TABLE public.tusers;

CREATE TABLE public.tusers
(
  cuserid text,
  csalt text
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tusers
  OWNER TO postgres;

