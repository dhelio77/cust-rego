### Docker Postgres

Note that this is not docker-machine, so maybe networking works differently.

docker run --rm -it \
-p 5432:5432 \
-e POSTGRES_USER=user-ms \
-e POSTGRES_PASSWORD=12345 \
postgres
I tried establishing connections to it from the Docker host…

Localhost worked:

psql --user user-ms -h localhost
And the local IP address worked also:

psql --user user-ms -h "$(ifconfig \
| grep -E "([0-9]{1,3}\.){3}[0-9]{1,3}" \
| grep -v 127.0.0.1 \
| awk '{ print $2 }' \
| cut -f2 -d: \
| head -n1)"