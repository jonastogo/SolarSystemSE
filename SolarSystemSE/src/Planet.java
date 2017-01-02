import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

class Planet {
	float		radius;
	float		distance;
	Planet[]	planets;
	float		angle;
	float		orbitspeed;
	boolean		stopped	= false;
	float		orbitspeedsafe;
	Constants	cons	= new Constants();
	Sphere		globe;
	PVector		coords;

	Planet(float r, float o, float d, float a, String img) {
		radius = r;
		distance = d;
		angle = a;
		orbitspeed = o;
		orbitspeedsafe = orbitspeed;
		globe = new Sphere(radius);
		PhongMaterial globeMatrial = new PhongMaterial();
		globeMatrial.setDiffuseMap(new Image(img, 2048, 1024, true, true));
		globe.setMaterial(globeMatrial);
	}

	void orbit() {
		angle = angle + orbitspeed;
		if (planets != null) {
			for (int i = 0; i < planets.length; i++) {
				planets[i].orbit();
			}
		}
	}

	void spawnPlanets(int total) {
		planets = new Planet[total];
		planets[0] = new Planet((float) (cons.merkur[0]) / 2, (float) (cons.merkur[1]), (float) (cons.merkur[2]) + this.radius, (float) cons.merkur[3], (String) (cons.merkur[4]));
		planets[1] = new Planet((float) (cons.venus[0]) / 2, (float) (cons.venus[1]), (float) (cons.venus[2]) + this.radius, (float) cons.venus[3], (String) (cons.venus[4]));
		planets[2] = new Planet((float) (cons.erde[0]) / 2, (float) (cons.erde[1]), (float) (cons.erde[2]) + this.radius, (float) cons.erde[3], (String) (cons.erde[4]));
		planets[3] = new Planet((float) (cons.mars[0]) / 2, (float) (cons.mars[1]), (float) (cons.mars[2]) + this.radius, (float) cons.mars[3], (String) (cons.mars[4]));
		planets[4] = new Planet((float) (cons.jupiter[0]) / 2, (float) (cons.jupiter[1]), (float) (cons.jupiter[2]) + this.radius, (float) cons.jupiter[3], (String) (cons.jupiter[4]));
		planets[5] = new Planet((float) (cons.saturn[0]) / 2, (float) (cons.saturn[1]), (float) (cons.saturn[2]) + this.radius, (float) cons.saturn[3], (String) (cons.saturn[4]));
		planets[6] = new Planet((float) (cons.uranus[0]) / 2, (float) (cons.uranus[1]), (float) (cons.uranus[2]) + this.radius, (float) cons.uranus[3], (String) (cons.uranus[4]));
		planets[7] = new Planet((float) (cons.neptun[0]) / 2, (float) (cons.neptun[1]), (float) (cons.neptun[2]) + this.radius, (float) cons.neptun[3], (String) (cons.neptun[4]));

	}

	void stop() {
		if (planets != null) {
			for (int i = 0; i < planets.length; i++) {
				Planet p = planets[i];
				if (p.stopped) {
					p.orbitspeed = p.orbitspeedsafe;
					p.stopped = false;
					p.stop();
				} else {
					p.orbitspeed = 0;
					p.stopped = true;
					p.stop();
				}
			}
		}
	}

	PVector getShowCoords() {
		return this.coords;
	}

	public void setShowCoords() {
		float y = distance * (float) Math.sin(angle);
		float x = distance * (float) Math.cos(angle);
		coords = new PVector(x, y);
	}

	public Sphere getSphere() {
		return this.globe;
	}
}

class Constants {
	// Durchmesser, Geschwindigkeit, Abstand, Winkel, Bild
	Object	merkur[]	= {
			48.794f, 0.04736f, 200.0f, 0.0f, "file:Data/2kMerkur.jpg"
						};
	Object	venus[]		= {
			121.026f, 0.03502f, 400.0f, 0.0f, "file:Data/2kVenus.jpg"
						};
	Object	erde[]		= {
			127.5632f, 0.02978f, 600.0f, 0.0f, "file:Data/2kErde.jpg"
						};
	Object	mars[]		= {
			67.924f, 0.02413f, 800.0f, 0.0f, "file:Data/2kMars.jpg"
						};
	Object	jupiter[]	= {
			180.9840f, 0.01307f, 1200.0f, 0.0f, "file:Data/2kJupiter.jpg"
						};
	Object	saturn[]	= {
			150.5360f, 0.00969f, 1500.0f, 0.0f, "file:Data/2kSaturn.jpg"
						};
	Object	uranus[]	= {
			120.1180f, 0.00681f, 1800.0f, 0.0f, "file:Data/2kUranus.jpg"
						};
	Object	neptun[]	= {
			110.5280f, 0.00543f, 2100.0f, 0.0f, "file:Data/2kNeptun.jpg"
						};
}
