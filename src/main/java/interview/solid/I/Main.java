package interview.solid.I;

public class Main {

    public static void main(String[] args) {
        WwwPingConnection www = new WwwPingConnection("www.yahoo.com");

        www.connect();
    }

}
