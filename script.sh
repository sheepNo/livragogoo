#! /usr/bin/env bash

cd bookstore/2-structured-data || exit
mvn -Plocal clean jetty:run-exploded -DprojectID=livragogoo
echo ""
echo "-------------------------------------------------------"
echo ""

# Pour visualiser l'app:
#   firefox http://localhost:8080 &

# Pour déployer sur le cloud:
#   mvn appengine:deploy -DprojectID=livragogoo

# Pour visualiser l'app:
#   firefox https://livragogoo.appspot-preview.com &
