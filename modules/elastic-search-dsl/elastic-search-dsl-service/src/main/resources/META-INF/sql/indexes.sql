create index IX_BA52BB5D on DSL_Course (courseName[$COLUMN_LENGTH:75$]);
create index IX_DC3BA9DE on DSL_Course (courseType, groupId);
create index IX_A129C667 on DSL_Course (groupId);
create index IX_D159D4D7 on DSL_Course (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_582FA519 on DSL_Course (uuid_[$COLUMN_LENGTH:75$], groupId);