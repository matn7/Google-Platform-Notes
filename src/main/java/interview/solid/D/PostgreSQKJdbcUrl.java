package interview.solid.D;

public class PostgreSQKJdbcUrl implements JdbcUrl {

    private final String dbName;

    public PostgreSQKJdbcUrl(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public String get() {
        return "jdbc:// ... " + this.dbName;
    }
}
