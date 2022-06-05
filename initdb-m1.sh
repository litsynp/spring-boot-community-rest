#!/bin/bash
# Designed to work on macOS with M1

# Check Docker
if ! [ -x "$(command -v docker)" ]; then
  exit 1
fi

echo 'Initializing MySQL Database on macOS M1'

docker run --platform linux/amd64 \
  -p 3306:3306 \
  --name mysql \
  -e MYSQL_ROOT_PASSWORD=testpass \
  -e MYSQL_DATABASE=testdb \
  -e MYSQL_PASSWORD=testpass \
  -d mysql
