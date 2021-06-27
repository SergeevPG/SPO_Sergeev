import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        LinkedList<Token> tokens = lexer.lex("a = 10; b = 7; c = 4; d = 8; l new List; l.add(15-29); l.add(30); s new Set; s.add(80-6); s.add(5); c = l.get(1); if(c > 25){d = d + c;}  while (a > b) {a = a - c;}");
        Poliz.setTokens(tokens);
        System.out.println("Tokens:\n------------------------------");
        for (int i = 0; i < tokens.size(); i++)
        {
            int count=i+1;
            System.out.println("|\t№" + count + "\t\t|\t[" + tokens.get(i) + "]");
        }
        System.out.println("\n\n------------------------------");
        Parser parser = new Parser();
        try {
            parser.createAST(new ArrayList<>(tokens));
            parser.CheckTokens();
            parser.print();
        }catch (Exception ex)
        { System.err.println(ex);
            System.exit(1);
        }

        int count2 = 1;
        System.out.println("\n\n------------------------------");
        for (Token token : Poliz.poliz) {
            System.out.println("|\t№"+ count2 + "\t\t|\t\t[" + token + "]");
            count2++;
        }
        System.out.println("------------------------------\n\n\nVariable table:\n------------------------------");
        CalcPoliz.calculate(Poliz.poliz);
        System.out.println("------------------------------");
    }
}