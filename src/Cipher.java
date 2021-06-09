import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cipher {

  public static final String UPPER_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static final String LOWER_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
  public static final String DIGITS = "0123456789";

  public static void main(String[] args) throws NumberFormatException {
    String command = args[0];
    int key = Integer.parseInt(args[1]);
    String msg = args[2];

    File file = new File(msg);
    if (file.isFile()) {
      msg = readFileContent(file);
    }

    if (args.length < 3) {
      System.out.println("Not enough commands");
    } else if (args.length > 3) {
      System.out.println("Way too many commands");
    }

    if (command.equals("encrypt")) {
      String cipher = encrypt(key, msg);
      System.out.println(cipher);
    } else if (command.equals("decrypt")) {
      String plaintext = decrypt(key, msg);
      System.out.println(plaintext);
    } else {
      System.out.println("This is not a command idiot.");
      System.exit(0);
    }
  }

  private static String decrypt(int key, String msg) {
    String plaintext = "";

    for (int i = 0; i < msg.length(); i++) {
      char c = msg.charAt(i);

      String alphabet = null;

      if (UPPER_ALPHABET.indexOf(c) > -1) {
        alphabet = UPPER_ALPHABET;
      } else if (LOWER_ALPHABET.indexOf(c) > -1) {
        alphabet = LOWER_ALPHABET;
      } else if (DIGITS.indexOf(c) > -1) {
        alphabet = DIGITS;
      }

      if (alphabet != null) {
        int length = alphabet.length();

        c -= key;

        while (c > alphabet.charAt(length - 1)) {
          c -= length;
        }
      }

      plaintext += c;
    }

    return plaintext;
  }

  private static String encrypt(int key, String msg) {
    String cipherText = "";

    for (int i = 0; i < msg.length(); i++) {
      char c = msg.charAt(i);

      String alphabet = null;

      if (UPPER_ALPHABET.indexOf(c) > -1) {
        alphabet = UPPER_ALPHABET;
      } else if (LOWER_ALPHABET.indexOf(c) > -1) {
        alphabet = LOWER_ALPHABET;
      } else if (DIGITS.indexOf(c) > -1) {
        alphabet = DIGITS;
      }

      if (alphabet != null) {
        int length = alphabet.length();

        c += key;

        while (c > alphabet.charAt(length - 1)) {
          c -= length;
        }
      }

      cipherText += c;
    }

    return cipherText;
  }

  private static String readFileContent(File file) {
    String content = null;
    
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        content = scanner.nextLine();
      }
      scanner.close();

      return content;
    } catch (FileNotFoundException e) {
      System.out.println("Can not find the file called " + file.getName());
    }

    return content;
  }
}
