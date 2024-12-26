package src.com.my;

import java.io.BufferReader;
import java.io.BufferedReader;
import java.io.IO;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Lox {
  static boolean hadError = false;
  public static void main(String[] args) throws IOException {
    if (args.length>1){
    	System.out.println("Usage:jlox [script]");
    	System.exit(64);
      } else if(args.length == 1) {
        runfile(args[0]);
          } else { runPrompt();
            }
          }
  private static void runfile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));
    if (hadError) System.exit(65);
  }
  private static void runPrompt() throws IOException  {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);
    for(;;){
      System.out.print("> ");
      String line = reader.readLine();
      if(line == null ) break;
      run(line);
      hadError =false;
    }
  }
  private static void run(String source){
    Scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();
    for(Token token : tokens){
      System.out.println(token);
    }
  }
  static void error (int line , String  message) {
    report(line, "", message);
  }
  private static void report(int line, String where, String message){
    System.out.println(
    "[line: " +line + "] Error"+ where+ ": "+ message  
    );
    hadError = true;
  } // still needs to work on better error code reports (more technical but ill do so later)

}