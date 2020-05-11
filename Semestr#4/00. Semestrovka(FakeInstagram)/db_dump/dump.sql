--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

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

--
-- Name: FakeInstagram; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "FakeInstagram" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';


ALTER DATABASE "FakeInstagram" OWNER TO postgres;

\connect "FakeInstagram"

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

SET default_with_oids = false;

--
-- Name: chatmessages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chatmessages (
    id integer NOT NULL,
    userid integer,
    pageid character varying(256),
    text character varying(256)
);


ALTER TABLE public.chatmessages OWNER TO postgres;

--
-- Name: chatmessages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.chatmessages_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.chatmessages_id_seq OWNER TO postgres;

--
-- Name: chatmessages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.chatmessages_id_seq OWNED BY public.chatmessages.id;


--
-- Name: comment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comment (
    id bigint NOT NULL,
    date character varying(255),
    idpost bigint NOT NULL,
    namepublicator character varying(255),
    text character varying(255)
);


ALTER TABLE public.comment OWNER TO postgres;

--
-- Name: comment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comment_id_seq OWNER TO postgres;

--
-- Name: comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;


--
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    id integer NOT NULL,
    idpost integer NOT NULL,
    likeofcomment integer DEFAULT 0,
    text character varying(256) NOT NULL,
    date character varying(128) NOT NULL,
    publicatorname character varying(128)
);


ALTER TABLE public.comments OWNER TO postgres;

--
-- Name: comments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comments_id_seq OWNER TO postgres;

--
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comments_id_seq OWNED BY public.comments.id;


--
-- Name: favorites; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.favorites (
    idowner integer NOT NULL,
    idpost integer NOT NULL
);


ALTER TABLE public.favorites OWNER TO postgres;

--
-- Name: persistent_logins; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persistent_logins (
    username character varying(64) NOT NULL,
    series character varying(64) NOT NULL,
    token character varying(64) NOT NULL,
    last_used timestamp without time zone NOT NULL
);


ALTER TABLE public.persistent_logins OWNER TO postgres;

--
-- Name: posts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.posts (
    id integer NOT NULL,
    idpublicator integer NOT NULL,
    text character varying(256) NOT NULL,
    likeofpost integer DEFAULT 0 NOT NULL,
    date character varying(128) NOT NULL,
    place character varying(64) NOT NULL,
    photo character varying(256) NOT NULL
);


ALTER TABLE public.posts OWNER TO postgres;

--
-- Name: posts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.posts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.posts_id_seq OWNER TO postgres;

--
-- Name: posts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.posts_id_seq OWNED BY public.posts.id;


--
-- Name: spring_session; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.spring_session (
    primary_id character(36) NOT NULL,
    session_id character(36) NOT NULL,
    creation_time bigint NOT NULL,
    last_access_time bigint NOT NULL,
    max_inactive_interval integer NOT NULL,
    expiry_time bigint NOT NULL,
    principal_name character varying(100),
    session_bytes bytea
);


ALTER TABLE public.spring_session OWNER TO postgres;

--
-- Name: spring_session_attributes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.spring_session_attributes (
    session_primary_id character(36) NOT NULL,
    attribute_name character varying(200) NOT NULL,
    attribute_bytes bytea NOT NULL
);


ALTER TABLE public.spring_session_attributes OWNER TO postgres;

--
-- Name: subs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subs (
    idwho integer NOT NULL,
    idtowho integer NOT NULL
);


ALTER TABLE public.subs OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(32),
    surname character varying(32),
    email character varying(64) NOT NULL,
    hashpassword character varying(128) NOT NULL,
    role character varying(16),
    state character varying(32),
    photo_path character varying(128),
    confirm_code character varying(64)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_seq OWNED BY public.users.id;


--
-- Name: chatmessages id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chatmessages ALTER COLUMN id SET DEFAULT nextval('public.chatmessages_id_seq'::regclass);


--
-- Name: comment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment ALTER COLUMN id SET DEFAULT nextval('public.comment_id_seq'::regclass);


--
-- Name: comments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments ALTER COLUMN id SET DEFAULT nextval('public.comments_id_seq'::regclass);


