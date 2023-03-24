package main;

public class VID {
	
	public static void main(String[] args) {
		tv1();
		tv3();
	}

	public static void tv1() {
		gBerechnen(0.74, 0.01, 0.417, 0.004);
		gBerechnen(0.76, 0.01, 0.409, 0.0030);
		gBerechnen(0.39, 0.01, 0.298, 0.0025);
	}

	public static void gBerechnen(double h0, double dh, double t0, double dt) {
		Parameter h = new Parameter(h0, "h", dh), t = new Parameter(t0, "t", dt);
		Expression num = new Product(new Constant(2), h);
		Expression g = new Product(num, new Power(t, -2));
		var params = new Parameter[] { h, t };
		System.out.println("g = " + g.eval());
		System.out.println("delta g = " + g.gaussUncertainty(params));
	}

	public static void tv3() {
		Parameter f0 = new Parameter(14, "f0"), f1 = new Parameter(35, "f1"),
			fps = new Parameter(240, "fps"), xfactor = new Parameter(0.75, "xfactor"),
			x0 = new Parameter(-0.444, "x0", 0.005), x1 = new Parameter(-0.136, "x1", 0.005),
			r0 = new Parameter(0, "r0", 0.3), r1 = new Parameter(3, "r1", 0.3),
			lGes = new Parameter(0.260, "ls", 0.003), lSt = new Parameter(0.043, "lS", 0.003),
			m10 = new Parameter(0.008, "m10", 0.001);
		var params = new Parameter[] { x0, x1, r0, r1, lGes, lSt, m10 };
		Expression minus1 = new Constant(-1);
		Expression pi2 = new Constant(2 * Math.PI);
		Expression half = new Constant(0.5);
		Expression deltaT = new Product(new Sum(f1, new Product(f0, minus1)), new Power(fps, -1));
		Expression dtInv = new Power(deltaT, -1);
		Expression dx = new Sum(x1, new Product(x0, minus1));
		Expression v = new Product(dx, dtInv);
		Expression dAng = new Product(pi2, new Sum(r1, new Product(r0, minus1)));
		Expression omega = new Product(dAng, dtInv);	
		Expression m1 = new Product(new Constant(1.0 / 10), m10);
		Expression m = new Product(new Product(m1, lSt), new Power(lGes, -1));
		Expression moi = new Product(new Constant(1.0 / 12), new Product(m, new Power(lSt, 2)));
		Expression transE = new Product(half, new Product(m, new Power(v, 2)));
		Expression rotE = new Product(half, new Product(moi, new Power(omega, 2)));
		Expression energy = new Sum(transE, rotE);
		// System.out.println(energy);
		System.out.println("translational kinetic energy = " + transE.eval());
		System.out.println("rotational kinetic energy = " + rotE.eval());
		System.out.println("total kinetic energy = " + energy.eval());
		System.out.println("total kinetic energy = " + energy.gaussUncertainty(params()));
	}
}
