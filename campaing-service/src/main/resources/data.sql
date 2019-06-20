INSERT into CAMPAIGN(ID, NAME, START_DATE, END_DATE)
  VALUES(uuid(), 'Campaign 1', now(), DATEADD('DAY', 1, now()));

INSERT into CAMPAIGN(ID, NAME, START_DATE, END_DATE)
  VALUES(uuid(), 'Campaign 2', now(), now());

INSERT into CAMPAIGN(ID, NAME, START_DATE, END_DATE)
  VALUES(uuid(), 'Campaign 3', now(), now());
