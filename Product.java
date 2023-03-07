package main;

public class Product extends Expression {

    public Product(Expression child1, Expression child2) {
        super(child1, child2);
    }

    @Override
    public double eval() {
        return child1.eval() * child2.eval();
    }

    @Override
    public Expression derivative(Parameter p) {
        return new Sum(new Product(child1, child2.derivative(p)),
                new Product(child1.derivative(p), child2));
    }

    @Override
    protected String getConcatSymbol() {
        return "*";
    }

    @Override
    protected boolean isAssociative() {
        return true;
    }
}