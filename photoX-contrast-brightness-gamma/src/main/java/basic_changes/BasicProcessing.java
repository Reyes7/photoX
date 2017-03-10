package basic_changes;

import config.Algorithms;
import image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class BasicProcessing {

    @Autowired
    ImageService imageService;

    public Optional<String> computeBasicChanges(BasicChangesModel basicChanges) {
        BasicsAlgorithms basicsAlgorithms = new BasicsAlgorithms(basicChanges);
        basicsAlgorithms.computeLUT();

        Optional optionalImage = Optional.ofNullable(imageService.getDstPixels());
        if (optionalImage.isPresent()) {
            process(basicsAlgorithms);
            return Optional.of(imageService.getBase64DstImage());
        } else {
            return Optional.empty();
        }
    }

    private void process(Algorithms algorithms) {
        int srcPixels[] = imageService.getSrcPixels();
        int dstPixels[] = imageService.getDstPixels();
        for (int i = 0; i < srcPixels.length; i++) {
            dstPixels[i] = algorithms.change(srcPixels[i]);
        }
        imageService.setDstPixels(dstPixels);
    }
}