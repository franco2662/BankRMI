CREATE TABLE public."User"
(	
    document_id VARCHAR(20)  NOT NULL,
    name VARCHAR(50)  NOT NULL,
    username VARCHAR(20)  NOT NULL,
    password character varying(50)[]  NOT NULL,
    CONSTRAINT "id_user" PRIMARY KEY (document_id)
)

CREATE TABLE public."Account"
(	
    number integer  NOT NULL,
	current_balance numeric(18,2) NOT NULL,
	fk_document_id varchar(20) NOT NULL,
    CONSTRAINT id_account PRIMARY KEY (number),
	CONSTRAINT fk_user FOREIGN KEY(fk_document_id) REFERENCES "User"(document_id)
)

CREATE TABLE public."Transaction"
(	
    id integer  NOT NULL,
	amount numeric(18,2) NOT NULL,
	date timestamp NOT NULL,
    description VARCHAR(20) DEFAULT 'No description' NOT NULL,
	fk_account_source integer NOT NULL,
	fk_account_destination integer NOT NULL,
	fk_operation integer NOT NULL,
	CONSTRAINT "id_transaction" PRIMARY KEY (id),
	CONSTRAINT fk_source FOREIGN KEY(fk_account_source) REFERENCES "Account"(number),
	CONSTRAINT fk_destination FOREIGN KEY(fk_account_destination) REFERENCES "Account"(number),
	CONSTRAINT fk_operation FOREIGN KEY(fk_operation) REFERENCES "Operation"(id)
)

CREATE TABLE public."Operation"
(	
    id integer  NOT NULL,
	name varchar(20) NOT NULL,
	CONSTRAINT "id_operation" PRIMARY KEY (id)
)