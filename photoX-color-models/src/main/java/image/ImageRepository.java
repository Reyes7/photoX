package image;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Repository;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@Repository
@Getter(AccessLevel.PUBLIC)
public class ImageRepository {

    @Setter(AccessLevel.PUBLIC)
    private String imageHeader;
    private int width;
    private int height;

    int srcPixels[];

    @Setter(AccessLevel.PUBLIC)
    int dstPixels[];

    public void setSrcImage(byte imageBytes []) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(imageBytes);
        BufferedImage srcImage = ImageIO.read(inputStream);
        width = srcImage.getWidth();
        height = srcImage.getHeight();
        srcPixels = new int [width * height];
        dstPixels = new int [width * height];
        srcImage.getRGB(0,0,srcImage.getWidth(),srcImage.getHeight(),srcPixels,0,srcImage.getWidth());
    }

    public String getBase64SrcImage() {
        if(srcPixels != null){
            return getBase64Image(srcPixels);
        } return "";
    }

    public String getBase64DstImage() {
        if(dstPixels != null){
            return getBase64Image(dstPixels);
        } return "";
    }

    public String getBase64Image(int[] pixels) {
        BufferedImage dstImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        dstImage.setRGB(0,0,width, height,pixels,0,width);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(dstImage, "jpeg", baos );
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageHeader + Base64.encodeBase64String(baos.toByteArray());
    }
}
