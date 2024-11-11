package interview.solid.I;

public class WwwPingConnection implements HttpConnection {

    private final String www;

    public WwwPingConnection(String www) {
        this.www = www;
    }

    @Override
    public void http() {
        System.out.println("Setup a http connection to " + www);
    }

    @Override
    public void connect() {
        System.out.println("Connect to " + www);
    }

}
