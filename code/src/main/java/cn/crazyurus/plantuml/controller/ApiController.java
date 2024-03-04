package cn.crazyurus.plantuml.controller;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import cn.crazyurus.plantuml.pojo.Parameter;
import cn.crazyurus.plantuml.pojo.Result;
import cn.crazyurus.plantuml.utils.Upload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;

@RestController
public class ApiController {
   @RequestMapping(value = "/api/generate", method = RequestMethod.POST)
    public ResponseEntity<Result> generate(@RequestBody Parameter parameter) {
        Result result = new Result();

        try {
            String content = parameter.content.replace("@startuml\n", "").replace("@enduml", "");
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            SourceStringReader reader = new SourceStringReader("@startuml\n!pragma layout smetana\n" + content + "\n@enduml", StandardCharsets.UTF_8.name());

            reader.outputImage(os, new FileFormatOption(FileFormat.SVG, true));

            result.url = Upload.UploadFile(os.toString());
        } catch (Exception error) {
            System.out.println(error.getMessage());
            result.url = "";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
