CREATE DATABASE bdfinanceiro;

CREATE TABLE conta
(
	numero int PRIMARY KEY,
	agencia int,
	cpf int(11),
	saldo double(10,2)
);

CREATE TABLE saque
(
	id int AUTO_INCREMENT PRIMARY KEY,
	valor double(10,2),
	nrconta int,
	FOREIGN KEY(nrconta) REFERENCES conta(numero)
);

CREATE TABLE deposito
(
	id int AUTO_INCREMENT PRIMARY KEY,
	valor double(10,2),
	nrconta int,
	FOREIGN KEY(nrconta) REFERENCES conta(numero)
);

/*----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------*/

DELIMITER &&

CREATE PROCEDURE salvar_conta (IN var_numero int, var_agencia int, var_cpf bigint, var_saldo double)
    
BEGIN
	IF(EXISTS(SELECT numero FROM conta WHERE numero = var_numero)) THEN
		UPDATE conta SET numero = var_numero, agencia = var_agencia, cpf = var_cpf, saldo = var_saldo;
	ELSE
		INSERT INTO conta VALUES (var_numero, var_agencia, var_cpf, var_saldo);
	END IF;
END
&&

/*-------------------------------------------------------------------------------------------*/

DELIMITER &&

CREATE PROCEDURE salvar_saque (IN var_id int, var_valor double, var_nrconta int)
    
BEGIN
	IF(EXISTS(SELECT id FROM saque WHERE id = var_id)) THEN
		UPDATE saque SET id = var_id, valor = var_valor, nrconta = var_nrconta;
	ELSE
		INSERT INTO saque VALUES (var_id, var_valor, var_nrconta);
	END IF;
END
&&

/*-------------------------------------------------------------------------------------------*/

DELIMITER &&

CREATE PROCEDURE salvar_deposito (IN var_id int, var_valor double, var_nrconta int)
    
BEGIN
	IF(EXISTS(SELECT id FROM deposito WHERE id = var_id)) THEN
		UPDATE deposito SET id = var_id, valor = var_valor, nrconta = var_nrconta;
	ELSE
		INSERT INTO deposito VALUES (var_id, var_valor, var_nrconta);
	END IF;
END
&&

/*-------------------------------------------------------------------------------------------*/

DELIMITER && 
        CREATE PROCEDURE listar_conta(IN var_numero INT) 
	BEGIN 
		IF(var_numero IS NULL) THEN 
        	SELECT * FROM conta;
      	ELSE 
        	SELECT * FROM conta where numero = var_numero;
		END IF;
END &&

/*-------------------------------------------------------------------------------------------*/

DELIMITER && 
	CREATE PROCEDURE listar_saque(IN var_id INT) 
	BEGIN
		IF(var_id IS NULL) THEN
        	SELECT * FROM saque;
      	ELSE
        	SELECT * FROM saque where id = var_id;
		END IF;
END &&

/*-------------------------------------------------------------------------------------------*/

DELIMITER && 
	CREATE PROCEDURE listar_deposito(IN var_id INT) 
	BEGIN
		IF(var_id IS NULL) THEN
        	SELECT * FROM deposito;
      	ELSE
        	SELECT * FROM deposito where id = var_id;
		END IF;
END &&

/*----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------*/

DELIMITER $$

CREATE TRIGGER T_saque_insert AFTER INSERT ON saque
FOR EACH ROW
BEGIN
	UPDATE conta SET saldo = saldo - NEW.valor WHERE numero = NEW.nrconta;
END $$

DELIMITER $$

CREATE TRIGGER T_deposito_insert AFTER INSERT ON deposito
FOR EACH ROW
BEGIN
	UPDATE conta SET saldo = saldo + NEW.valor WHERE numero = NEW.nrconta;
END $$

/*-------------------------------------------------------------------------------------------*/

DELIMITER $$

CREATE TRIGGER T_saque_delete AFTER DELETE ON saque
FOR EACH ROW
BEGIN
	UPDATE conta SET saldo = saldo + OLD.valor WHERE numero = OLD.nrconta;
END $$

DELIMITER $$

CREATE TRIGGER T_deposito_delete AFTER DELETE ON deposito
FOR EACH ROW
BEGIN
	UPDATE conta SET saldo = saldo - OLD.valor WHERE numero = OLD.nrconta;
END $$