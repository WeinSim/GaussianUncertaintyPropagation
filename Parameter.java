package main;

public class Parameter extends Expression {

    protected final double value;
    protected final String name;
    private double uncertainty;

    public Parameter(double value, String name) {
        this(value, name, 0);
    }

    public Parameter(double value, String name, double uncertainty) {
        super(null, null);
        this.value = value;
        this.name = name;
        this.uncertainty = uncertainty;
    }

    @Override
    public double eval() {
        return value;
    }

    @Override
    public Expression derivative(Parameter p) {
        return this == p ? new Constant(1) : new Constant(0);
    }

    public String getName() {
        return name;
    }

    @Override
    protected String getConcatSymbol() {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    public double getUncertainty() {
        return uncertainty;
    }

    public void setUncertainty(double uncertainty) {
        this.uncertainty = uncertainty;
    }
}