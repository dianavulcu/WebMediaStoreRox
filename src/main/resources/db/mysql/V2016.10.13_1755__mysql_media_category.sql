ALTER TABLE media 
ADD COLUMN age_category VARCHAR(45) DEFAULT 'GENERAL_AUDIENCE' AFTER author,
ADD COLUMN price_category VARCHAR(45) DEFAULT 'REGULAR' AFTER age_category

