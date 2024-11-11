#!/usr/bin/env python3


# Imports
import sys
import ssl
import socket


# Procedures and functions
def usage() -> None:
   print("Simple Python DNS and SSL tester script")
   print("Usage: python netpoke.py <mode> <hostname>")
   print("  - mode: dns|ssl")
   print("  - hostname: for ssl mode you can specify host:port, default port is 443")


def test_dns(hostname: str) -> None:
    print(f"DNS resolved: {socket.gethostbyname(hostname)}")


def test_ssl(hostname: str, port: str) -> None:
    context = ssl.create_default_context()
    s = socket.create_connection((hostname, port))
    ss = context.wrap_socket(s, server_hostname=hostname)
    print(f"SSL established. Peer: {ss.getpeercert()}")


# Entrypoint
if __name__ == "__main__":
    if len(sys.argv) <= 1:
        usage()

    else:
        if sys.argv[1] == "dns":
            test_dns(sys.argv[2])

        elif sys.argv[1] == "ssl":
            hp = sys.argv[2].split(":")
            if len(hp) > 1:
                test_ssl(hp[0], hp[1])
            else:
                test_ssl(sys.argv[2], "443")
        else:
            usage()
            sys.exit(1)
