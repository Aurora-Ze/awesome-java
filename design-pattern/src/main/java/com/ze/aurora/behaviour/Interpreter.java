package com.ze.aurora.behaviour;

import java.beans.Expression;
import java.util.StringTokenizer;

/**
 * @author Aurora
 * @date 2021/11/19 20:49
 */
public class Interpreter {
    abstract class AbstractExpression {
        abstract boolean interpret(String exp);
    }

    class AndExpression extends AbstractExpression{
        AbstractExpression expression1;
        AbstractExpression expression2;

        AndExpression(AbstractExpression expression1, AbstractExpression expression2) {
            this.expression1 = expression1;
            this.expression2 = expression2;
        }

        @Override
        boolean interpret(String exp) {
            return expression1.interpret(exp) && expression2.interpret(exp);
        }
    }

    class OrExpression extends AbstractExpression{
        AbstractExpression expression1;
        AbstractExpression expression2;

        OrExpression(AbstractExpression expression1, AbstractExpression expression2) {
            this.expression1 = expression1;
            this.expression2 = expression2;
        }

        @Override
        boolean interpret(String exp) {
            return expression1.interpret(exp) || expression2.interpret(exp);
        }
    }

    class TerminalExpression extends AbstractExpression{
        String letter;

        TerminalExpression(String letter) {
            this.letter = letter;
        }

        @Override
        boolean interpret(String exp) {
            StringTokenizer tokenizer = new StringTokenizer(exp);
            while(tokenizer.hasMoreTokens()) {
                String next = tokenizer.nextToken();
                if (letter.equals(next)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void test() {
        AbstractExpression A = new TerminalExpression("A");
        AbstractExpression B = new TerminalExpression("B");
        AbstractExpression C = new TerminalExpression("C");
        AbstractExpression D = new TerminalExpression("D");

        // 构造表达式 D and (A or (B or C))
        AbstractExpression BC = new OrExpression(B, C);
        AbstractExpression exp = new OrExpression(A, BC);
        AbstractExpression exp1 = new AndExpression(D, exp);

        System.out.println(exp1.interpret("D A"));;
        System.out.println(exp1.interpret("A B"));

    }

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        interpreter.test();
    }
}
