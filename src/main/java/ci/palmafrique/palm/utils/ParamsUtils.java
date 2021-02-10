package ci.palmafrique.palm.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class ParamsUtils {
 @Value("${spring.datasource.url}")
  private String urlDb;
 
 @Value("${spring.datasource.username}")
  private String userDb;
 
 @Value("${spring.datasource.password}")
  private String passDb;
 
 @Value("${spring.datasource.driverClassName}")
  private String userDataSource;

}
