package ru.stga.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by serg on 08.05.2017.
 */
public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    GeoIP geoIp = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("109.127.189.226");
    assertEquals(geoIp.getCountryCode(), "RUS");

  }

}
