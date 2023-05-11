package study.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/renew")
public class MyRenewController {
    @GetMapping(value = "/echo", produces = MediaType.TEXT_PLAIN_VALUE)
// 반환한 문자열이 바로 응답 메시지의 바디 데이터에 삽입될 수 있도록 @ResponseBody 어노테이션 추가 (@RestController를 사용하면 생략 가능)
    @ResponseBody
    public String echo(@RequestBody byte[] content) {
    // 메서드 정보 접근할 필요 없음 (GET 메서드)
    // 주소 정보 접근할 필요 없음 (@PathVariable 사용)
    // 쿼리 스트링 정보 접근할 필요 없음 (@RequestParam 사용)
    // 프로토콜, HTTP 버전 정보 접근할 필요가 보통 없음
    // 헤더 정보 접근할 필요 없음 (@RequestHeader 사용)
    // 요청 메시지의 바디 데이터 접근은 @RequestBody 어노테이션을 이용해서 전달받을 수 없음
        String bytesToString = new String(content, StandardCharsets.UTF_8);
//        "/hello-html"
//        "/echo-repeat"
//        "/dog-image"
//        "/dog-image-file"
        System.out.println(bytesToString);
// Content-Type 헤더의 경우 produces 옵션을 제공하여 미디어 타입 지정 가능
        return bytesToString;
    }
    @GetMapping(value = "/hello-html", produces = MediaType.TEXT_HTML_VALUE)
// 반환 코드도 마찬가지로 그냥 성공적으로 메서드에서 값을 반환하면 자동으로 200이 됨
    @ResponseStatus(HttpStatus.OK)
    public String helloHTML() {
        return "<h1>Hello</h1>";
    }
    @GetMapping(value = "/echo-repeat", produces = MediaType.TEXT_PLAIN_VALUE)
// @RequestHeader 어노테이션을 통해서 X-Repeat-Count에 적힌 숫자 정보 가져오고 없으면 1로 초기화
    public String echoRepeat(@RequestParam("word") String word, @RequestHeader(value
            = "X-Repeat-Count", defaultValue = "1") Integer repeatCount) throws IOException
    {
        String result = "";
        for(int i=0;i<repeatCount;i++) {
            result += word;
        }
        return result;
    }

}
