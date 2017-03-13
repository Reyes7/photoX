package processing;

import algorithms.CmykAlgorithms;
import algorithms.HslAlgorithms;
import algorithms.LabAlgorithms;
import algorithms.LuvAlgorithms;
import config.Algorithms;
import image.ImageService;
import model.Cmyk;
import model.Hsl;
import model.Lab;
import model.Luv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ColorModels {

    @Autowired
    ImageService imageService;

    public Optional<String> computeCMYK(Cmyk cmyk) {
        Optional optionalImage = Optional.ofNullable(imageService.getDstPixels());
        if (optionalImage.isPresent()) {
            process(new CmykAlgorithms(cmyk));
            return Optional.of(imageService.getBase64DstImage());
        } else {
            return Optional.empty();
        }
    }

    public Optional<String> computeHsl(Hsl hsl) {
        Optional optionalImage = Optional.ofNullable(imageService.getDstPixels());
        if (optionalImage.isPresent()) {
            process(new HslAlgorithms(hsl));
            return Optional.of(imageService.getBase64DstImage());
        } else {
            return Optional.empty();
        }
    }

    public Optional<String> computeLab(Lab lab) {
        Optional optionalImage = Optional.ofNullable(imageService.getDstPixels());
        if (optionalImage.isPresent()) {
            process(new LabAlgorithms(lab));
            return Optional.of(imageService.getBase64DstImage());
        } else {
            return Optional.empty();
        }
    }

    public Optional<String> computeLuv(Luv luv) {
        Optional optionalImage = Optional.ofNullable(imageService.getDstPixels());
        if (optionalImage.isPresent()) {
            process(new LuvAlgorithms(luv));
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
