package com.marvin_elsen.eva.jsf.calculator;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class Calculator
{
    private long operandA;
    private long operandB;
    private long result;
    private String operator;


    public long getOperandA()
    {
        return operandA;
    }


    public void setOperandA(long operandA)
    {
        this.operandA = operandA;
    }


    public long getOperandB()
    {
        return operandB;
    }


    public void setOperandB(long operandB)
    {
        this.operandB = operandB;
    }


    public String getOperator()
    {
        return operator;
    }


    public void setOperator(String operator)
    {
        this.operator = operator;
    }


    public long getResult()
    {
        return result;
    }


    public String compute()
    {
        switch (operator)
        {
            case "+":
                result = operandA + operandB;
                break;

            case "-":
                result = operandA - operandB;
                break;

            case "*":
                result = operandA * operandB;
                break;

            case "/":
                result = operandA / operandB;
                break;

            default:
                break;
        }

        return "calculator-response";
    }
}
