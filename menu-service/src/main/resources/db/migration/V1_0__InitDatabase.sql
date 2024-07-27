create table dishes(
    id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL UNIQUE,
    name varchar not null,
    price float not null,
    weight integer,
    description varchar
)