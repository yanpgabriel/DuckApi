#!/bin/bash
ls -la
set -eu
function create_user_and_database() {
	local schema=$(echo $1 | tr ',' ' ' | awk  '{print $1}')
	local user=$(echo $1 | tr ',' ' ' | awk  '{print $2}')
	local password=$(echo $1 | tr ',' ' ' | awk  '{print $3}')
	echo "  Criando usuario '$user' e schema '$schema'"
	psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d $POSTGRES_DB <<-EOSQL
	    CREATE USER $user WITH PASSWORD '$password';
	    CREATE DATABASE $schema;
	    CREATE SCHEMA $schema;
	    GRANT ALL PRIVILEGES ON DATABASE $schema TO $user;
	    GRANT ALL PRIVILEGES ON SCHEMA $schema TO $user;
	    REVOKE ALL PRIVILEGES ON SCHEMA public FROM PUBLIC;
EOSQL
#	psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d $database <<-EOSQL
#EOSQL
}
if [ -n "$POSTGRES_MULTIPLE_DATABASES" ]; then
	echo "Iniciando o processo de criar as multiplas databases: $POSTGRES_MULTIPLE_DATABASES"
	for db in $(echo $POSTGRES_MULTIPLE_DATABASES | tr ';' ' '); do
		create_user_and_database $db
	done
	echo "Multiplas databases criadas!"
fi
