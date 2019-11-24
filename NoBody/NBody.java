/**
Author: Ryan Tan
CS61B Spring 2019
NBody class
*/

public class NBody{

/**
This function determines the radius of the universe from a given text file.
*/
	public static double readRadius(String filename)
	{
		In in = new In(filename);
		int numofbodies = in.readInt();
		double radiusUniverse = in.readDouble();
		return radiusUniverse;
	}
/**
This function determines the scope of the Bodies in the textfile and group all of them within one single array.
The readBodiese is refering to the bodies of the universe such as planets and the Sun.
*/
	public static Body[] readBodies(String filename)
	{
		In in = new In(filename);
		int numofbodies = in.readInt();
		double radiusUniverse = in.readDouble();
		Body[] listofbodies = new Body[numofbodies];
		int counter = 0;

		//Create one single body instance from the given values and inserting it into the created array

		while(counter < numofbodies){
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double mass = in.readDouble();
			String image = in.readString();
			Body newBody = new Body(xPos,yPos,xVel,yVel,mass,image);
			listofbodies[counter] = newBody;
			counter += 1;
		}
		return listofbodies;
	}

	public static void main(String[] args) {

		 //Create the Universe constants and variables.
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double universeradius = readRadius(filename);
		Body[] universebodyarray = readBodies(filename);

		//Draws the starfield image that is supposively to be the universe. (Background)
		StdDraw.clear();
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-universeradius, universeradius);
		StdDraw.picture(0,0,"images/starfield.jpg",universeradius*2,universeradius*2);


		//Draws each universe body
		for(int i=0; i<universebodyarray.length;i++){

			universebodyarray[i].draw();
		}

		/**
		Animation Loop, loop until this time variable reaches the T from above.
		Force arrays are created and then the net forces are calculated.
		*/
		double time = 0;
		while (time <= T){
			double[] xForces = new double[universebodyarray.length];
			double[] yForces = new double[universebodyarray.length];

			for(int i=0; i<universebodyarray.length;i++){
				xForces[i] = universebodyarray[i].calcNetForceExertedByX(universebodyarray);
				yForces[i] = universebodyarray[i].calcNetForceExertedByY(universebodyarray);
			
			}

			for(int i=0; i<universebodyarray.length;i++){

				universebodyarray[i].update(dt,xForces[i],yForces[i]);
			}	

			StdDraw.picture(0,0,"images/starfield.jpg",universeradius*2,universeradius*2);
			for(int i=0; i<universebodyarray.length;i++){

				universebodyarray[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		
	StdOut.printf("%d\n", universebodyarray.length);
	StdOut.printf("%.2e\n", universeradius);
	for (int i = 0; i < universebodyarray.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  universebodyarray[i].xxPos, universebodyarray[i].yyPos, universebodyarray[i].xxVel,
                  universebodyarray[i].yyVel, universebodyarray[i].mass, universebodyarray[i].imgFileName);   
		}

	}

}