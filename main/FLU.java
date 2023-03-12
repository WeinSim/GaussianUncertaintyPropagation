package main;

public class FLU {
    
    public static void main(String[] args) {
        viskositaetSpuelmittel();
    }
    
    public static void viskositaetSpuelmittel() {
        Parameter rhoSt = new Parameter(7900, "rhoSt"), rhoSp = new Parameter(1032, "rhoSp"),
                d = new Parameter(0.00096, "r", 0.000005), g = new Parameter(9.81, "g"),
                t = new Parameter(171.42, "t", 0.2), s = new Parameter(0.8, "s", 0.001);
        Expression num1 = new Product(new Product(new Constant(2.0 / 9), g), t);
        Expression rhoDiff = new Sum(rhoSt, new Product(new Constant(-1), rhoSp));
        Expression r2 = new Power(new Product(new Constant(0.5), d), 2);
        Expression num = new Product(new Product(r2, rhoDiff), num1);
        Expression eta = new Product(num, new Power(s, -1));
        var params = new Parameter[] { d, t, s };
        System.out.println(eta);
        System.out.println("eta = " + eta.eval());
        System.out.println("delta eta = " + eta.gaussUncertainty(params));
    }
    
    public static void viskositaetWasser() {
        Parameter dk = new Parameter(0.00080, "dk", 0.00005), rho = new Parameter(998, "rho"),
                g = new Parameter(9.81, "g"), l = new Parameter(0.233, "l", 0.001),
                a = new Parameter(-1.711678e-3, "a", 0.000006e-3), dv = new Parameter(0.0167, "dv", 0.00005);
        Expression num1 = new Power(new Product(new Constant(0.5), dk), 4);
        Expression num = new Product(new Product(num1, rho), g);
        Expression den1 = new Product(new Product(new Constant(-8), a), l);
        Expression den2 = new Power(new Product(new Constant(0.5), dv), 2);
        Expression eta = new Product(num, new Power(new Product(den1, den2), -1));
        var params = new Parameter[] { dv, dk, l, a };
        System.out.println("eta = " + eta.eval());
        System.out.println("delta eta = " + eta.gaussUncertainty(params));
    }
    
    public static void oberflaechenSpannung() {
        Parameter d = new Parameter(0.0621, "d", 0.000005), f0 = new Parameter(0.05, "f0", 0.001),
                fa = new Parameter(0.0748, "fa", 0.001);
        Expression num = new Sum(fa, new Product(f0, new Constant(-1)));
        Expression den = new Product(new Constant(2 * Math.PI), d);
        Expression sigma = new Product(num, new Power(den, -1));
        var params = new Parameter[] { d, f0, fa };
        System.out.println("sigma = " + sigma.eval());
        System.out.println("delta digma = " + sigma.gaussUncertainty(params));
    }
}
