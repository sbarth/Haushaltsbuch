use finance_db;

insert into account(name, description, logo, start_amount) values ("Sparkasse", "Dresden", "", "0.00");
insert into account(name, description, logo, start_amount) values ("Sparkasse", "Oberlausitz Niederschlesien", "", "0.00");

insert into category(name) values ("Allgemein");
insert into category(name) values ("Auto");
insert into category(name) values ("Arbeit");
insert into category(name) values ("Freizeit");
insert into category(name) values ("Haushalt");
insert into category(name) values ("Wohnung");
insert into category(name) values ("Alltag");
insert into category(parent_id, name) values (1, "Einzahlung");
insert into category(parent_id, name) values (1, "Auszahlung");
insert into category(parent_id, name) values (2, "Autoversicherung");

insert into category(parent_id, name) values (2, "Benzin");
insert into category(parent_id, name) values (2, "Kfz-Steuer");
insert into category(parent_id, name) values (2, "Strafzettel");
insert into category(parent_id, name) values (3, "Gehalt");
insert into category(parent_id, name) values (3, "Weihnachtsgeld");
insert into category(parent_id, name) values (3, "Berufsunfähigkeitsversicherung");
insert into category(parent_id, name) values (3, "Rente");
insert into category(parent_id, name) values (4, "Kino");
insert into category(parent_id, name) values (4, "Musik");
insert into category(parent_id, name) values (4, "Film");

insert into category(parent_id, name) values (4, "Computer");
insert into category(parent_id, name) values (4, "Restaurant");
insert into category(parent_id, name) values (5, "Lebensmittel");
insert into category(parent_id, name) values (6, "Möbel");
insert into category(parent_id, name) values (6, "Miete");
insert into category(parent_id, name) values (6, "Renovierung");
insert into category(parent_id, name) values (6, "Hausratversicherung");
insert into category(parent_id, name) values (7, "Kleidung");
insert into category(parent_id, name) values (19, "CD");
insert into category(parent_id, name) values (19, "Schallplatte");

insert into category(parent_id, name) values (20, "DVD");
insert into category(parent_id, name) values (20, "Bluray");
insert into category(parent_id, name) values (20, "VHS");
insert into category(parent_id, name) values (21, "Hardware");
insert into category(parent_id, name) values (21, "Reparatur");
insert into category(parent_id, name) values (21, "Software");


select * from account;
select * from category;
select count(*) from transaction;