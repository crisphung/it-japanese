package quan.hust.itjapanese.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HealthCheckAPI.HEALTH_CHECK_API)
public class HealthCheckAPI
{
  public static final String HEALTH_CHECK_API = "/api/healthcheck";

  @GetMapping
  public ResponseEntity<String> healthCheck()
  {
    return ResponseEntity.ok("Health check!");
  }
}
