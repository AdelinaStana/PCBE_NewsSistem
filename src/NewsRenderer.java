import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
 
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
 
public class NewsRenderer extends JPanel implements ListCellRenderer<News> {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbIcon = new JLabel();
    private JLabel lbDomeniu = new JLabel();
    private JLabel lbAutor = new JLabel();
    private JLabel lbSubdomeniu = new JLabel();
    private JLabel lbSursa = new JLabel();
    private JLabel lbTitlu = new JLabel();
    private JLabel lbDataCreated= new JLabel();
    private JLabel lbDataModified= new JLabel();
 
    public NewsRenderer() {
        setLayout(new BorderLayout(5, 5));
 
        JPanel panelText = new JPanel(new GridLayout(0, 1));
        panelText.add(lbDomeniu);
        panelText.add(lbSubdomeniu);
        panelText.add(lbSursa);
        panelText.add(lbAutor);
        panelText.add(lbDataCreated);
        panelText.add(lbDataModified);
        panelText.add(lbTitlu);
        add(lbIcon, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);
    }
 
    @Override
    public Component getListCellRendererComponent(JList<? extends News> list,
            News news, int index, boolean isSelected, boolean cellHasFocus) {
 
        lbIcon.setIcon(new ImageIcon(news.getIconName() + ".jpg"));
        lbDomeniu.setText("Domeniu : "+news.getDomain());
        lbDomeniu.setForeground(Color.red);
        lbSubdomeniu.setText("Subdomeniu : "+news.getSubdomain());
        lbSursa.setText("Sursa : "+news.getSource());
        lbAutor.setText("Autor : "+news.getAuthor());
        lbDataCreated.setText("Data publicarii : "+news.getDateCreateds());
        lbDataModified.setText("Data modificarii: "+news.getDateModified());
        lbTitlu.setText("Titlul : "+news.getTitle());
        lbTitlu.setForeground(Color.blue);
        
     // set Opaque to change background color of JLabel
        lbDomeniu.setOpaque(true);
        lbSubdomeniu.setOpaque(true);
        lbSursa.setOpaque(true);
        lbAutor.setOpaque(true);
        lbDataCreated.setOpaque(true);
        lbDataModified.setOpaque(true);
        lbTitlu.setOpaque(true);
        
        if (isSelected) {
        	lbDomeniu.setBackground(list.getSelectionBackground());
            lbSubdomeniu.setBackground(list.getSelectionBackground());
            lbSursa.setBackground(list.getSelectionBackground());
            lbAutor.setBackground(list.getSelectionBackground());
            lbDataCreated.setBackground(list.getSelectionBackground());
            lbDataModified.setBackground(list.getSelectionBackground());
            lbTitlu.setBackground(list.getSelectionBackground());
            lbIcon.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());
        } else { // when don't select
        	lbDomeniu.setBackground(list.getBackground());
            lbSubdomeniu.setBackground(list.getBackground());
            lbSursa.setBackground(list.getBackground());
            lbAutor.setBackground(list.getBackground());
            lbDataCreated.setBackground(list.getBackground());
            lbDataModified.setBackground(list.getBackground());
            lbTitlu.setBackground(list.getBackground());
            lbIcon.setBackground(list.getBackground());
            setBackground(list.getBackground());
        }
 
        return this;
    }
}