import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class CreateTextFile {
    private Formatter output;

    public void openFile() {
        try {
            output = new Formatter("clients.txt");
        }
        catch (SecurityException securityExcepion) {
            System.err.println("Acesso negado");
            System.exit(1);
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Erro.");
            System.exit(1);
        }
    }

    public void addRecords() {
        AccountRecord record = new AccountRecord();

        double number = 0;

        Scanner input = new Scanner(System.in);

        System.out.printf( "%s\n%s\n%s\n%s\n\n",

                "To terminate input, type the end-of-file indicator",

                "when you are prompted to enter input.",

                "On UNIX/Linux/Mac OS X type <ctrl> d then press Enter",

                "On Windows type <ctrl> z then press Enter");

        System.out.printf("%s\n%s",

                "Enter account number (> 0), first name, last name and balance.", "?");

        while (number == 0) {
            try {
                record.setAccount(Integer.parseInt(JOptionPane.showInputDialog("Número:")));
                record.setFirstName(JOptionPane.showInputDialog("Nome:"));
                record.setLastName(JOptionPane.showInputDialog("Sobrenome:"));
                record.setBalance(Double.parseDouble(JOptionPane.showInputDialog("Saldo:")));

                if(record.getAccount() > 0) {
                    output.format("%d %s %s %.2f\n", record.getAccount(), record.getFirstName(), record.getLastName(), record.getBalance());
                }
                else {
                    System.out.println("Número precisa ser > 0");
                }
            }
            catch(FormatterClosedException formatterClosedException) {
                System.err.println("Erro");
                return;
            }
            catch(NoSuchElementException noSuchElementException) {
                System.err.println("Inválido. Tente novamente");
                input.nextLine();
            }
            number = Double.parseDouble(JOptionPane.showInputDialog("0 para continuar, qualquer outro número finaliza"));
            if(number == 0) {
                System.out.printf("%s %s\n%s", "Enter account number(>0),", "first name", "last name and balance.", "? ");
            }
        }
        JOptionPane.showMessageDialog(null, "Arquivo finalizado");
    }

    public void closeFile() {
        if(output != null)
            output.close();
    }
}