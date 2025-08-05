ALTER TABLE users
DROP COLUMN role;

ALTER TABLE users
ADD COLUMN user_role users_roles;