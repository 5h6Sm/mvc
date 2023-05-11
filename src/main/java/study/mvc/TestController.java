package study.mvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/test")
public class TestController {
//    @GetMapping(value = "/reverse")
//    public String st(@RequestParam("words") String words){
//        String a = "";
//        String[] s = words.split(",");
//        for(int i = s.length -1; i>=0; i--){
//            a += s[i] + ",";
//        }
//        a = a.substring(0, a.length()-1);
//        return a;
//    }
//    @GetMapping(value = "/mul")
//    public int mul(@RequestParam("num1") int a, @RequestParam("num2") int b){
//        return a*b;
//    }
    @GetMapping(value = "/calc/{words}")
    public int calc(
            @PathVariable("words") String words,
            @RequestParam("num1") int a, @RequestParam("num2") int b) throws Exception {
            if(words.equals("mul")){return a*b;}
            else if(words.equals("add")){ return a+b;}
            else if (words.equals("sub")){return a-b;}
            else throw new Exception("해당 연산자는 지원하지 않습니다");
    }
    int cnt = 0;
    @PostMapping("/count")
    public int count(){
        cnt++;
        return cnt;
    }




    @GetMapping("/now")
    @ResponseBody
    public Now now(){
        LocalDateTime dateTime = LocalDateTime.now();
        return new Now(
                dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                dateTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"))
        );
    }

//    public Map<String, Object> now(){
//        LocalDateTime dateTime = LocalDateTime.now();
//        Map<String, Object> map = new HashMap<>();
//        map.put("date", dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        map.put("time", dateTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
//        return map;
//    }
}
class Now{
    String date, time;

    public Now(String date, String time) {
        this.date = date;
        this.time = time;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}






//    GET "/hello"
//    문자열 "Hello World!" 화면에 표시되도록 핸들러 메서드 구현
//    (스프링스럽게 구현, request, response 객체안쓰고)
//    @GetMapping("/hello")
//    public String hello(){
//        return "Hello World";
//    }
//    "/reverse?words=hello,world,mirim"
