CREATE TABLE IF NOT EXISTS cards (
  card_id BIGINT NOT NULL AUTO_INCREMENT,

  user_id INT NOT NULL, -- ✅ aligned with user service

  card_number VARCHAR(50) NOT NULL UNIQUE,

  card_type VARCHAR(20) NOT NULL, -- CREDIT / DEBIT

  total_limit DECIMAL(15,2) NOT NULL,

  amount_used DECIMAL(15,2) NOT NULL DEFAULT 0.00,

  available_amount DECIMAL(15,2) NOT NULL,

  status VARCHAR(20) NOT NULL, -- ACTIVE / BLOCKED / EXPIRED

  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(50) NOT NULL,

  updated_at TIMESTAMP NULL DEFAULT NULL,
  updated_by VARCHAR(50) NULL,

  PRIMARY KEY (card_id)
);