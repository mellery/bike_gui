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


public void setup() {
  // List all the available serial ports:
  println(Serial.list());
  size(320, 60);
  background(51);
    font = loadFont("CourierNewPSMT-24.vlw"); 
    textFont(font);
    textAlign(LEFT);    
    text("let's go!", 15, 30); 
    
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
      println(inBuffer);

      background(51);
  
    // The font must be located in the sketch's 
    // "data" directory to load successfully

    //textFont("CourierNewPSMT-24.vlw"); 
    textFont(font);
    textAlign(CENTER);    
    text(inBuffer, 15, 30); 
    }
  }
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#DFDFDF", "bike_gui" });
  }
}
