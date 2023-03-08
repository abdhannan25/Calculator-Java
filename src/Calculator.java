import java.util.*;

public class Calculator {
    
    public double evaluate(String input){
        List<String> tokens = Arrays.asList(input.split("(?<=[-+*/])|(?=[-+*/])"));
        Stack<Double> operands = new Stack<>();
        Stack<String> operators = new Stack<>();

        for(String token : tokens ){
            if(token.matches("\\d+")){
                operands.push(Double.parseDouble(token));
            }
            else if(token.matches("[-+*/]")){
                while(!operators.isEmpty() && hasHigherPrecedence(token, operators.peek())){
                    applyoperator(operands, operators.pop());
                }
                operators.push(token);
            }
        }
        while(!operators.isEmpty()){
            applyoperator(operands, operators.pop());
        }
        return operands.pop();
    }

    public boolean hasHigherPrecedence(String op1, String op2){
        int precedence1 = getPrecedence(op1);
        int precedence2 = getPrecedence(op2);

        return precedence1 > precedence2;
        //return (op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-"));
    }

    public int getPrecedence(String op){
        if(op.equals("+") || op.equals("-"))
            return 2;
        else if(op.equals("*") || op.equals("/"))
            return 1;
        else
            throw new IllegalArgumentException("Unknown operator: " + op);
    }

    
    public void applyoperator(Stack<Double> operands, String operator){
        double operand2 = operands.pop();
        double operand1 = operands.pop();
        Operator op;
        switch(operator){
            case "+":{
                op = new Add();
                operands.push(op.getResult(operand1, operand2));
                break;
            }
            case "-":{
                op = new Subtract();
                operands.push(op.getResult(operand1, operand2));
                break;
            }
            case "*":{
                op = new Multiply();
                operands.push(op.getResult(operand1, operand2));
                break;
            }
            case "/":{
                op = new Divide();
                operands.push(op.getResult(operand1, operand2));
                break;
            }
            default:{
                throw new IllegalArgumentException("Unkown Operator: " + operator);
            }
            
        }
    }
}
