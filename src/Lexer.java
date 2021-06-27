import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    public static LinkedList<Token> lex(String input) {
        LinkedList<Token> tokens = new LinkedList<Token>();
        boolean flag;
        Lexem lexem = null;
        String substr = "";
        int index = 0;
        while (input.length() > index) {
            flag = false;
            substr += input.charAt(index);
            for (Lexem l : Lexem.values()) {
                Pattern pattern = l.getPattern();
                Matcher matcher = pattern.matcher(substr);
                if (matcher.matches()) {
                    lexem = l;
                    flag = true;
                    break;
                }
            }
            if (input.charAt(index) == ' ' || input.charAt(index) == '\n' || input.charAt(index) == '\t') {
                if (lexem != null) {
                    tokens.add(new Token(lexem, substr.substring(0, substr.length()-1)));
                    lexem = null;
                    substr = "";
                }
            }
            if (lexem != null && !flag) {
                substr = substr.substring(0, substr.length()-1);
                index--;
                tokens.add(new Token(lexem, substr));
                lexem = null;
                substr = "";
            }
            index++;
        }
        if (lexem != null)
            tokens.add(new Token(lexem, substr));
        return tokens;
    }
}

