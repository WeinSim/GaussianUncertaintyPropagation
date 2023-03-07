package main;

public class Main {

    public static void main(String[] args) {
        // proportionalitaetskonstanteK();
        // traegheitsradiusR();
        // traegheitsMomenteSchwingung();
        // traegheitsMomenteGroesseQuader();
    }

    private static void traegheitsMomenteGroesseQuader() {
        Parameter m = new Parameter(3.0104, "m", 0.00013), lx = new Parameter(0.051, "Lx", 0.0005),
                ly = new Parameter(0.073, "Ly", 0.0005), lz = new Parameter(0.102, "Lz", 0.0005);
        Expression jx = new Product(new Constant(1.0 / 12),
                new Product(m, new Sum(new Power(ly, 2), new Power(lz, 2))));
        Expression jy = new Product(new Constant(1.0 / 12),
                new Product(m, new Sum(new Power(lx, 2), new Power(lz, 2))));
        Expression jz = new Product(new Constant(1.0 / 12),
                new Product(m, new Sum(new Power(lx, 2), new Power(ly, 2))));
        var params = new Parameter[] { m, lx, ly, lz };
        System.out.println("jx = " + jx.eval());
        System.out.println("delta jx = " + Expression.gaussUncertainty(jx, params));
        System.out.println("delta jy = " + Expression.gaussUncertainty(jy, params));
        System.out.println("delta jz = " + Expression.gaussUncertainty(jz, params));
    }

    public static void traegheitsMomenteSchwingung() {
        Parameter k = new Parameter(3.87e-5, "k", 0.11e-9), tx = new Parameter(10.019, "Tx", 0.2),
                ty = new Parameter(9.119, "Ty", 0.2), tz = new Parameter(7.087, "Tz", 0.2),
                td = new Parameter(8.169, "Td", 0.2);
        Expression jx = new Product(k, new Power(tx, 2)), jy = new Product(k, new Power(ty, 2)),
                jz = new Product(k, new Power(tz, 2)), jd = new Product(k, new Power(td, 2));
        System.out.println("jx = " + jx.eval());
        System.out.println("delta jx = " + Expression.gaussUncertainty(jx, new Parameter[] { k, tx }));
        System.out.println("delta jy = " + Expression.gaussUncertainty(jy, new Parameter[] { k, ty }));
        System.out.println("delta jz = " + Expression.gaussUncertainty(jz, new Parameter[] { k, tz }));
        System.out.println("delta jd = " + Expression.gaussUncertainty(jd, new Parameter[] { k, td }));
    }

    public static void traegheitsradiusR() {
        Parameter r1 = new Parameter(0.0065, "r1", 0.001), r2 = new Parameter(0.0035, "r2", 0.0015),
                r3 = new Parameter(0.0065, "r3", 0.002), r4 = new Parameter(0.0625, "r4", 0.0025),
                r5 = new Parameter(0.1415, "r5", 0.0025), l1 = new Parameter(0.085, "l1", 0.004),
                l2 = new Parameter(0.063, "l2", 0.0045), l3 = new Parameter(0.006, "l3", 0.0005),
                l4 = new Parameter(0.0045, "l4", 0.0005);
        Expression num1 = new Product(l1, new Power(r1, 4));
        Expression num2 = new Product(l2, new Sum(new Power(r2, 4), new Product(new Constant(-1), new Power(r1, 4))));
        Expression num3 = new Product(l3, new Sum(new Power(r3, 4), new Product(new Constant(-1), new Power(r2, 4))));
        Expression num4 = new Product(l3, new Sum(new Power(r5, 4), new Product(new Constant(-1), new Power(r4, 4))));
        Expression num5 = new Product(l4, new Sum(new Power(r4, 4), new Product(new Constant(-1), new Power(r3, 4))));
        Expression num = new Sum(new Sum(new Sum(new Sum(num1, num2), num3), num4), num5);
        Expression den1 = new Product(l1, new Power(r1, 2));
        Expression den2 = new Product(l2, new Sum(new Power(r2, 2), new Product(new Constant(-1), new Power(r1, 2))));
        Expression den3 = new Product(l3, new Sum(new Power(r3, 2), new Product(new Constant(-1), new Power(r2, 2))));
        Expression den4 = new Product(l3, new Sum(new Power(r5, 2), new Product(new Constant(-1), new Power(r4, 2))));
        Expression den5 = new Product(l4, new Sum(new Power(r4, 2), new Product(new Constant(-1), new Power(r3, 2))));
        Expression den = new Sum(new Sum(new Sum(new Sum(den1, den2), den3), den4), den5);
        Expression frac = new Product(num, new Power(den, -1));
        Expression rj = new Power(new Product(new Constant(0.5), frac), 0.5);
        var params = new Parameter[] { r1, r2, r3, r4, r5, l1, l2, l3, l4 };
        System.out.println("rj = " + rj.eval());
        System.out.println("delta rj = " + Expression.gaussUncertainty(rj, params));
    }

    public static void proportionalitaetskonstanteK() {
        Parameter m = new Parameter(3.0104, "m", 0.00013), r = new Parameter(0.083, "r", 0.0005),
                t = new Parameter(16.4, "T", 0.2);
        Expression num = new Product(m, new Power(r, 2));
        Expression den = new Product(new Constant(2), new Power(t, 2));
        Expression k = new Product(num, new Power(den, -1));
        // System.out.println("k = " + k.eval());
        System.out.println("delta k = " + Expression.gaussUncertainty(k, new Parameter[] { m, r, t }));
    }
}