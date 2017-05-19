insert ignore into sec_group(groupname, groupdesc) values('CUSTOMERS','This group contains customers.');
insert ignore into sec_group(groupname, groupdesc) values('EMPLOYEES','This group contains employees.');

insert ignore into sec_user(username, password) values('puneet',SHA2('ojha',256)); 
insert ignore into sec_user(username, password) values('customer2',SHA2('customer2',256)); 
insert ignore into sec_user(username, password) values('paras',SHA2('singh',256));
insert ignore into sec_user(username, password) values('employee2',SHA2('employee2',256)); 

insert ignore into sec_user_groups(username, groupname) values('puneet','CUSTOMERS');
insert ignore into sec_user_groups(username, groupname) values('customer2','CUSTOMERS');
insert ignore into sec_user_groups(username, groupname) values('paras','EMPLOYEES');
insert ignore into sec_user_groups(username, groupname) values('employee2','EMPLOYEES');

INSERT INTO productdetails(PRODUCTID,PRODUCTDESCRIPTION,QUANTITY,PRICE,IMAGE) VALUES(101,'Bolt',1250,250,'http://www.secure-fasteners.co.uk/contentimages/0/0/high%20tensile%20bolts.jpg');
INSERT INTO productdetails(PRODUCTID,PRODUCTDESCRIPTION,QUANTITY,PRICE,IMAGE) VALUES(102,'Nut',1100,200,'http://www.fasteningproducts.net/images/nuts.png');
INSERT INTO productdetails(PRODUCTID,PRODUCTDESCRIPTION,QUANTITY,PRICE,IMAGE) VALUES(103,'Screw Driver',150,90,'http://www.pngpix.com/wp-content/uploads/2016/11/PNGPIX-COM-Screwdriver-Vector-PNG-Transparent-Image.png');
INSERT INTO productdetails(PRODUCTID,PRODUCTDESCRIPTION,QUANTITY,PRICE,IMAGE) VALUES(104,'Plier',400,110,'http://pngimg.com/uploads/plier/plier_PNG10063.png');
INSERT INTO productdetails(PRODUCTID,PRODUCTDESCRIPTION,QUANTITY,PRICE,IMAGE) VALUES(105,'Nails',2000,5,'https://3.imimg.com/data3/TA/AT/MY-3126718/ms-nails-250x250.png');
INSERT INTO invoiceproducts(SNO,QUANTITY,RATE,TAX,TOTAL,PRODUCTDETAILS_PRODUCTID)VALUES(1,30,250,750,8250,101);
INSERT INTO invoiceproducts(SNO,QUANTITY,RATE,TAX,TOTAL,PRODUCTDETAILS_PRODUCTID)VALUES(2,20,200,400,4400,102);
INSERT INTO invoiceproducts(SNO,QUANTITY,RATE,TAX,TOTAL,PRODUCTDETAILS_PRODUCTID)VALUES(3,20,200,400,4400,102);
INSERT INTO customer(ID,ADDRESS,CONTACTNO,EMAIL,FNAME,LNAME,USERNAME,CREATEDDATE)VALUES(210,'3001 S King Dr',3129756222,'a210@gmail.com','puneet','ojha','puneet',current_timestamp);
INSERT INTO customer(ID,ADDRESS,CONTACTNO,EMAIL,FNAME,LNAME,USERNAME,CREATEDDATE)VALUES(211,'2951 S King Dr',3129999222,'a211@gmail.com','vishal','singh','customer2',current_timestamp);
INSERT INTO invoice(SNO,DATE,PARTYNAME,TOTAL,CUSTOMER_ID,DISPATCH_ID)VALUES(2,'2017-02-03','Ankit industries',4400,210,null);
INSERT INTO invoice(SNO,DATE,PARTYNAME,TOTAL,CUSTOMER_ID,DISPATCH_ID)VALUES(1,'2017-02-03','Ankit industries',12650,210,null);
INSERT INTO invoice_invoiceproducts(Invoice_SNO,invoiceproducts_SNO)VALUES(1,1);
INSERT INTO invoice_invoiceproducts(Invoice_SNO,invoiceproducts_SNO)VALUES(1,2);
INSERT INTO invoice_invoiceproducts(Invoice_SNO,invoiceproducts_SNO)VALUES(2,3);
INSERT INTO employee(ID,ADDRESS,CONTACTNO,EMAIL,FNAME,LNAME,USERNAME,CREATEDDATE)VALUES(43,'2951 S King Dr',6174872830,'pookk@hotmail.com','paras','singh','paras',current_timestamp);
INSERT INTO employee(ID,ADDRESS,CONTACTNO,EMAIL,FNAME,LNAME,USERNAME,CREATEDDATE)VALUES(44,'3001 S King Dr',9875887830,'ajkdh@gmail.com','Sumit','Asati','employee2',current_timestamp);
INSERT INTO dispatch(ID,DATE,DELIVERYDATE,SHIPPINGDETAILS,EMPLOYEE_ID,INVOICE_SNO)VALUES(31,'2017-03-02','2017-04-04','By Transportation',43,1);
UPDATE invoice SET `DISPATCH_ID`='31' WHERE `SNO`='1';
