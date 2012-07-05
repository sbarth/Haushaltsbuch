use finance_db;

delete from transaction where id > 0;
delete from category where id > 0;
delete from account where id > 0;

insert into account(name, description, logo, start_amount) values ("Sparkasse", "Dresden", "", "10000.00");
insert into account(name, description, logo, start_amount) values ("Sparkasse", "Oberlausitz Niederschlesien", "", "10000.00");
insert into account(name, description, logo, start_amount) values ("Dresdner Bank", "Berlin", "", "10000.00");

insert into category(name) values ("Allgemein");
insert into category(name) values ("Auto");
insert into category(name) values ("Haushalt");
insert into category(parent_id, name) values (1, "Einzahlung");
insert into category(parent_id, name) values (1, "Auszahlung");
insert into category(parent_id, name) values (2, "Benzin");
insert into category(parent_id, name) values (2, "Versicherung");
insert into category(parent_id, name) values (2, "Bußgeld");
insert into category(parent_id, name) values (2, "Steuer");
insert into category(parent_id, name) values (3, "Lebensmittel");
insert into category(parent_id, name) values (3, "Möbel");
insert into category(parent_id, name) values (3, "Miete");

select * from account;
select * from category;
select count(*) from transaction;