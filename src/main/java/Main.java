public class Main {
    public static void main(String[] args) throws CustomIOException {

        TestConnection connection = new TestConnection();
        Fileobj config = new Fileobj("TEXT.TXT");

        System.out.println(connection.isReachable(config));

    }
}
