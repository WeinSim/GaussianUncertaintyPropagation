package main;

public class GFP {
    
    public static void main(String[] args) {
        tv3();
    }
    
    public static void tv3() {
        System.out.println("1. Messung:");
        direkteBerechnung(0.22, 0.005, 0.94, 0.01);
        System.out.println("2. Messung:");
        direkteBerechnung(0.60, 0.005, 1.54, 0.01);
        System.out.println("3. Messung:");
        direkteBerechnung(1.00, 0.01, 2.00, 0.01);
    }
    
    public static void indirekteBerechnung() {
        Parameter b = new Parameter(0.04, "b", 0.001), a = new Parameter(0.1863, "a", 0.0421);
        Expression piSq4 = new Product(new Power(new Constant(Math.PI), 2), new Constant(4));
        Expression g = new Product(new Product(piSq4, b), new Power(a, -1));
        var params = new Parameter[] { b, a };
        System.out.println("g = " + g.eval());
        System.out.println("delta g = " + g.gaussUncertainty(params));
    }
    
    public static void tv1() {
        System.out.println("delta t = (tmax - tmin) / 2:");
        direkteBerechnung(0.72, 0.01, 1.664, 0.18);
        System.out.println("delta t = Standardabweichung aller Werte:");
        direkteBerechnung(0.72, 0.01, 1.664, 0.0952);
    }
    
    public static void direkteBerechnung(double len, double dlen, double time, double dtime) {
        Parameter l = new Parameter(len, "l", dlen), t = new Parameter(time, "t", dtime);
        Expression piSq4 = new Product(new Power(new Constant(Math.PI), 2), new Constant(4));
        Expression g = new Product(new Product(piSq4, l), new Power(t, -2));
        var params = new Parameter[] { l, t };
        System.out.println("g = " + g.eval());
        System.out.println("delta g = " + g.gaussUncertainty(params));
    }
}
