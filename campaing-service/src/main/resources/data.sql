INSERT into CAMPAIGN(ID, NAME, START_DATE, END_DATE)
  VALUES(uuid(), 'Campaign 1', now(), DATEADD('DAY', 1, now()));

INSERT into CAMPAIGN(ID, NAME, START_DATE, END_DATE)
  VALUES(uuid(), 'Campaign 2', now(), now());

INSERT into CAMPAIGN(ID, NAME, START_DATE, END_DATE)
  VALUES(uuid(), 'Campaign 3', now(), now());

INSERT into CAMPAIGN(ID, NAME, START_DATE, END_DATE)
  VALUES(uuid(), 'Campaign 4', now(), DATEADD('DAY', 1, now()));

INSERT into CAMPAIGN(ID, NAME, START_DATE, END_DATE)
  VALUES(uuid(), 'Campaign 5', now(), DATEADD('DAY', 5, now()));

INSERT into CAMPAIGN(ID, NAME, START_DATE, END_DATE)
  VALUES(uuid(), 'Campaign 6', now(), DATEADD('DAY', 10, now()));

INSERT into CAMPAIGN(ID, NAME, START_DATE, END_DATE)
  VALUES(uuid(), 'Campaign 7', now(), DATEADD('DAY', 30, now()));
