CREATE TABLE "xsaj"."t_xs_aj_tz" (
  "c_bh" varchar(32) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL,
  "c_bh_aj" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_jbfy" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_ywlx" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_ajsj" varchar(100)[] COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_sjgbhdq" varchar(100)[] COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_swys" varchar(100)[] COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_sfmgaj" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_ajyxdx" varchar(100)[] COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_sfta" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_sfjabj" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_sfldgw" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_zhxgfs" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "dt_cjsj" timestamp(6) DEFAULT NULL,
  "dt_zhgxsj" timestamp(6) DEFAULT NULL,
  "c_sfsw" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_sfsa" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_sfsg" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "c_sfst" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL,
  CONSTRAINT "t_xs_aj_tz_pkey" PRIMARY KEY ("c_bh")
)
;