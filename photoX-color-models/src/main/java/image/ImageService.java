package image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void addImage(byte image []) throws IOException {
        imageRepository.setSrcImage(image);
    }

    public String getBase64SrcImage(){
        return imageRepository.getBase64SrcImage();
    }

    public String getBase64DstImage(){
        return imageRepository.getBase64DstImage();
    }

    public int[] getSrcPixels(){
        return imageRepository.getSrcPixels();
    }

    public void setDstPixels(int[] dstPixels){
        imageRepository.setDstPixels(dstPixels);
    }

    public int[] getDstPixels(){
        return imageRepository.getDstPixels();
    }

    public void setImageHeader(String header){
        imageRepository.setImageHeader(header);
    }
}
