/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateCert {
    String fileName = "C:\\Users\\DIVYA\\Desktop\\JES.CER";
   public static String readFile(String path) 
  throws IOException 
{
  byte[] encoded = Files.readAllBytes(Paths.get(path));
  return new String(encoded, "UTF8");
}
}