/*
 * Copyright 2015 MooUOnline.com
 */
package com.moouonline.textresources;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author outtascope
 */
public class TextResourceTest {
  
  public TextResourceTest() {
  }

  @Test
  public void testGetContents() {
    ResourceBundle bundle = ResourceBundle.getBundle("com.moouonline.textresources.Buttons", Locale.FRENCH);
    System.out.println("STOP_LABEL\n----------\n" + bundle.getString("STOP_LABEL"));
    System.out.println("\nPLAY_LABEL\n----------\n" + bundle.getString("PLAY_LABEL"));
    System.out.println("\nESCAPE_TEST\n-----------\n" + bundle.getString("ESCAPE_TEST"));
  }
  
}
