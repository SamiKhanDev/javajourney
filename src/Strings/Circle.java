package Strings;

public class Circle {
  double radius;
  
  public Circle(double radius){
      this.radius = radius;
  }
  
  public double getRadius(){
      if(radius < 0){
          return 0;
      }  else  {
          return radius;
      }
  }
  
  public double getArea(){
      return (radius * radius * Math.PI);
  }
  
}
 class Cylinder extends Circle{
    double height;
    
    public Cylinder(double height, double radius){
        super(radius); 
        this.height = height;
    }
    
    public double getHeight(){
      if(height < 0){
          return 0;
      } else {
          return height;
      } 
    }
      
    public double getVolume(){
        return getArea() * getHeight();
    }
  }



