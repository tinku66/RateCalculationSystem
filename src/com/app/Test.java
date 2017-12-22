/**
 * 
 */
package com.app;

/**
 * @author Tinku & Lavanya
 *
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test {

 public static void main(String[] args) {

  String csvFile = "";
  try {
   csvFile = args[0];
  } catch (Exception e) {
   System.out.println("Please provide the file name");
   System.exit(0);
  }

  String line = "";
  String csvSplitBy = ",";
  try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
   int count = 1;
   while ((line = br.readLine()) != null) {
    String[] data = line.split(csvSplitBy);
    System.out.println("Result " + data[1]);
   
   }
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
}