--
-- Name: posts id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts ALTER COLUMN id SET DEFAULT nextval('public.posts_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- Data for Name: chatmessages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.chatmessages (id, userid, pageid, text) FROM stdin;
502	17	17	New user: Ruslan Pashin
503	17	17	New user: Ruslan Pashin
504	17	17	New user: Ruslan Pashin
505	17	17	New user: Ruslan Pashin
506	17	17	New user: Ruslan Pashin
507	17	17	New user: Ruslan Pashin
508	17	17	New user: Ruslan Pashin
509	17	17	New user: Ruslan Pashin
510	17	17	New user: Ruslan Pashin
511	17	17	wqe
512	17	17	New user: Ruslan Pashin
513	15	15	New user: sad sad
514	18	18	New user: Danil Elagin
515	15	15	ДАниЛ ЛОХ
453	15	15	New user: sad sad
454	15	15	hello
455	15	15	Welcome back
456	15	15	i am hungry
457	15	15	Marsel hi
458	15	15	New user: sad sad
459	15	15	New user: sad sad
460	15	15	New user: sad sad
461	15	15	New user: sad sad
462	15	15	New user: sad sad
463	15	15	New user: sad sad
464	15	15	New user: sad sad
465	15	15	New user: sad sad
466	15	15	New user: sad sad
467	15	15	New user: sad sad
468	15	15	New user: sad sad
469	15	15	New user: sad sad
470	15	15	kek
471	15	15	a po4emy ti ne pishesh noviu` user daun
472	17	17	New user: Ruslan Pashin
473	17	17	kekk
474	15	15	New user: sad sad
475	15	15	New user: sad sad
476	15	15	New user: sad sad
477	15	15	New user: sad sad
478	15	15	kek
479	17	17	New user: Ruslan Pashin
480	17	17	New user: Ruslan Pashin
481	15	15	New user: sad sad
482	15	15	New user: sad sad
483	15	15	New user: sad sad
484	15	15	kek
485	17	17	New user: Ruslan Pashin
486	17	17	Hello sad sad
487	15	15	Hello Rus
488	15	15	New user: sad sad
489	17	17	New user: Ruslan Pashin
490	15	15	Hello Ruslan Pashin
491	17	17	How are you sad sad ?!
492	15	15	Good bye bro
493	15	15	bye bye !
494	17	17	New user: Ruslan Pashin
495	15	15	New user: sad sad
496	17	17	HELLO IT ME id 17
497	17	17	sorry, it`s *
498	15	15	dont worry, it`s okey
499	15	15	good night )
500	15	15	bye
501	17	17	u2
516	17	17	New user: Ruslan Pashin
517	17	17	New user: Ruslan Pashin
519	17	17	New user: Ruslan Pashin
518	17	17	New user: Ruslan Pashin
520	17	17	New user: Ruslan Pashin
521	17	17	New user: Ruslan Pashin
522	19	19	New user: Admin Admin
523	19	19	New user: Admin Admin
\.


--
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comment (id, date, idpost, namepublicator, text) FROM stdin;
\.


--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comments (id, idpost, likeofcomment, text, date, publicatorname) FROM stdin;
2	8	0	hello	12 aprelya	\N
3	8	0	hello2	12 aprelya	\N
4	10	0	AYE	Sat May 09 22:20:13 MSK 2020	Admin Admini4
5	10	0	AYE@2	Sat May 09 22:22:38 MSK 2020	Admin Admini4
\.


--
-- Data for Name: favorites; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.favorites (idowner, idpost) FROM stdin;
17	8
17	8
17	8
17	4
19	10
\.


--
-- Data for Name: persistent_logins; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.persistent_logins (username, series, token, last_used) FROM stdin;
\.


--
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.posts (id, idpublicator, text, likeofpost, date, place, photo) FROM stdin;
4	15	My first post	0	Thu Apr 09 23:05:16 MSK 2020	KAZAN	http://localhost/files/cb5adb14-a725-4310-a08a-4cb06122dd4f.jpg
5	15	My Second Post	0	Thu Apr 09 23:05:35 MSK 2020	Moscow	http://localhost/files/6964028c-0961-4012-9650-8550757d2fc0.jpg
6	16	Me and Ariella	0	Thu Apr 09 23:17:54 MSK 2020	Kazan	http://localhost/files/2c3c9160-ef60-4f9f-aa7d-c73643635c85.jpg
7	15	My third post	0	Thu Apr 09 23:43:50 MSK 2020	DVOIKA	http://localhost/files/bf505ab6-1aca-4890-8a2e-601df8acceb5.jpg
8	17	check search #keks	0	Fri May 01 01:06:20 MSK 2020	KAZAHSTAN!!!!!!	http://localhost/files/b51703fd-7b6c-4d04-94b0-5f0c7947aed4.png
9	17	2 post #keks	0	Fri May 01 01:57:40 MSK 2020	kakakskd	http://localhost/files/b774998d-c481-48ec-8f25-2d6271fa11e2.png
10	19	#firstpost	0	Sat May 09 18:08:22 MSK 2020	Kazan	http://localhost/files/388869e9-ace7-4a4e-ae8a-b0af39d42e7d.JPG
11	19	#fisrtpost	0	Sat May 09 18:09:37 MSK 2020	Chistopol	http://localhost/files/a9be67aa-2ea6-423a-8a84-50d6049070c4.JPG
12	19	kek	0	Sat May 09 18:46:52 MSK 2020	kek	http://localhost/files/3d74c924-9358-4291-9738-9d2020d618b7.JPG
13	19	check	0	Sat May 09 19:29:18 MSK 2020	check	http://localhost/files/be3a8781-a461-45cb-81fc-97b9e3ae97cd.JPG
\.


--
-- Data for Name: spring_session; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.spring_session (primary_id, session_id, creation_time, last_access_time, max_inactive_interval, expiry_time, principal_name, session_bytes) FROM stdin;
\.


--
-- Data for Name: spring_session_attributes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.spring_session_attributes (session_primary_id, attribute_name, attribute_bytes) FROM stdin;
\.


--
-- Data for Name: subs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subs (idwho, idtowho) FROM stdin;
17	16
17	15
19	15
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name, surname, email, hashpassword, role, state, photo_path, confirm_code) FROM stdin;
18	Danil	Elagin	danelag00@gmail.com	$2a$10$vbvQGrtQkjJutE3gnvekne3wje6sGnbbVg14VcOqMxP4lDsBWQikW	USER	CONFIRMED	http://localhost/files/20e9ad41-ac49-4812-b2b1-322799f5742e.PNG	bde3a24f-6c7b-43dc-8e54-72a6522cb0ff
15	sad	sad	empty-12@mail.ru	$2a$10$2LnyzeUgwzZtUIFe44QMN.TV9QFIiS21mtdtl0cGhn4nIlLa/wHcO	USER	CONFIRMED	http://localhost/files/c4b6e471-bb0c-4644-b377-e33d3e54ff4b.jpg	8d72c762-0669-4a93-81e4-fc4ad862921a
16	Alfiya	Pashina	alfiya1972tat@gmail.com	$2a$10$8tPA0duiCK2wi0/A7zHsmuGd.YVQs10mY209YF8UQXjeKGfOBEcb6	USER	CONFIRMED	http://localhost/files/4c39e3fc-a8ef-4bb5-8498-9e4a0bedb799.jpg	751a450d-99ec-47a6-992b-6970cfc8b237
17	Ruslan	Pashin	empty-15@mail.ru	$2a$10$As85AK3YJuuK917XmxWwPuthSliOX5mXV4ZeACdctMFW0o4VaNFoe	USER	CONFIRMED	http://localhost/files/44e13c24-b141-404b-97b8-29d4f4c310af.jpg	6623180d-47a1-4ee6-b301-5a0a901d4f0c
19	Admin	Admini4	checks@mail.ru	$2a$10$ADcebqC9Ky/tvr5qN3dSieKJ1JE/y5BhkNCvW0bIf4wdFT3D6.4ka	USER	CONFIRMED	http://localhost/files/dba4d6d7-6a7b-4342-b0cc-42accfbc5d96.JPG	23b228bd-cb37-4846-a3e8-62b9e02dddd7
22	123	123	1111111111mail.ru	$2a$10$.HQ2tfp1EczvRiRINwkQ2.MRncZ7QSrROCALsK1VKGvks6CGsBK5S	USER	NOT_CONFIRMED	../img/empty-photo.png	dd8c2f06-6c7e-47c9-9e98-2cbcade41a64
23	Rus	Pash	213	$2a$10$WvV6iuCubmw29FvIqaVbxOB0gq0GZMiTLzwI7SOvwiJudI2EeiLtG	USER	NOT_CONFIRMED	../img/empty-photo.png	f4469fe6-cfdc-414b-b939-8986a3ec843e
24	1	1	12	$2a$10$BKxZAXmjZFhrur39TX1fpudrAYFsI7WYOoKToNHujfmNFCmx1EdpO	USER	NOT_CONFIRMED	../img/empty-photo.png	41fffb24-823e-479b-811b-ac672b97f3b3
\.


--
-- Name: chatmessages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.chatmessages_id_seq', 523, true);


--
-- Name: comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comment_id_seq', 1, false);


--
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comments_id_seq', 5, true);


--
-- Name: posts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.posts_id_seq', 13, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 24, true);


--
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- Name: comments comments_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pk PRIMARY KEY (id);


--
-- Name: persistent_logins persistent_logins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persistent_logins
    ADD CONSTRAINT persistent_logins_pkey PRIMARY KEY (series);


--
-- Name: posts posts_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_pk PRIMARY KEY (id);


--
-- Name: spring_session_attributes spring_session_attributes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spring_session_attributes
    ADD CONSTRAINT spring_session_attributes_pk PRIMARY KEY (session_primary_id, attribute_name);


--
-- Name: spring_session spring_session_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spring_session
    ADD CONSTRAINT spring_session_pk PRIMARY KEY (primary_id);


--
-- Name: users user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- Name: spring_session_ix1; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX spring_session_ix1 ON public.spring_session USING btree (session_id);


--
-- Name: spring_session_ix2; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX spring_session_ix2 ON public.spring_session USING btree (expiry_time);


--
-- Name: spring_session_ix3; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX spring_session_ix3 ON public.spring_session USING btree (principal_name);


--
-- Name: spring_session_attributes spring_session_attributes_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spring_session_attributes
    ADD CONSTRAINT spring_session_attributes_fk FOREIGN KEY (session_primary_id) REFERENCES public.spring_session(primary_id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

