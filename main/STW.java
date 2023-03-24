package main;

public class STW {
    
    // public static void main(String[] args) {
    //     tv1V();
    //     tv1Vl();
    //     tv1Gamma();
    //     
    //     tv3();
    // }
    
    public static void tv3() {
        Parameter v = new Parameter(352.2, "v", 2.1), a = new Parameter(326.4091, "a", 0.0027);
        Expression l = new Product(v, new Power(new Product(new Constant(2), a), -1));
        var params = new Parameter[] { v, a };
        System.out.println("l = " + l.eval());
        System.out.println("delta l = " + l.gaussUncertainty(params));
    }
    
    public static void tv1V() {
        Parameter f = new Parameter(2525, "f", 15), l = new Parameter(0.1399, "l", 0.0001);
        Expression v = new Product(f, l);
        var params = new Parameter[] { f, l };
        System.out.println("v = " + v.eval());
        System.out.println("delta v = " + v.gaussUncertainty(params));
    }
    
    public static void tv1Vl() {
        Parameter v0 = new Parameter(331, "v0"), t0 = new Parameter(273.15, "t0"),
                tl = new Parameter(19.1 + 273.15, "tl", 0.1);
        Expression tRatio = new Product(new Power(tl, 0.5), new Power(t0, -0.5));
        Expression vl = new Product(v0, tRatio);
        var params = new Parameter[] { t0, tl };
        System.out.println("vl = " + vl.eval());
        System.out.println("delta vl = " + vl.gaussUncertainty(params));
    }
    
    public static void tv1Gamma() {
        Parameter v = new Parameter(352.2, "v", 2.1), t = new Parameter(19.1 + 273.15, "T", 0.1),
                m = new Parameter(0.02896, "M"), r = new Parameter(8.314, "R");
        Expression num = new Product(new Power(v, 2), m);
        Expression den = new Product(r, t);
        Expression gamma = new Product(num, new Power(den, -1));
        var params = new Parameter[] { v, t };
        System.out.println("gamma = " + gamma.eval());
        System.out.println("delta gamma = " + gamma.gaussUncertainty(params));
    }
}
