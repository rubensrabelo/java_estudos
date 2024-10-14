CREATE TABLE IF NOT EXISTS public.task (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    description VARCHAR(255) COLLATE pg_catalog."default",
    name VARCHAR(255) COLLATE pg_catalog."default",
    task_status INTEGER,
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE
);