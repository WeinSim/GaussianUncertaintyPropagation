package main;

public class Constant extends Expression {
    
    protected double value;
    
    public Constant(double value) {
        super(null, null);
        this.value = value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
    
    @Override
    public double eval() {
        return value;
    }
    
    @Override
    public Expression derivative(Parameter p) {
        return new Constant(0);
    }
    
    public boolean isOne() {
        return value == 1.0;
    }
    
    public boolean isZero() {
        return value == 0.0 || value == -0.0;
    }

    @Override
    protected String getConcatSymbol() {
        return null;
    }
    
    public String toString() {
        return Double.toString(value);
    }
}
