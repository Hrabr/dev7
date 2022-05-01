
CREATE TABLE developers(
	id_developers serial PRIMARY KEY,
	name varchar(100) NOT NULL,
	age integer NOT NULL,
	gender varchar(100) NOT NULL,
	salary decimal(10,0) NOT NULL
);
CREATE TABLE skill(
	id_skill serial PRIMARY KEY,
	language varchar(100) NOT NULL,
	level_skill varchar(100) NOT NULL
);
CREATE TABLE companies(
	id_companies serial PRIMARY KEY,
	name_companies varchar(100) NOT NULL,
	country_companies varchar(100) NOT NULL
);
CREATE TABLE customers(
	id_customers serial PRIMARY KEY,
	name_customers varchar(100) NOT NULL,
	country_customers varchar(100) NOT NULL
);
CREATE TABLE projects(
	id_projects serial PRIMARY KEY,
	name_projects varchar(100) NOT NULL,
	companies_id int ,
	customers_id int ,
	start_project date ,
	cost_project decimal(15,0) NOT NULL,
	FOREIGN KEY (companies_id) REFERENCES companies(id_companies) ON DELETE SET NULL,
	FOREIGN KEY (customers_id) REFERENCES customers(id_customers) ON DELETE SET NULL
);
CREATE TABLE developers_projects(
	developer_id int ,
	projects_id int ,
	FOREIGN KEY (developer_id) REFERENCES developers(id_developers) ON DELETE SET NULL,
	FOREIGN KEY (projects_id) REFERENCES projects(id_projects)
);
CREATE TABLE developers_skill(
	developer_id int ,
	skill_id int ,
	FOREIGN KEY (developer_id) REFERENCES developers(id_developers) ON DELETE SET NULL,
	FOREIGN KEY (skill_id) REFERENCES skill(id_skill)
);