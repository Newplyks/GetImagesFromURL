import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        String url = args[0];
        Document doc = Jsoup.connect(url).get();
        Elements media = doc.getElementsByTag("img");
        Pattern p = Pattern.compile("https?");
        String urlImage="";
        for (Element src : media) {
            urlImage=src.attr("abs:src");
            if(urlImage.isEmpty()){
                continue;
            }
            Matcher m = p.matcher(urlImage);
            BufferedImage image = ImageIO.read(new URL(m.replaceFirst("https")));
            long size = (long)image.getData().getDataBuffer().getSize()*4l;
            System.out.println("Image url: " + urlImage +" "+image.getWidth() +"x" + image.getHeight() + " size: " + size/1024 + " Кб");
        }
    }



}
