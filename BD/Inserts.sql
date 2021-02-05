INSERT INTO public."User"(
	document_id, name, username, password)
	VALUES (26254452, 'Franco Marino', 'francomarino', 'admin1234');
INSERT INTO public."User"(
	document_id, name, username, password)
	VALUES (12345678, 'Julio Diaz', 'diazjulio', 'admin1234');
INSERT INTO public."User"(
	document_id, name, username, password)
	VALUES (87654321, 'Jesús Cádiz', 'cadizjesus', 'admin1234');

INSERT INTO public."Account"(
	"number", current_balance, fk_document_id)
	VALUES (100200300, 500000, 26254452);
INSERT INTO public."Account"(
	"number", current_balance, fk_document_id)
	VALUES (200300400, 500000, 12345678);
INSERT INTO public."Account"(
	"number", current_balance, fk_document_id)
	VALUES (500600700, 500000, 87654321);

INSERT INTO public."Operation"(
	id, name)
	VALUES (1, 'Deposit');
INSERT INTO public."Operation"(
	id, name)
	VALUES (2, 'Withdraw');
INSERT INTO public."Operation"(
	id, name)
	VALUES (3, 'Transference');