create index IX_77048095 on TD_Todo (userId);
create index IX_4A3B348F on TD_Todo (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E2D592D1 on TD_Todo (uuid_[$COLUMN_LENGTH:75$], groupId);