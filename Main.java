public class Main {
    public static void main(String[] args) {
        CreateTextFile ctf = new CreateTextFile();
        ctf.openFile();
        ctf.addRecords();
        ctf.closeFile();
    }
}