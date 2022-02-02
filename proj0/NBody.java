public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        Planet[] res = new Planet[num];
        double radius = in.readDouble();
        for (int i = 0; i < num; i++) {
            Planet p = new Planet(
                    in.readDouble(),
                    in.readDouble(),
                    in.readDouble(),
                    in.readDouble(),
                    in.readDouble(),
                    in.readString());
            res[i] = p;
        }
        return res;
    }

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        double time = 0.0;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);
        // StdDraw.setCanvasSize(800, 800);
        StdDraw.setScale(-radius, radius);
        String background = "images/starfield.jpg";
        StdDraw.picture(0, 0, background);
        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.show();
        while (time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < xForces.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < xForces.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, background);
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time = time + dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

        // StdDraw.pause(1000);
        // drawBackground();

    }

}
