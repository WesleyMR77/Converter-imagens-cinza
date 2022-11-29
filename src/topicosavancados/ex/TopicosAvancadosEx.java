/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package topicosavancados.ex;

/**
 *
 * @author Wesley
 */
import ij.ImagePlus;
import ij.process.ImageProcessor;
import java.io.File;
import javax.swing.JFileChooser;
import ij.IJ;
import ij.ImagePlus;
import ij.Prefs;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TopicosAvancadosEx {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File escolherArquivo = escolherArquivo();
        String path = escolherArquivo.getAbsolutePath();

        ImagePlus image = new ImagePlus(path);
        ImageProcessor ip = image.getProcessor();
        ImagePlus original = new ImagePlus("Imagem Original", ip);
        original.show();

        ImagePlus imgGrayscale = image.duplicate();
        ImageConverter converterGrayscale = new ImageConverter(imgGrayscale);
        converterGrayscale.convertToGray8();//convertendo a imagem para tons de cinza
        IJ.run(imgGrayscale, "8-bit", "");
        ImageProcessor ipGrayscale = imgGrayscale.getProcessor().duplicate();

        System.out.println("Largura:" + imgGrayscale.getWidth());
        System.out.println("Altura:" + imgGrayscale.getHeight());
        System.out.println("Valores de pixels:");

        String nPixels = "";
        int [] v = new int[256];
        for (int i =0; i <256;i++){
            v[i] = 0;
        }
        for (int linha = 0; linha < imgGrayscale.getWidth(); linha++) {
            for (int coluna = 0; coluna < imgGrayscale.getHeight(); coluna++) {
                v[ipGrayscale.getPixel(linha, coluna)] ++;
                //System.out.println(v[ipGrayscale.getPixel(linha, coluna)] ++);
            }
        }
        
        
        for (int i =0; i <256;i++){
            System.out.println("pixel cor: " + i + " quantidade: " + v[i]);
        }
        
        imgGrayscale.show();

    }

    public static File escolherArquivo() {
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Apenas imagens", "png", "jpeg", "jpg");
        file.setAcceptAllFileFilterUsed(false);
        file.addChoosableFileFilter(filtro);
        int i = file.showSaveDialog(null);
        if (i == 1) {

        } else {
            File arquivo = file.getSelectedFile();
            return arquivo;
        }
        return null;
    }

}
