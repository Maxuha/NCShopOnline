CREATE TABLE IF NOT EXISTS "user" (
	"id" serial NOT NULL UNIQUE,
	"username" varchar(128) NOT NULL UNIQUE,
	"password" varchar(128) NOT NULL,
	CONSTRAINT "user_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE IF NOT EXISTS "customer" (
	"user_id" integer NOT NULL,
	"full_name" varchar(255) NOT NULL,
	CONSTRAINT "customer_pk" PRIMARY KEY ("user_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE IF NOT EXISTS "shipper" (
	"user_id" integer NOT NULL,
	"company_name" varchar(255) NOT NULL,
	CONSTRAINT "shipper_pk" PRIMARY KEY ("user_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE IF NOT EXISTS "order" (
	"id" serial NOT NULL UNIQUE,
	"is_processed" BOOLEAN NOT NULL,
	"date" TIMESTAMP NOT NULL,
	"user_id" integer NOT NULL,
	CONSTRAINT "order_pk" PRIMARY KEY ("id","user_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE IF NOT EXISTS "category" (
	"id" serial NOT NULL UNIQUE,
	"title" varchar(255) NOT NULL,
	"image_id" integer,
	"parent_id" integer,
	CONSTRAINT "category_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE IF NOT EXISTS "product" (
	"id" serial NOT NULL UNIQUE,
	"title" varchar(255) NOT NULL,
	"description" varchar(2048),
	"price" numeric(8, 2) NOT NULL,
	"discount" numeric(3, 2),
	"category_id" integer NOT NULL,
	"shipper_id" integer NOT NULL,
	CONSTRAINT "product_pk" PRIMARY KEY ("id","category_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE IF NOT EXISTS "product_has_order" (
	"product_id" integer NOT NULL,
	"order_id" integer NOT NULL,
	"count" integer NOT NULL,
	CONSTRAINT "product_has_order_pk" PRIMARY KEY ("product_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE IF NOT EXISTS "image" (
	"id" serial NOT NULL UNIQUE,
	"image" TEXT NOT NULL,
	CONSTRAINT "image_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE IF NOT EXISTS "image_to_product" (
	"product_id" integer NOT NULL,
	"image_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);

ALTER TABLE "shipper" DROP CONSTRAINT IF EXISTS "shipper_fk0";
ALTER TABLE "shipper" ADD CONSTRAINT "shipper_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");

ALTER TABLE "customer" DROP CONSTRAINT IF EXISTS "customer_fk0";
ALTER TABLE "customer" ADD CONSTRAINT "customer_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");

ALTER TABLE "order" DROP CONSTRAINT IF EXISTS "order_fk0";
ALTER TABLE "order" ADD CONSTRAINT "order_fk0" FOREIGN KEY ("user_id") REFERENCES "customer"("user_id");

ALTER TABLE "category" DROP CONSTRAINT IF EXISTS "category_fk0";
ALTER TABLE "category" ADD CONSTRAINT "category_fk0" FOREIGN KEY ("image_id") REFERENCES "image"("id");
ALTER TABLE "category" DROP CONSTRAINT IF EXISTS "category_fk1";
ALTER TABLE "category" ADD CONSTRAINT "category_fk1" FOREIGN KEY ("parent_id") REFERENCES "category"("id");

ALTER TABLE "product" DROP CONSTRAINT IF EXISTS "product_fk0";
ALTER TABLE "product" ADD CONSTRAINT "product_fk0" FOREIGN KEY ("category_id") REFERENCES "category"("id");
ALTER TABLE "product" DROP CONSTRAINT IF EXISTS "product_fk1";
ALTER TABLE "product" ADD CONSTRAINT "product_fk1" FOREIGN KEY ("shipper_id") REFERENCES "shipper"("user_id");

ALTER TABLE "product_has_order" DROP CONSTRAINT IF EXISTS "product_has_order_fk0";
ALTER TABLE "product_has_order" ADD CONSTRAINT "product_has_order_fk0" FOREIGN KEY ("product_id") REFERENCES "product"("id");
ALTER TABLE "product_has_order" DROP CONSTRAINT IF EXISTS "product_has_order_fk1";
ALTER TABLE "product_has_order" ADD CONSTRAINT "product_has_order_fk1" FOREIGN KEY ("order_id") REFERENCES "order"("id");

ALTER TABLE "image_to_product" DROP CONSTRAINT IF EXISTS "image_to_product_fk0";
ALTER TABLE "image_to_product" ADD CONSTRAINT "image_to_product_fk0" FOREIGN KEY ("product_id") REFERENCES "product"("id");
ALTER TABLE "image_to_product" DROP CONSTRAINT IF EXISTS "image_to_product_fk1";
ALTER TABLE "image_to_product" ADD CONSTRAINT "image_to_product_fk1" FOREIGN KEY ("image_id") REFERENCES "image"("id");

