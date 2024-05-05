create database oceansync;

use oceansync;

create table user(
                     userId varchar(10) primary key,
                     userName varchar (20),
                     password varchar (15)
);

create table stock(
                      itemId varchar(10) primary key,
                      name varchar(30),
                      type varchar(40),
                      qty int(200),
                      userId varchar(10),
                      foreign key(userId) references user(userId) on update cascade on delete cascade
);

create table employee(
                         id varchar(10) primary key,
                         employeeId varchar(10),
                         name varchar(30),
                         activity varchar(40),
                         month varchar(15),
                         salary decimal(10,2),
                         date date,
                         userId varchar(10),
                         foreign key(userId) references user(userId) on update cascade on delete cascade
);

create table customer(
                         customerId varchar(10) primary key,
                         name varchar(30),
                         address varchar(40),
                         tel varchar(11),

);

create table customerAddedDetails(
                                     userId varchar(10),
                                     foreign key(userId) references user(userId) on update cascade on delete cascade,
                                     customerId varchar(10),
                                     foreign key(customerId) references customer(customerId) on update cascade on delete cascade
);

create table discount(
                         discountId varchar (10) primary key,
                         localOrForeign varchar (10),
                         discount int(4)
);

alter table customer add foreign key (discountId)references discount(discountId) on update cascade on delete cascade;

create table payment(
                        paymentId varchar(10) primary key,
                        type varchar(40),
                        amount decimal(10,2),
                        date date,
                        customerId varchar(10),
                        foreign key(customerId) references customer(customerId) on update cascade on delete cascade
);

create table activity(
                         activityId varchar(10) primary key,
                         name varchar(30),
                         type varchar(40),
                         location varchar(40),
                         cost decimal(10,2)




);

create table cource(
                       courceId varchar(10) primary key,
                       name varchar(30),
                       duration varchar(30),
                       cost decimal(10,2)
);

create table selectedCource(
                               customerId varchar(10),
                               foreign key(customerId) references customer(customerId) on update cascade on delete cascade,
                               courceId varchar(10),
                               foreign key(courceId) references cource(courceId) on update cascade on delete cascade,
                               date date
);



create table selectedActivity(
                                 activityId varchar(10),
                                 foreign key(activityId) references activity(activityId) on update cascade on delete cascade,
                                 customerId varchar(10),
                                 foreign key(customerId) references customer(customerId) on update cascade on delete cascade,
                                 date date
);

