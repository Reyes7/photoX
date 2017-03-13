package config;

import model.Cmyk;
import model.Hsl;
import model.Lab;
import model.Luv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import processing.ColorModels;

import java.util.Optional;

@RestController
@RequestMapping(ApplicationEndpoints.ALGORITHMS_ENDPOINT)
public class ColorModelsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ColorModelsController.class);

    @Autowired
    ColorModels colorModels;

    @PostMapping(
            value = ApplicationEndpoints.CMYK_ENDPOINT,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> cmyk(@RequestBody Cmyk cmyk){
        LOGGER.info("cmyk request: {}", cmyk);
        Optional<String> optionalImage = colorModels.computeCMYK(cmyk);
        return getResponse(optionalImage);
    }

    @PostMapping(
            value = ApplicationEndpoints.HSL_ENDPOINT,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> hsl(@RequestBody Hsl hsl){
        LOGGER.info("hsl request: {}",hsl);
        Optional<String> optionalImage = colorModels.computeHsl(hsl);
        return getResponse(optionalImage);
    }

    @PostMapping(
            value = ApplicationEndpoints.LAB_ENDPOINT,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> lab(@RequestBody Lab lab){
        LOGGER.info("lab request: {}",lab);
        Optional<String> optionalImage = colorModels.computeLab(lab);
        return getResponse(optionalImage);
    }

    @PostMapping(
            value = ApplicationEndpoints.LUV_ENDPOINT,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> luv(@RequestBody Luv luv){
        LOGGER.info("luv request: {}",luv);
        Optional<String> optionalImage = colorModels.computeLuv(luv);
        return getResponse(optionalImage);
    }

    private ResponseEntity<String> getResponse(Optional<String> optionalImage) {
        if(optionalImage.isPresent()) {
            String imageBase64 = optionalImage.get();
            return new ResponseEntity<String>(imageBase64, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }
}