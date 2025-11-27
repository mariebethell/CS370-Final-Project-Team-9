-- Create the database
CREATE DATABASE IF NOT EXISTS gym_scheduler;
USE gym_scheduler;

-- Table for user accounts
CREATE TABLE accounts (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    team_num INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email),
    INDEX idx_name (name)
);

-- Table for gym locations
CREATE TABLE gyms (
    gym_id INT PRIMARY KEY AUTO_INCREMENT,
    chain_name VARCHAR(100) NOT NULL,
    location_number INT,
    address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(50),
    zip_code VARCHAR(10),
    hours VARCHAR(100),
    num_courts INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_chain (chain_name),
    INDEX idx_location (city, state)
);

-- Table for teams
CREATE TABLE teams (
    team_id INT PRIMARY KEY AUTO_INCREMENT,
    team_num INT NOT NULL,
    player1_id INT,
    player2_id INT,
    player3_id INT,
    player4_id INT,
    player5_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for games
CREATE TABLE games (
    game_id INT PRIMARY KEY AUTO_INCREMENT,
    gym_id INT,
    team1_id INT,
    team2_id INT,
    game_time DATETIME NOT NULL,
    is_full BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (gym_id) REFERENCES gyms(gym_id) ON DELETE SET NULL,
    FOREIGN KEY (team1_id) REFERENCES teams(team_id) ON DELETE SET NULL,
    FOREIGN KEY (team2_id) REFERENCES teams(team_id) ON DELETE SET NULL,
    INDEX idx_game_time (game_time),
    INDEX idx_gym (gym_id)
);

-- Gym data
INSERT INTO gyms (chain_name, location_number, address, city, state, zip_code, hours, num_courts) VALUES
('The Facility Sports', 1, '1285 Stone Dr #101', 'San Marcos', 'CA', '92078', '6:00 AM - 10:00 PM', 4),
('LA Fitness', 1, '125 N Twin Oaks Valley Rd', 'San Marcos', 'CA', '92069', '5:00 AM - 11:00 PM', 3),
('Corky Smith Gymnasium', 1, '274 Pico Ave', 'San Marcos', 'CA', '92069', '6:00 AM - 9:00 PM', 2),
('CSUSM Clarke Field House', 1, '333 S Twin Oaks Valley Rd', 'San Marcos', 'CA', '92078', '6:00 AM - 10:00 PM', 5),
('The Hoop House San Marcos', 1, '247 Venture St #100', 'San Marcos', 'CA', '92078', '24 Hours', 6),
('Redline Athletics San Marcos', 1, '2937 Norman Strasse Rd', 'San Marcos', 'CA', '92069', '3:00 PM - 9:00 PM', 2),
('EōS Fitness', 1, '3533 Cannon Rd', 'Oceanside', 'CA', '92056', '5:00 AM - 10:00 PM', 3),
('Performance360 Strength & Conditioning', 1, '809 W San Marcos Blvd', 'San Marcos', 'CA', '92078', '6:00 AM - 8:00 PM', 2),
('MOA Fitness', 1, '737 Windy Point Dr Suite H & I', 'San Marcos', 'CA', '92069', '5:00 AM - 9:00 PM', 2),
('OHM Fitness San Marcos Grand Plaza', 1, '137 S Las Posas Rd suite 253', 'San Marcos', 'CA', '92078', '5:00 AM - 10:00 PM', 2),
('Self Made Training Facility San Marcos', 1, '803 W San Marcos Blvd', 'San Marcos', 'CA', '92078', '5:00 AM - 9:00 PM', 2),
('F45 Training West San Marcos', 1, '727 W San Marcos Blvd UNIT 111', 'San Marcos', 'CA', '92078', '6:00 AM - 8:00 PM', 2),
('Citizen Athletics Club', 1, '1251 Activity Dr Suite E and F', 'Vista', 'CA', '92081', '5:00 AM - 10:00 PM', 3),
('Planet Fitness', 1, '641 S Rancho Santa Fe Rd', 'San Marcos', 'CA', '92078', '24 Hours', 4),
('LA Fitness', 2, '324 Sycamore Ave', 'Vista', 'CA', '92083', '5:00 AM - 11:00 PM', 3),
('Hardcore Fitness San Marcos', 1, '1284 W San Marcos Blvd', 'San Marcos', 'CA', '92078', '24 Hours', 2),
('Vista Athletic Club', 1, '1910 Shadowridge Dr #107', 'Vista', 'CA', '92081', '5:00 AM - 10:00 PM', 3),
('Anytime Fitness', 1, '1234 E Mission Rd', 'San Marcos', 'CA', '92069', '24 Hours', 2),
('Bigger Picture Fitness', 1, '1740 La Costa Meadows Dr #250', 'San Marcos', 'CA', '92078', '5:00 AM - 9:00 PM', 2);

-- Insert sample account data (using unhashed passwords for now)
INSERT INTO accounts (name, email, password_hash, team_num) VALUES
('Marie', 'marie@example.com', 'password', NULL),
('Benjamin', 'benjamin@example.com', 'password', NULL),
('Sarah', 'sarah@example.com', 'password', NULL),
('Logan', 'logan@example.com', 'password', NULL);