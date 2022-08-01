FROM postgres:14.2-alpine
COPY multiDB.sh /docker-entrypoint-initdb.d/
RUN chmod +x /docker-entrypoint-initdb.d/multiDB.sh
