package utilities;
// mutable 2D vectors
public final class Vector2D {
    public double x, y;

    // constructor for zero vector
    public Vector2D() {
        x=0;
        y=0;
    }

    // constructor for vector with given coordinates
    public Vector2D(double x, double y) {
        this.x=x;
        this.y=y;
    }

    // constructor that copies the argument vector
    public Vector2D(Vector2D v) {
        x = v.x;
        y = v.y;
    }

    // set coordinates
    public Vector2D set(double x, double y) {
        this.x=x;
        this.y=y;

        return this;
    }

    // set coordinates based on argument vector
    public Vector2D set(Vector2D v) {
        this.x  = v.x;
        this.y  = v.y;

        return this;
    }

    // compare for equality (note Object type argument)
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(!(o instanceof Vector2D)){
        	return false;
        }
        Vector2D other = (Vector2D) o;
        if(other.x == this.x && other.y == this.y){
        	return true;
        }
        return false;
    }

    // String for displaying vector as text
    public String toString() {
        return "("+x+","+y+")";
    }

    //  magnitude (= "length") of this vector
    public double mag() {
        return Math.sqrt(x*x + y*y);
    }

    // angle between vector and horizontal axis in radians in range [-PI,PI]
// can be calculated using Math.atan2
    public double angle() {
        return Math.atan2(y,x);
    }

    // angle between this vector and another vector in range [-PI,PI]
    public double angle(Vector2D other) {
        double result = other.angle() - this.angle();
        
        result += result < -Math.PI? 2*Math.PI: 
        			result > Math.PI? -2*Math.PI:0;
        
        return result;
    }

    // add argument vector
    public Vector2D add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;

        return this;
    }

    // add values to coordinates
    public Vector2D add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    // weighted add - surprisingly useful
    public Vector2D addScaled(Vector2D v, double fac) {
        this.x += v.x*fac;
        this.y += v.y*fac;

        return this;
    }

    // subtract argument vector
    public Vector2D subtract(Vector2D v) {
        x -= v.x;
        y -= v.y;

        return this;
    }

    // subtract values from coordinates
    public Vector2D subtract(double x, double y) {
        this.x -= x;
        this.y -= y;

        return this;
    }

    // multiply with factor
    public Vector2D mult(double fac) {
        x*=fac;
        y*=fac;

        return this;
    }

    // rotate by angle given in radians
    public Vector2D rotate(double angle) {
    	
    	double newX = x*Math.cos(angle) - y*Math.sin(angle);
    	double newY = x*Math.sin(angle) + y*Math.cos(angle);
    	
        x = newX;
        y = newY;

        return this;
    }

    // "dot product" ("scalar product") with argument vector
    public double dot(Vector2D v) {
        return x*v.x + y*v.y;
    }

    // distance to argument vector
    public double dist(Vector2D v) {
        return Math.sqrt(
                Math.pow(v.x - x,2) +
                Math.pow(v.y - y, 2)
                );
    }

    // normalise vector so that magnitude becomes 1
    public Vector2D normalise() {
    	double mag = mag();
    	
        x /=mag;
        y /=mag;

        return this;
    }

    // wrap-around operation, assumes w> 0 and h>0
    public Vector2D wrap(double w, double h) {
        if(x >= w)
            x %= w;
        if(y >= h)
            y %= h;
        if(x <= 0)
            x += w;
        if(y <= 0)
            y += h;

        return this;
    }

    // construct vector with given polar coordinates
    public static Vector2D polar(double angle, double mag) {
        double x = mag * Math.cos(angle);
        double y = mag * Math.sin(angle);

        return new Vector2D(x,y);
    }

}