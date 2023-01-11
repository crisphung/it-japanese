package quan.hust.itjapanese.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import quan.hust.itjapanese.config.jwt.JwtTokenEntryPoint;
import quan.hust.itjapanese.config.jwt.JwtTokenFilter;
import quan.hust.itjapanese.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
  @Autowired
  private UserService userService;

  @Autowired
  private JwtTokenFilter jwtTokenFilter;

  @Autowired
  private JwtTokenEntryPoint jwtTokenEntryPoint;

  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    // TODO Auto-generated method stub
    return super.authenticationManager();
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors()
      .and()
      .csrf().disable()
      .authorizeRequests()
      .antMatchers(
        "/v2/api-docs",
        "/configuration/ui",
        "/swagger-resources",
        "/configuration/security",
        "/webjars/**",
        "/swagger-resources/configuration/ui",
        "/swagger-ui.html").permitAll()
      .antMatchers("/api/user/login", "/api/user/signup","/swagger-ui/**").permitAll()
      .antMatchers(HttpMethod.GET,"/api/book","/api/book/compare").permitAll()
      .antMatchers(HttpMethod.GET,"/api/healthcheck").permitAll()
      .antMatchers(HttpMethod.GET,"/api/comment","/api/comment/*").permitAll()
      .anyRequest().authenticated()
      .and()
      .logout()
      .logoutUrl("/api/logout")
      .logoutSuccessUrl("/")
      .and()
      .exceptionHandling().authenticationEntryPoint(jwtTokenEntryPoint).and();

    http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/v2/api-docs",
      "/configuration/ui",
      "/swagger-resources/**",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**");
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService)
      .passwordEncoder(getPasswordEncoder());
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source =
      new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}
