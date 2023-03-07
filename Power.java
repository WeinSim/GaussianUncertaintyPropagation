package main;

public class Power extends Expression {

    public Power(Expression base, double exponent) {
        super(base, new Constant(exponent));
    }

    @Override
    public double eval() {
        return Math.pow(child1.eval(), child2.eval());
    }

    @Override
    public Expression derivative(Parameter p) {
        Expression derInside = child1.derivative(p);
        Expression powerRule = new Product(child2, new Power(child1, child2.eval() - 1));
        return new Product(powerRule, derInside);
    }

    @Override
    protected String getConcatSymbol() {
        return "^";
    }
}