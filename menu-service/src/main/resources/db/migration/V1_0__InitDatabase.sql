create table menu_items(
    id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL UNIQUE,
    name varchar,
    price float,
    weight integer,
    description varchar
)