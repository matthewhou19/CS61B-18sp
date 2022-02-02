public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
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
        return Math.sqrt(Math.pow(p.xxPos - this.xxPos, 2) + Math.pow(p.yyPos - this.yyPos, 2));
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        double g = 0.0000000000667;
        return (p.mass * this.mass * g / distance) / distance;
    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        return force * (p.xxPos - this.xxPos) / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        return force * (p.yyPos - this.yyPos) / distance;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double sum = 0.0;
        for (Planet p : planets) {
            if (!p.equals(this)) {
                sum = sum + calcForceExertedByX(p);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double sum = 0.0;
        for (Planet p : planets) {
            if (!p.equals(this)) {
                sum = sum + calcForceExertedByY(p);
            }
        }
        return sum;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = this.xxVel + aX * dt;
        this.yyVel = this.yyVel + aY * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }
}
