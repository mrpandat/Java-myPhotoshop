package GUI.view.layout;

import GUI.model.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class MyHistoricRender implements ListCellRenderer {

    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        BufferedImage bimage = MainModel.getInstance().getHistoric().get(index).getImg();
        Dimension d = getDimension(bimage,200);
        ImageIcon imgicon = new ImageIcon(bimage.getScaledInstance(d.width, d.height, Image.SCALE_DEFAULT));
        jPanel.add(new JLabel(imgicon), BorderLayout.PAGE_END);
        jPanel.add(renderer, BorderLayout.PAGE_START);
        return jPanel;
    }

    private Dimension getDimension(BufferedImage img, int max) {
        int height = img.getHeight(null);
        int width = img.getWidth(null);
        if(height > max || width > max) {
            if(width > height) {
                float ratio = (100*max)/width;
                width = max;
                height = (int)(height*ratio)/100;
            } else if(height > width) {
                float ratio = (100*max)/height;
                height = max;
                width = (int)(width*ratio)/100;
            } else {
                height = max;
                width = max;
            }
        }
        return new Dimension(width,height);
    }
}