/*
 * Copyright 2015 MooUOnline.com
 */
package com.moouonline.textresources;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.ListResourceBundle;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author outtascope
 */
public class TextResource extends ListResourceBundle {
/**
 * Look for << marker
 * ex.
 * KEY1<<ENDOFDATA
 * This is a text resource \
 * \\
 * Yadda Yadda Yadda
 * ENDOFDATA
 * 
 * @return 
 */
  
  
  @Override
  protected Object[][] getContents() {
    Object[][] returnvalue = null;
    String filename = this.getClass().getName().replaceAll(".*\\.", "") + ".txt";
    Map<String,String> data = new HashMap<>();
    StringBuilder value;
    try(LineNumberReader lnr =
            new LineNumberReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
      String inputline;
      while(((inputline = lnr.readLine()) != null)) {
        if(inputline.contains("<<")) {
          String[] parts = inputline.split("<<");
          value = new StringBuilder();
          while((inputline = lnr.readLine()) != null) {
            if(inputline.matches(parts[1])) {
              data.put(parts[0], value.toString());
              break;
            }
            if(inputline.endsWith("\\\\")) {
              value.append(inputline.substring(0, inputline.length() - 1)).append("\n");
            } else if(inputline.endsWith("\\")) {
              value.append(inputline.substring(0, inputline.length() - 1));
            } else {
              value.append(inputline.substring(0, inputline.length())).append("\n");
            }
          }
          if(!inputline.matches(parts[1])) {
            throw new RuntimeException("Premature end of resource encountered.");
          }
        }
      }
      returnvalue = new Object[data.size()][2];
      Set<String> keys = data.keySet();
      int i = 0;
      for(String key : keys) {
        returnvalue[i][0] = key;
        returnvalue[i++][1] = data.get(key);
      }
      
    } catch (IOException ex) {
      throw new RuntimeException("Error encountered reading resource.", ex);
    }
    return returnvalue;
  }
  
}
