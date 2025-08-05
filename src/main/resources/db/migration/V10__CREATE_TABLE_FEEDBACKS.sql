CREATE TABLE feedbacks (
    id BIGINT PRIMARY KEY,
    description TEXT NOT NULL,
    rating INT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);