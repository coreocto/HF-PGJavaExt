-- Function: public.h(character varying, character varying)

-- DROP FUNCTION public.h(character varying, character varying);

CREATE OR REPLACE FUNCTION public.h(
    searchtoken character varying,
    pseudorandomval character varying)
  RETURNS character varying AS
'org.coreocto.dev.hf.pgjavaext.SuisePgExt.H(java.lang.String,java.lang.String)'
  LANGUAGE java IMMUTABLE STRICT
  COST 100;
ALTER FUNCTION public.h(character varying, character varying)
  OWNER TO postgres;

-- Function: public.r(integer)

-- DROP FUNCTION public.r(integer);

CREATE OR REPLACE FUNCTION public.r(seed integer)
  RETURNS character varying AS
'org.coreocto.dev.hf.pgjavaext.SuisePgExt.R(int)'
  LANGUAGE java IMMUTABLE STRICT
  COST 100;
ALTER FUNCTION public.r(integer)
  OWNER TO postgres;

-- Function: public.chlh_search(character varying, character varying)

-- DROP FUNCTION public.chlh_search(character varying, character varying);

CREATE OR REPLACE FUNCTION public.chlh_search(
    databitset character varying,
    querybitset character varying)
  RETURNS integer AS
'org.coreocto.dev.hf.pgjavaext.Chlh1PgExt.Search(java.lang.String,java.lang.String)'
  LANGUAGE java IMMUTABLE STRICT
  COST 100;
ALTER FUNCTION public.chlh_search(character varying, character varying)
  OWNER TO postgres;
