--------------------------------------------------------
--  DDL for Table DDD_COFFEE
--------------------------------------------------------

  CREATE TABLE "DDD_COFFEE" 
   (	"NOME" VARCHAR2(40 BYTE), 
	"PRECO" NUMBER(19,2), 
	"ID" NUMBER(19,0), 
	"DATA_FABRICACAO" DATE, 
	"DATA_VALIDADE" DATE
   ) ;
--------------------------------------------------------
--  DDL for Index DDD_COFFEE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DDD_COFFEE_PK" ON "DDD_COFFEE" ("ID");
--------------------------------------------------------
--  Constraints for Table DDD_COFFEE
--------------------------------------------------------

  ALTER TABLE "DDD_COFFEE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "DDD_COFFEE" ADD CONSTRAINT "DDD_COFFEE_PK" PRIMARY KEY ("ID") ENABLE;
  
  
  
--------------------------------------------------------
--  DDL for Sequence SQ_COFFEE
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_COFFEE"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
