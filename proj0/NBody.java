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

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);
        StdDraw.setScale(radius / (-2), radius / 2);
        String background = "images/starfield.jpg";
        StdDraw.picture(0, 0, background);
        for (Planet p : planets) {
            p.draw();

        }
        StdDraw.show();
        StdDraw.pause(1000);
        // drawBackground();

    }

}
