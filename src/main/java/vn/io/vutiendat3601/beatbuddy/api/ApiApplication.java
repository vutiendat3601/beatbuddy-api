package vn.io.vutiendat3601.beatbuddy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@EnableFeignClients
@SpringBootApplication
public class ApiApplication {

  public static void main(String[] args) {
    final ApplicationContext ctx = SpringApplication.run(ApiApplication.class, args);
    // UserContext.setJwtAuthenticationToken(
    //
    // "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJuRk1nWHIxaVByanBRR1VXbzVHWGxGNkV3VHdWd3oyNU1Zc25wVUFmSnNnIn0.eyJleHAiOjE3MTYyMjk4OTQsImlhdCI6MTcxNjE5NDg3OSwiYXV0aF90aW1lIjoxNzE2MTkzODk0LCJqdGkiOiJiNzdjZTkxNC1hZjNlLTQ0YWUtYWMwYi0xNjdlN2YwOTFjYmMiLCJpc3MiOiJodHRwczovL2F1dGguYmVhdGJ1ZGR5LmlvLnZuL3JlYWxtcy9iZWF0YnVkZHkiLCJzdWIiOiIzNDIyMzkyYi03MzBmLTQ3NzQtYTgxZS1hNjhlNTY1MzViZjAiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ3ZWIiLCJzZXNzaW9uX3N0YXRlIjoiODUxY2RmNmEtNWRhZS00MzM3LThhODAtMDhjM2VmNGI3ODUxIiwicmVzb3VyY2VfYWNjZXNzIjp7InJlc291cmNlLW1hbmFnZW1lbnQiOnsicm9sZXMiOlsidXNlciJdfX0sInNjb3BlIjoiIiwic2lkIjoiODUxY2RmNmEtNWRhZS00MzM3LThhODAtMDhjM2VmNGI3ODUxIn0.izL-L5zKQbOU52qW7ZMttuZJuFIYgssm6Dijho1SAegf-b2ZprRStsCsLztiMcHWyIbrbbSli0BoxDAe7LWG9Q9Nj-FoYRbfbTS3ugtjwZcAFFlnykbMThUGmD_mquXuW867lV8KL6BapSQxxlBs56561ed0N6zn4lIsMlqHx6nQTqJwNJqPnAT7B3jLUOc_Mc6eYi98txxuGNr1eKmQYyfVt6QXad764WdbBFIKkPpy4AFKxODPQU_kQOSHf6acfZbcfj-ZYUUxZRFzCIaHCSZZ_FAXtKhNwNtuaXiPIL4y03IoW_OwLTurPfM7CD2OwF1VQBGFUKacwJSvc7B2TQ");
    // // final ResponseEntity<UserDetailResponse> userResp =
    //     ctx.getBean(AuthFeignClient.class).getCurrentUserDetail();
    // System.out.println();
  }

  // @Autowired private AuthResource authResource;

  // @Autowired private UserResourceRepository userResourceRepo;

  // @EventListener(ApplicationReadyEvent.class)
  // public void test() {
  //   authResource.createResource(
  //       "beatbuddy:playlist:bDzhlZfIh2BkjN1r",
  //       "Những bài hát hay nhất của Sơn Tùng M-TP",
  //       "3422392b-730f-4774-a81e-a68e56535bf0");
  //   System.out.println();
  // }

  // @EventListener(ApplicationReadyEvent.class)
  // public void test2() {
  //   userResourceRepo.findByPkId("b9ea9be3-0b55-4507-a651-4b9c86ebcf96");
  // }

  // public void test3(AuthFeignClient authFeignClient) {
  //   authFeignClient.getCurrentUserDetail();
  //   System.out.println();
  // }
}
