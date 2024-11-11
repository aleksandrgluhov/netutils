#!/usr/bin/env bash

build_version="1.0.0"

docker build -t agluhov/netutils:latest -f Dockerfile-alpine .
docker tag agluhov/netutils:latest agluhov/netutils:${build_version}

docker build -t agluhov/netutils:latest-java -f Dockerfile-alpine-java .
docker tag agluhov/netutils:latest-java agluhov/netutils:${build_version}-java

docker build -t agluhov/netutils:latest-python -f Dockerfile-alpine-python .
docker tag agluhov/netutils:latest-python agluhov/netutils:${build_version}-python
