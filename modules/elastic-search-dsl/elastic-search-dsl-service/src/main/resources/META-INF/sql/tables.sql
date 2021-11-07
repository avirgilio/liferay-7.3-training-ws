create table DSL_Course (
	uuid_ VARCHAR(75) null,
	courseId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	courseName VARCHAR(75) null,
	courseDescription VARCHAR(75) null,
	courseType INTEGER
);