drop table cars;
drop table pets;
drop table residents;
drop table apartments;


create table apartments(
	id serial primary key,
	building_letter varchar (1),
	room_number numeric (3,0),
	monthly_rent numeric (5,0)
);
create table residents(
	id serial primary key,
	first_name varchar (60),
	last_name varchar (60),
	apartment_id integer references apartments(id)
);
create table cars(
	id serial primary key,
	make varchar (60),
	model varchar (60),
	year numeric(4,0),
	license_plate varchar (8),
	owner_id integer references residents(id)
);
create table pets(
	id serial primary key,
	breed varchar (60),
	name varchar (60),
	apartment_id integer references apartments(id),
	is_service_animal numeric (1,0)
);


INSERT INTO apartments VALUES
  (1, 'A', 1, 1250),
  (2, 'B', 1, 1300),
  (3, 'C', 1, 1500),
  (4, 'C', 2, 1400);

INSERT INTO residents(first_name,last_name,apartment_id) VALUES
  ('Jacob', 'Davis', 1),
  ('Sally', 'Bobson', 2),
  ('Ricky', 'Bobson', 2),
  ('Martha', 'Stuart', 3),
  ('Jackie', 'Samson', 4);

INSERT INTO cars VALUES
  (1, 'Toyota', 'Corolla', 1995, 'IGB18SS', 2),
  (2, 'Honda', 'Civic', 2001, 'FFGB91S', 3);

INSERT INTO pets VALUES
  (1, 'cat', 'Jimmy', 3, 0),
  (2, 'dog', 'Spot', 4, 1);
 
 
select * from residents;

select * from residents
join apartments
on residents.apartment_id = apartments.id
where building_letter='B';

select building_letter, avg(monthly_rent) as average_rent
from apartments
group by building_letter;


select count(pets) from pets;


select first_name,last_name from residents
join apartments ON residents.apartment_id = apartments.id
join pets on apartments.id = pets.apartment_id 
where pets."name" = 'Spot';


select make from cars
join residents on cars.owner_id = residents.id
where first_name = 'Sally' and last_name = 'Bobson'


















