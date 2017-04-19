package by.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Artsiom on 4/18/2017.
 */
public class GeoIpServiceTests {


  @Test
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("50.235.209.170");
    assertEquals(geoIP.getCountryCode(), "USA");
  }
}
