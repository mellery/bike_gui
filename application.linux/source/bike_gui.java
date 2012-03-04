import processing.core.*; 
import processing.xml.*; 

import processing.serial.*; 

import java.applet.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class bike_gui extends PApplet {

 	

// Example by Tom Igoe



Serial myPort;  // The serial port

PFont font;  
PrintWriter output;

float circ = 81.6814089933346f;
float speed_con = 0.0568181818f;
   
public void setup() {
  // List all the available serial ports:
  int date[] = new int[5];
  date[1] = day();
  date[0] = month();
  date[2] = year();
  date[3] = hour();
  date[4] = minute();
  String filename = join(nf(date,0), "-");
  
 filename = filename + "-bikelog.txt";
  
  output = createWriter(filename);
  println(Serial.list());
  size(130, 100);
  background(51);
    font = loadFont("CourierNewPSMT-24.vlw"); 
    textFont(font);
    textAlign(CENTER);    
    text("let's go!", width/2, 30); 
    
  // I know that the first port in the serial list on my mac
  // is always my  Keyspan adaptor, so I open Serial.list()[0].
  // Open whatever port is the one you're using.
  myPort = new Serial(this, Serial.list()[0], 57600);
}

public void draw() {

  while (myPort.available() > 0) {
    //String inBuffer = myPort.readString();   
    String inBuffer = myPort.readStringUntil('\n');
    if (inBuffer != null) {
      output.println(inBuffer);
      String[] data = split(inBuffer, ',');
      background(51);
      float distance;
    // The font must be located in the sketch's 
    // "data" directory to load successfully

    //textFont("CourierNewPSMT-24.vlw"); 
    textFont(font);
    textAlign(CENTER);    
    //text(inBuffer, 15, 30); 
    int temp = PApplet.parseInt(data[0]);
    float temp2;
    temp2 = PApplet.parseFloat(data[1])/1000;
    float speed = (circ/temp2);
    speed = speed * speed_con;
    distance = ((temp*circ)/12)/5280;
    text(distance, width/2, 30);
    text(speed, width/2, 60);
    
    }
  }
}

public void keyPressed() {
  output.flush();
  output.close();
  exit();
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#DFDFDF", "bike_gui" });
  }
}
