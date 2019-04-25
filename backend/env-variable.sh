export MYSQL_DATABASE=moviedb
export MYSQL_USER=root
export MYSQL_PASSWORD=root
export MYSQL_DIALECT=org.hibernate.dialect.MySQL5InnoDBDialect
if [[ -z "${MYSQL_CI_URL}" ]]; then
export MYSQL_CI_URL=jdbc:mysql://localhost:3306/moviedb
fi
