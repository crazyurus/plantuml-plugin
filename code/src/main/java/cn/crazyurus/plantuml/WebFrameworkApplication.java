package cn.crazyurus.plantuml;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;

@SpringBootApplication
@RestController
public class WebFrameworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebFrameworkApplication.class, args);
    }

    @RequestMapping(value = "/api/generate", method = RequestMethod.POST)
    public ResponseEntity<Result> generate(@RequestBody Parameter parameter) {
        Result result = new Result();

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            SourceStringReader reader = new SourceStringReader("@startuml\n" + parameter.content + "\n@enduml", StandardCharsets.UTF_8.name());

            reader.outputImage(os, new FileFormatOption(FileFormat.SVG, true));

            result.url = Utilities.UploadFile(os.toString());
        } catch (Exception error) {
            System.out.println(error.getMessage());
            result.url = "";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
