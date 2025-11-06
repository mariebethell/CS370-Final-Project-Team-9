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
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_team_num (team_num)
);

-- Junction table for team members (many-to-many relationship)
CREATE TABLE team_members (
    team_member_id INT PRIMARY KEY AUTO_INCREMENT,
    team_id INT NOT NULL,
    account_id INT NOT NULL,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE CASCADE,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE,
    UNIQUE KEY unique_team_member (team_id, account_id),
    INDEX idx_team (team_id),
    INDEX idx_account (account_id)
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

-- Insert sample gym data
INSERT INTO gyms (chain_name, location_number, address, city, state, zip_code, hours, num_courts) VALUES
('Gold''s Gym', 1, '123 Main St', 'Los Angeles', 'CA', '90001', '5:00 AM - 11:00 PM', 4),
('24 Hour Fitness', 1, '456 Oak Ave', 'Los Angeles', 'CA', '90002', '24 Hours', 6),
('Planet Fitness', 1, '789 Pine Rd', 'Los Angeles', 'CA', '90003', '5:00 AM - 10:00 PM', 3),
('LA Fitness', 1, '321 Elm St', 'Los Angeles', 'CA', '90004', '5:00 AM - 11:00 PM', 5),
('Anytime Fitness', 1, '654 Maple Dr', 'Los Angeles', 'CA', '90005', '24 Hours', 2),
('Equinox', 1, '987 Beach Blvd', 'Los Angeles', 'CA', '90006', '6:00 AM - 10:00 PM', 8),
('Crunch Fitness', 1, '147 Valley Ln', 'Los Angeles', 'CA', '90007', '5:00 AM - 11:00 PM', 4),
('YMCA', 1, '258 Park Ave', 'Los Angeles', 'CA', '90008', '5:30 AM - 9:00 PM', 6),
('Snap Fitness', 1, '369 Hill St', 'Los Angeles', 'CA', '90009', '24 Hours', 2),
('Life Time Fitness', 1, '741 Lake Dr', 'Los Angeles', 'CA', '90010', '4:00 AM - 11:00 PM', 10);

-- Insert sample account data (using unhashed passwords for now)
INSERT INTO accounts (name, email, password_hash, team_num) VALUES
('Marie', 'marie@example.com', 'password', NULL),
('Benjamin', 'benjamin@example.com', 'password', NULL),
('Sarah', 'sarah@example.com', 'password', NULL),
('Logan', 'logan@example.com', 'password', NULL);