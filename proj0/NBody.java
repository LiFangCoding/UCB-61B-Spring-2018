import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);

        int planetNum = in.readInt();
        double radius = in.readDouble();

        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);

        int planetNum = in.readInt();
        double radius = in.readDouble();
        Planet[] res = new Planet[planetNum];

        for(int i = 0; i < planetNum; i++) {
            double x_pos = in.readDouble();
            double y_pos = in.readDouble();
            double x_vel = in.readDouble();
            double y_vel = in.readDouble();
            double mass = in.readDouble();
            String name = in.readString();
            Planet planet = new Planet(x_pos, y_pos, x_vel, y_vel, mass, name);
            res[i] = planet;
        }
        return res;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];

        Planet[] planets = NBody.readPlanets(filename);
        double radius = NBody.readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        StdDraw.picture(0,  0, "images/starfield.jpg");

        for (Planet p : planets) {
            p.draw();
        }

        // creating an animation
        StdDraw.enableDoubleBuffering();

        double t = 0;

        while(t < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                Planet p = planets[i];
                xForces[i] = p.calcNetForceExertedByX(planets);
                yForces[i] = p.calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
