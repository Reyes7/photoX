package image;

import config.ApplicationEndpoints;
import config.ImageFormat;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApplicationEndpoints.MAIN_ENDPOINT)
public class ImageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    ImageService imageService;

    @RequestMapping(
            value = ApplicationEndpoints.IMAGE_ENDPOINT,
            method = RequestMethod.POST)
    public ResponseEntity<String> uploadImage(@RequestBody String image){
        byte[] imageByte;
        String header = image.substring(0,23);
        image = findHeader(image, header);
        try {
            imageByte = Base64.decodeBase64(image);
            imageService.addImage(imageByte);
            LOGGER.info("Image was uploaded");
        }
        catch(Exception e) {
            LOGGER.error("Error in image upload: {}",e.getMessage());
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(
            value = ApplicationEndpoints.BASE_IMAGE,
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getBaseImage(){
        return new ResponseEntity<String>(imageService.getBase64SrcImage(),HttpStatus.OK);
    }

    private String findHeader(String image, String header) {
        for (Object format : ImageFormat.values()) {
            if(header.contains(format.toString().toLowerCase())){
                image = setHeader(image,format.toString());
            }
        }
        return image;
    }

    public String setHeader(String image, String format) {
        int headerLength = 23;

        if(format.length()==4) {
            imageService.setImageHeader(image.substring(0, headerLength));
            image = image.substring(headerLength, image.length());
        }else{
            imageService.setImageHeader(image.substring(0, headerLength-1));
            image = image.substring(headerLength-1, image.length());
        }
        return image;
    }
}
