package basic_changes;

import config.ApplicationEndpoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping(ApplicationEndpoints.ALGORITHMS_ENDPOINT)
public class BasicController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicController.class);

    @Autowired
    BasicProcessing basicProcessing;

    @PostMapping(
            value = ApplicationEndpoints.BASIC_CHANGES_ENDPOINT,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> basicChanges(@RequestBody BasicChangesModel model) {
        LOGGER.info("basic request: {}", model);
        Optional<String> optionalImage = basicProcessing.computeBasicChanges(model);
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