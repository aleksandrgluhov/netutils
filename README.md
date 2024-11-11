# Netutils

Docker image for DNS, SSL tests


![GitHub License](https://img.shields.io/github/license/aleksandrgluhov/netutils?style=flat-square&cacheSeconds=3600)
[![Docker Pulls](https://img.shields.io/docker/pulls/agluhov/netutils?style=flat-square&cacheSeconds=3600)](https://hub.docker.com/r/agluhov/netutils/tags)
[![Docker Image Size (tag)](https://img.shields.io/docker/image-size/agluhov/netutils/latest?arch=amd64&style=flat-square&cacheSeconds=3600&label=latest)](https://hub.docker.com/r/agluhov/netutils/tags)
[![Docker Image Size (tag)](https://img.shields.io/docker/image-size/agluhov/netutils/latest-java?arch=amd64&style=flat-square&cacheSeconds=3600&label=latest-java)](https://hub.docker.com/r/agluhov/netutils/tags)
[![Docker Image Size (tag)](https://img.shields.io/docker/image-size/agluhov/netutils/latest-python?arch=amd64&style=flat-square&cacheSeconds=3600&label=latest-python)](https://hub.docker.com/r/agluhov/netutils/tags)

# About

This repository has 3 versions of netutils image:
- basic
- java flavoured (same as basic, but it has openjdk21 and NetPoke pre-compiled class)
- python flavoured (same as basic, but it has python3 and netpoke.py script)

# Tools

- telnet
- netcat
- socat
- dig
- nslookup
- curl
- openssl

# How to use it

## Basic

```bash
kubectl -n default run -it --rm --image agluhov/netutils:latest netutils /bin/sh
If you don't see a command prompt, try pressing enter.

/ # nslookup google.com
;; Got recursion not available from 10.96.0.10
;; Got recursion not available from 10.96.0.10
;; Got recursion not available from 10.96.0.10
Server:         10.96.0.10
Address:        10.96.0.10#53

Non-authoritative answer:
Name:   google.com
Address: 64.233.162.139
Name:   google.com
Address: 64.233.162.138
Name:   google.com
Address: 64.233.162.113
Name:   google.com
Address: 64.233.162.102
Name:   google.com
Address: 64.233.162.101
Name:   google.com
Address: 64.233.162.100
Name:   google.com
Address: 2a00:1450:4010:c05::8b
Name:   google.com
Address: 2a00:1450:4010:c05::65
Name:   google.com
Address: 2a00:1450:4010:c05::8a
Name:   google.com
Address: 2a00:1450:4010:c05::66

/ # openssl s_client -showcerts -connect google.com:443
Connecting to 64.233.162.138
CONNECTED(00000003)
depth=2 C=US, O=Google Trust Services LLC, CN=GTS Root R1
verify return:1
depth=1 C=US, O=Google Trust Services, CN=WR2
verify return:1
depth=0 CN=*.google.com
verify return:1
```

## Java flavoured

```bash
kubectl -n default run -it --rm --image agluhov/netutils:latest-java netutils /bin/sh
If you don't see a command prompt, try pressing enter.

~ # java NetPoke
Simple Java DNS and SSL tester
Usage: NetPoke <mode> <hostname>
  - mode: dns|ssl
  - hostname: for ssl mode you can specify host:port, default port is 443

~ # java NetPoke ssl google.com
Connection established

~ # java NetPoke dns google.com
host address: 216.58.212.174
```

## Python flavoured
```bash
kubectl -n default run -it --rm --image agluhov/netutils:latest-python netutils /bin/sh
If you don't see a command prompt, try pressing enter.

~ # python3 netpoke.py
Simple Python DNS and SSL tester script
Usage: python netpoke.py <mode> <hostname>
  - mode: dns|ssl
  - hostname: for ssl mode you can specify host:port, default port is 443

~ # python3 netpoke.py dns google.com
DNS resolved: 216.58.212.174

~ # python3 netpoke.py ssl google.com
SSL established
```
