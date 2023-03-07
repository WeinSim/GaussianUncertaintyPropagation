package main;

public abstract class Expression {

    protected final Expression child1, child2;

    public Expression(Expression child1, Expression child2) {
        this.child1 = simplify(child1);
        this.child2 = simplify(child2);
    }

    public abstract double eval();

    public abstract Expression derivative(Parameter e);

    public Expression getChild1() {
        return child1;
    }

    public Expression getChild2() {
        return child2;
    }

    public static Expression simplify(Expression e) {
        if (e == null)
            return null;
        if (e instanceof Power) {
            Power p = (Power) e;
            if (p.getChild2() instanceof Constant) {
                Constant c2 = (Constant) p.getChild2();
                if (c2.isOne())
                    e = p.getChild1();
                if (c2.isZero())
                    e = new Constant(0);
            }
        }
        if (e instanceof Sum) {
            Sum a = (Sum) e;
            if (a.getChild1() instanceof Constant) {
                Constant c1 = (Constant) a.getChild1();
                if (c1.isZero())
                    e = a.getChild2();
            }
            if (a.getChild2() instanceof Constant) {
                Constant c2 = (Constant) a.getChild2();
                if (c2.isZero())
                    e = a.getChild1();
            }
        }
        if (e instanceof Product) {
            Product m = (Product) e;
            if (m.getChild1() instanceof Constant) {
                Constant c1 = (Constant) m.getChild1();
                if (c1.isZero())
                    e = new Constant(0);
                if (c1.isOne())
                    e = m.getChild2();
            }
            if (m.getChild2() instanceof Constant) {
                Constant c2 = (Constant) m.getChild2();
                if (c2.isZero())
                    e = new Constant(0);
                if (c2.isOne())
                    e = m.getChild1();
            }
        }
        return e;
    }

    protected abstract String getConcatSymbol();

    protected boolean isAssociative() {
        return false;
    }

    public String toString() {
        String[] parentheses = new String[4];
        var children = new Expression[] { child1, child2 };
        for (int i = 0; i < children.length; i++) {
            var child = children[i];
            boolean associative = isAssociative() && child.getClass() == getClass();
            boolean constParam = child instanceof Constant || child instanceof Parameter;
            boolean useParentheses = !(associative || constParam);
            parentheses[2 * i] = useParentheses ? "(" : "";
            parentheses[2 * i + 1] = useParentheses ? ")" : "";
        }
        String str1 = parentheses[0] + child1.toString() + parentheses[1],
                str2 = parentheses[2] + child2.toString() + parentheses[3];
        return str1 + " " + getConcatSymbol() + " " + str2;
    }

    public static double gaussUncertainty(Expression e, Parameter[] params) {
        double sum = 0;
        for (int i = 0; i < params.length; i++)
            sum += Math.pow(e.derivative(params[i]).eval() * params[i].getUncertainty(), 2);
        return Math.sqrt(sum);
    }
}