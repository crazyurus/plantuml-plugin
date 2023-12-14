package cn.crazyurus.plantuml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
    public ResponseEntity<Result> listHeaders(@RequestBody Parameter parameter) {
        String image;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            SourceStringReader reader = new SourceStringReader("@startuml\n" + parameter.content + "\n@enduml");

            reader.outputImage(os, new FileFormatOption(FileFormat.SVG));

            image = os.toString();
        } catch (IOException e) {
            image = "";
        }

        Result result = new Result(image);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
