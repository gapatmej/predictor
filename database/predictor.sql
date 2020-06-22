--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: restriction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.restriction (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    day character varying(255) NOT NULL,
    plate_number integer NOT NULL,
    schedule_id bigint,
    CONSTRAINT restriction_plate_number_check CHECK (((plate_number >= 0) AND (plate_number <= 9)))
);


ALTER TABLE public.restriction OWNER TO postgres;

--
-- Name: restriction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.restriction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.restriction_id_seq OWNER TO postgres;

--
-- Name: restriction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.restriction_id_seq OWNED BY public.restriction.id;


--
-- Name: schedule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.schedule (
    id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    from_hour integer NOT NULL,
    from_minute integer NOT NULL,
    to_hour integer NOT NULL,
    to_minute integer NOT NULL,
    CONSTRAINT schedule_from_hour_check CHECK (((from_hour >= 0) AND (from_hour <= 24))),
    CONSTRAINT schedule_from_minute_check CHECK (((from_minute >= 0) AND (from_minute <= 60))),
    CONSTRAINT schedule_to_hour_check CHECK (((to_hour >= 0) AND (to_hour <= 24))),
    CONSTRAINT schedule_to_minute_check CHECK (((to_minute >= 0) AND (to_minute <= 60)))
);


ALTER TABLE public.schedule OWNER TO postgres;

--
-- Name: schedule_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.schedule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.schedule_id_seq OWNER TO postgres;

--
-- Name: schedule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.schedule_id_seq OWNED BY public.schedule.id;


--
-- Name: restriction id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.restriction ALTER COLUMN id SET DEFAULT nextval('public.restriction_id_seq'::regclass);


--
-- Name: schedule id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schedule ALTER COLUMN id SET DEFAULT nextval('public.schedule_id_seq'::regclass);


--
-- Data for Name: restriction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.restriction (id, created_by, created_date, last_modified_by, last_modified_date, day, plate_number, schedule_id) FROM stdin;
3	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	MONDAY	1	1
4	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	MONDAY	1	2
5	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	MONDAY	2	1
6	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	MONDAY	2	2
7	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	TUESDAY	3	1
8	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	TUESDAY	3	2
9	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	TUESDAY	4	1
10	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	TUESDAY	4	2
11	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	WEDNESDAY	5	1
12	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	WEDNESDAY	5	2
13	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	WEDNESDAY	6	1
14	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	WEDNESDAY	6	2
15	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	THURSDAY	7	1
16	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	THURSDAY	7	2
17	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	THURSDAY	8	1
18	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	THURSDAY	8	2
19	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	FRIDAY	9	1
20	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	FRIDAY	9	2
21	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	FRIDAY	0	1
22	gapatmej	2020-06-22 00:00:00	gapatmej	2020-06-22 00:00:00	FRIDAY	0	2
\.


--
-- Data for Name: schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.schedule (id, created_by, created_date, last_modified_by, last_modified_date, from_hour, from_minute, to_hour, to_minute) FROM stdin;
1	gapatmej	2020-01-01 00:00:00	gapatmej	2020-01-01 00:00:00	7	30	9	30
2	gapatmej	2020-01-01 00:00:00	gapatmej	2020-01-01 00:00:00	16	0	19	30
\.


--
-- Name: restriction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.restriction_id_seq', 22, true);


--
-- Name: schedule_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.schedule_id_seq', 1, false);


--
-- Name: restriction restriction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.restriction
    ADD CONSTRAINT restriction_pkey PRIMARY KEY (id);


--
-- Name: schedule schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (id);


--
-- Name: restriction fkoawlha06ce6i14qbpyd9neg87; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.restriction
    ADD CONSTRAINT fkoawlha06ce6i14qbpyd9neg87 FOREIGN KEY (schedule_id) REFERENCES public.schedule(id);


--
-- PostgreSQL database dump complete
--

