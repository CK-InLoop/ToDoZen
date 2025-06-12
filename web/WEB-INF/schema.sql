-- H2 Database schema for ToDoZen
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE,
    priority VARCHAR(10),
    completed BOOLEAN DEFAULT FALSE,
    tags VARCHAR(255),
    created_at DATE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
