 public class Body{

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	//First Constructor with six parameters.
	public Body(double xP, double yP, double xV, double yV, 
				double m, String img){

		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}
	//Second Constructor with Body as the parameter.
	public Body(Body b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;

	}
	//Calculate the Distance between the two bodies
	public double calcDistance(Body b){
		double dxx = this.xxPos - b.xxPos;
		double dyy = this.yyPos - b.yyPos;
		double distance = Math.sqrt(dxx*dxx + dyy*dyy);
		return distance;
	}

	//Calculate the Force exerted by BodyB on BodyA
	public double calcForceExertedBy(Body b){
		double gravityConst = 6.67 * Math.pow(10,-11);
		double distance = this.calcDistance(b);
		double force = (gravityConst * this.mass * b.mass)/(distance*distance);
		return force;
	}
	//Calculate the XForce exerted by BodyB on BodyA
	public double calcForceExertedByX(Body b){
		double force = this.calcForceExertedBy(b);
		double forcex = (force * (b.xxPos - this.xxPos))/this.calcDistance(b);
		return forcex;
	}
	//Calculate the YForce exerted by BodyB on BodyA
	public double calcForceExertedByY(Body b){
		double force = this.calcForceExertedBy(b);
		double forcey = (force * (b.yyPos - this.yyPos))/this.calcDistance(b);
		return forcey;
	}
	//Calculate the Net XForce exerted by BodyB on BodyA
	public double calcNetForceExertedByX(Body[] bodyarray){
		int index = bodyarray.length-1;
		double xxNetForce = 0;
		while(index >= 0)
			{
				if (! bodyarray[index].equals(this))
					{
						xxNetForce += calcForceExertedByX(bodyarray[index]);
					}
				index -= 1;
			}
		return xxNetForce;
	}
	//Calculate the Net YForce exerted by BodyB on BodyA
	public double calcNetForceExertedByY(Body[] bodyarray){
		int index = bodyarray.length-1;
		double yyNetForce = 0;
		while(index >= 0)
			{
				if (! bodyarray[index].equals(this))
					{
						yyNetForce += calcForceExertedByY(bodyarray[index]);
					}
				index -= 1;
			}
		return yyNetForce;
	}
	//Update on the status of the Body
	public void update(double dt,double fX,double fY){
		double xxAcel = fX/this.mass;
		double yyAcel = fY/this.mass;
		xxVel = xxVel + xxAcel*dt;
		yyVel = yyVel + yyAcel*dt;
		xxPos = xxPos + xxVel*dt;
		yyPos = yyPos + yyVel*dt;
	}

	public void draw(){

		StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
	}
}