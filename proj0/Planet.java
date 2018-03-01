public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67e-11;


    // class中的method只是做定义. 我们想这个class需要有哪些method.
    // 具体的实现利用是在object有了之后再用.

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double xDiff = xxPos - p.xxPos;
        double yDiff = yyPos - p.yyPos;
        double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
        return distance;
    }

    public double calcForceExertedBy(Planet p) {
        double multiply = p.G * p.mass * mass;
        double distance = calcDistance(p);
        return multiply / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dx = p.xxPos - xxPos;
        return force * dx / r;
    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dy = p.yyPos - yyPos;
        return force * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double res = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                double forcex = calcForceExertedByX(p);
                res += forcex;
            }
        }
        return res;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double res = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                double forcey = calcForceExertedByY(p);
                res += forcey;
            }
        }
        return res;
    }

    public void update(double dt, double fX, double fY) {
//        1 calculat acceration use x and y
//        2 new v by using acceleration and current veocity
//        3 calculate new position
        double axx = fX / mass;
        double ayy = fY / mass;

        xxVel += dt * axx;
        yyVel += dt * ayy;

        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
}
