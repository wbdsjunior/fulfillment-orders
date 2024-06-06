CREATE TABLE IF NOT EXISTS buyer (
          id NUMERIC(10) PRIMARY KEY
        , name VARCHAR(45) NOT NULL
    );

CREATE TABLE IF NOT EXISTS buyer_order (
          buyer_id NUMERIC(10)
        , id NUMERIC(10)
        , sales_date DATE NOT NULL
        , PRIMARY KEY (buyer_id, id)
        , FOREIGN KEY (buyer_id) REFERENCES buyer (id)
    );

CREATE TABLE IF NOT EXISTS buyer_order_product (
          buyer_id NUMERIC(10)
        , order_id NUMERIC(10)
        , id NUMERIC(10)
        , price NUMERIC(10, 2) NOT NULL
        , PRIMARY KEY (buyer_id, buyer_id, id)
        , FOREIGN KEY (buyer_id, order_id) REFERENCES buyer_order (buyer_id, id)
    );
