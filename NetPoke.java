import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetPoke {
    public static void usage() {
        System.out.println("Simple Java DNS and SSL tester");
        System.out.println("Usage: " + NetPoke.class.getName() + " <mode> <hostname>");
        System.out.println("  - mode: dns|ssl");
        System.out.println("  - hostname: for ssl mode you can specify host:port, default port is 443");
        System.exit(1);
    }
    public static void testDNS(String hostname) {
        InetAddress address = null;
        try {
            address = InetAddress.getByName(hostname);
            System.out.println("host address: " + address.getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
    public static void testSSL(String hostname, Integer port) {
        try {
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(hostname, port);

            InputStream in = sslsocket.getInputStream();
            OutputStream out = sslsocket.getOutputStream();

            out.write(1);

            while ( in .available() > 0) {
                System.out.print( in .read());
            }
            System.out.println("Connection established");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public static void main(String[] args) {
        if (args.length <= 1) {
            usage();
        }
        switch (args[0]) {
            case "dns":
                testDNS(args[1]);
                break;

            case "ssl":
                String hostPort[] = args[1].split(":");
                if (hostPort.length > 1) {
                    testSSL(hostPort[0], Integer.parseInt(hostPort[1]));
                } else {
                    testSSL(args[1], 443);
                }
                break;

            default:
                usage();
                break;
        }
    }
}