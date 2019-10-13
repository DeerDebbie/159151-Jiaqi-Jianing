package edit;
  

import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Dimension;  
import java.awt.Font;  
import java.awt.GraphicsEnvironment;  
import java.awt.GridLayout;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.InputEvent;  
import java.awt.event.KeyEvent;  
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
import java.io.BufferedReader;    
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;  
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.ButtonGroup;  
import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JCheckBox;  
import javax.swing.JCheckBoxMenuItem;  
import javax.swing.JColorChooser;  
import javax.swing.JComboBox;  
import javax.swing.JDialog;  
import javax.swing.JFileChooser;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JMenu;  
import javax.swing.JMenuBar;  
import javax.swing.JMenuItem;  
import javax.swing.JOptionPane;  
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;  
import javax.swing.JRadioButtonMenuItem;  
import javax.swing.JScrollPane;  
import javax.swing.JTextArea;  
import javax.swing.JTextField;  
import javax.swing.JToolBar;  
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.WindowConstants;
 
@SuppressWarnings("serial")  
public class Texteditor extends JFrame {  
    // �������  
    private JComboBox combox_name, combox_size;// ���塢�ֺ���Ͽ�  
    private JButton button_larger,button_smaller,button_color;//�������С����ɫѡ����  
    private JCheckBox checkb_bold, checkb_italic;// ���塢б�帴ѡ��  
    private JPopupMenu popupmenu;  
    private JTextArea ta = new JTextArea();  
    private JScrollPane sp = new JScrollPane(ta); 
    public JFileChooser filechooser = new JFileChooser(); //�ļ�ѡ����
    //���ҶԻ�������  
    private JTextField tf_search;  
    private JButton button_next;  
    //  
    private int key=0;  
  
    public  Texteditor(String str) {  
        super(str);  
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
        Dimension dim = getToolkit().getScreenSize(); // �����Ļ�ֱ���  
        this.setBounds(dim.width / 4, dim.height / 4, 700, 480);  

        JToolBar toolbar = new JToolBar(); // ����������  
        this.add(toolbar, BorderLayout.NORTH); // ��������ӵ����񱱲�  
        this.add(sp);  
        ta.setLineWrap(true);// ����  
        //////////////////����  
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
        String[] fontsName = ge.getAvailableFontFamilyNames(); // ���ϵͳ����  
        combox_name = new JComboBox(fontsName);  
        toolbar.add(combox_name);  
        combox_name.addActionListener(new ActionListener() {// �ֺ�  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                int size = font.getSize();  
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        /////////////////�ֺ�  
        String sizestr[] = { "20", "30", "40", "50", "60", "70" ,"80","90","100"};  
        combox_size = new JComboBox(sizestr);  
        combox_size.setEditable(true);  
        toolbar.add(combox_size);  
        combox_size.addActionListener(new ActionListener() {// �ֺ�  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                int size = Integer.parseInt((String)combox_size.getSelectedItem());  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        ////////////////////�ֺżӼ���ť  
        button_larger=new JButton("A+");  
                toolbar.add(button_larger);  
        button_larger.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                int size = font.getSize()+5;  
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        button_smaller=new JButton("A-");  
        toolbar.add(button_smaller);  
        button_smaller.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                int size = font.getSize()-5;  
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        /////////////////J  
        /////////////////�����б��  
        checkb_bold = new JCheckBox("����"); //���θ�ѡ��  
        toolbar.add(checkb_bold);  
        checkb_bold.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                int size = font.getSize();  
                style = style ^ 1;  
                ta.setFont(new Font(fontname, style, size));  
            }  
                    });  
        checkb_italic = new JCheckBox("б��");  
        toolbar.add(checkb_italic);  
        checkb_italic.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                int size = font.getSize();  
                style = style ^ 2;  
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        ////////////////  
        JRadioButton radiob_color[];  
        String colorstr[]={"��","��","��"};  
        ButtonGroup bgroup_color = new ButtonGroup();      //��ť��  
        radiob_color = new JRadioButton[colorstr.length];  //��ɫ��ѡ��ť����  
        for (int i=0; i<radiob_color.length; i++){  
            radiob_color[i]=new JRadioButton(colorstr[i]);   
            bgroup_color.add(radiob_color[i]); //��ӵ���ť��  
            toolbar.add(radiob_color[i]);     //��ӵ�������  
        }          
        radiob_color[0].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.setForeground(Color.red);// ������ɫ  
            }  
        });  
        radiob_color[1].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.setForeground(Color.green);  
            }  
        });  
        radiob_color[2].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.setForeground(Color.blue);  
            }  
        });  
        ///////////////��ɫѡ����  
        button_color=new JButton("����");  
        toolbar.add(button_color);  
        button_color.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                Color color;  
                color=JColorChooser.showDialog(Texteditor.this,"��ɫѡ��", Color.black);  
                ta.setForeground(color);// ������ɫ  
            }  
        });  
        ////////////////����¼�  
        ta.addMouseListener(new MouseAdapter() {// ����¼����������һ������˵�  
            public void mouseClicked(MouseEvent e) {  
                if (e.getModifiers() == MouseEvent.BUTTON3_MASK) // ������������Ҽ�  
                    popupmenu.show(ta, e.getX(), e.getY()); // ����굥������ʾ��ݲ˵�  
            }  
        });  
        ////////////////  
        this.addmyMenu();       //�����Զ��巽������Ӳ˵�  
        this.setVisible(true);  
    }  
  
    private void addmyMenu() {// ������˵�����ݲ˵����Ի���  
        JMenuBar menubar = new JMenuBar(); // �˵���  
        this.setJMenuBar(menubar); // ��Ӳ˵���  
        String menustr[] = { "�ļ�", "�༭", "����", "����" };  
        JMenu menu[] = new JMenu[menustr.length];  
        for (int i = 0; i < menustr.length; i++) {  
           menu[i] = new JMenu(menustr[i]); // �˵�  
            menubar.add(menu[i]); // �˵����м���˵�  
        }  
        ////////////////////////////////  
        JMenuItem menuitem_open = new JMenuItem("��");  
        menu[0].add(menuitem_open);  
        menuitem_open.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) { 
         
           

                JFileChooser filechooser = new JFileChooser();  
                int result = filechooser.showOpenDialog(Texteditor.this);  
                if (result == JFileChooser.APPROVE_OPTION) {  
                    try {  
                        File file = filechooser.getSelectedFile();  
                        FileReader fr = new FileReader(file);  
                        BufferedReader br = new BufferedReader(fr);  
                        ta.setText("");  
                        String text;  
                        while ((text = br.readLine()) != null) {  
                            ta.append(text); 
                            ta.append("\n");
                        }  
                        fr.close();  
                        br.close();  
                    } catch (Exception ex) {  
                        JOptionPane.showMessageDialog(Texteditor.this,"���ĵ�����");  
                    }  
                }  
                
            
            }  
        });  
        JMenuItem menuitem_save = new JMenuItem("����");  
        menu[0].add(menuitem_save);  
        menuitem_save.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	int i=filechooser.showSaveDialog(Texteditor.this);
    			if(i==JFileChooser.APPROVE_OPTION)
    			{
    				File f=filechooser.getSelectedFile();
    				try				
    				{
    					FileOutputStream out=new FileOutputStream(f);
    					out.write(ta.getText().getBytes());
    				}
    				catch(Exception ex)
    				{					 
    					ex.printStackTrace();
    				}			
    			}
  
            }  
        });  
        JMenuItem menuitem_save1 = new JMenuItem("����ΪPDF");  
        menu[0].add(menuitem_save1);  
        menuitem_save1.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	int i=filechooser.showSaveDialog(Texteditor.this);
    			if(i==JFileChooser.APPROVE_OPTION)
    			{
    				File f=filechooser.getSelectedFile();
    				try				
    				{
    					FileOutputStream out=new FileOutputStream(f);
    					out.write(ta.getText().getBytes());
    				}
    				catch(Exception ex)
    				{					 
    					ex.printStackTrace();
    				}			
    			}
  
            }  
        });  
        
        menu[0].addSeparator(); // �ӷָ���  
        JMenuItem menuitem_exit = new JMenuItem("�˳�");  
        menu[0].add(menuitem_exit);  
        menuitem_exit.addActionListener(new ActionListener() {// �˳�  
                    public void actionPerformed(ActionEvent e) {  
                        System.exit(0);  
                    }  
                });  
        /////////////////////////////  
        JMenu menu_style = new JMenu("����");  
        JCheckBoxMenuItem checkboxmenuitem_bold = new JCheckBoxMenuItem("����");  
        menu_style.add(checkboxmenuitem_bold);  
        JCheckBoxMenuItem checkboxmenuitem_italic = new JCheckBoxMenuItem("б��");  
        menu_style.add(checkboxmenuitem_italic);  
        menu[1].add(menu_style); // �˵����뵽�˵��г�Ϊ�����˵�  
        checkboxmenuitem_bold.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                int size = font.getSize();  
                style = style ^ 1;  
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
          
        checkboxmenuitem_italic.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                int size = font.getSize();  
                style = style ^ 2;  
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        ////////////////////////////  
        JMenu menu_color = new JMenu("��ɫ");  
        menu[1].add(menu_color);  
        ButtonGroup buttongroup = new ButtonGroup();  
        String colorstr[] = { "��", "��", "��" };  
        JRadioButtonMenuItem rbmi_color[] = new JRadioButtonMenuItem[colorstr.length];  
        for (int i = 0; i < rbmi_color.length; i++) {  
            rbmi_color[i] = new JRadioButtonMenuItem(colorstr[i]); // ��ѡ�˵���  
            buttongroup.add(rbmi_color[i]);  
            menu_color.add(rbmi_color[i]);  
        }  
        rbmi_color[0].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.setForeground(Color.red);  
            }  
        });  
        rbmi_color[1].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.setForeground(Color.green);  
            }  
        });  
        rbmi_color[2].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.setForeground(Color.blue);  
            }  
        });  
        /////////////////////////////////  
        JMenuItem menuitem_countwordsnum = new JMenuItem("����ͳ��");  
        menu[2].add(menuitem_countwordsnum);  
        menuitem_countwordsnum.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                int count=0;  
                for(int i=0;i<ta.getText().length();i++){  
                    if(!ta.getText().substring(i,i+1).equals(" ")){  
                        count++;  
                    }  
                }  
                JOptionPane.showMessageDialog(Texteditor.this, "�ı�����һ����"+count+"���ַ���");  
            }  
        });
        JMenuItem menuitem_countwordsnum1 = new JMenuItem("����");  
        menu[2].add(menuitem_countwordsnum1);  
        menuitem_countwordsnum1.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	ta.cut();
            	}
        });  
        JMenuItem menuitem_countwordsnum2 = new JMenuItem("����");  
        menu[2].add(menuitem_countwordsnum2);  
        menuitem_countwordsnum2.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	ta.copy();
            	}
        });  
        JMenuItem menuitem_countwordsnum3= new JMenuItem("ճճ");  
        menu[2].add(menuitem_countwordsnum3);  
        menuitem_countwordsnum3.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	ta.paste();
            	}
        });  
        menu[2].addSeparator(); // �ӷָ���  
        JMenuItem menuitem_search = new JMenuItem("����");  
        menu[2].add(menuitem_search);  
        menuitem_search.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                new MessageJDialog();  
                  
                button_next.addActionListener(new ActionListener() {  
                    public void actionPerformed(ActionEvent e) {  
                        String str_search=tf_search.getText();  
                        int len = str_search.length();  
                        for (int i = key; i < ta.getText().length() - len + 1; i++) {  
                            String str_record = ta.getText().substring(i, i + len);  
                            if (str_record.equals(str_search)) {  
                                key = i + 1;  
                                ta.requestFocus();  
                                ta.select(i, i + len);  
                                return;  
                            }  
                        }  
                    }  
                });  
                  
                key=0;  
            }  
        });  
        JMenuItem menuitem_replace = new JMenuItem("�滻");  
        menu[2].add(menuitem_replace);  
        menuitem_replace.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String str_replace=JOptionPane.showInputDialog(Texteditor.this,  
                        "��������Ҫ�滻���ַ���:" );  
                String str_replacelater=JOptionPane.showInputDialog(Texteditor.this,  
                        "��������Ҫ�����滻������:" );  
                int len=str_replace.length();  
                for(int i=0;i<ta.getText().length()-len+1;i++){  
                    String str_record=ta.getText().substring(i, i+len);  
                    if(str_record.equals(str_replace)){  
                        ta.replaceRange(str_replacelater,i, i+len);  
                    }  
                }  
            }  
        });  
        /////////////////////////////////  
        JMenuItem menuitem_about = new JMenuItem("����");  
        menu[3].add(menuitem_about);  
        menuitem_about.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                JOptionPane.showMessageDialog(Texteditor.this,"�ı��༭��v1.0   �����ߣ�������,������");  
            }  
        }); 
        JMenuItem menuitem_about1 = new JMenuItem("ʱ��");  
        menu[3].add(menuitem_about1);  
        menuitem_about1.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	setSize(300,100); 
  			  setVisible(true); 
  			  setDefaultCloseOperation(EXIT_ON_CLOSE); 
  			  Timer timer = new Timer(1000,  new ActionListener(){ 
  			   public void actionPerformed(ActionEvent evt) { 
  			    setTitle( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); 
  			   } 
  			  } 
  			  ); 
  			  timer.start();   
  		}		
             
        });  
        JMenuItem menuitem_about2 = new JMenuItem("��ӡ");  
        menu[3].add(menuitem_about2);  
        menuitem_about2.addActionListener(new ActionListener() {  
        	public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			JFileChooser fileChooser = new JFileChooser(); // ������ӡ��ҵ
    	        int state = fileChooser.showOpenDialog(null);
    	        if (state == fileChooser.APPROVE_OPTION) {
    	            File file = fileChooser.getSelectedFile(); // ��ȡѡ����ļ�
    	            // ������ӡ�������Լ�
    	            HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
    	            // ���ô�ӡ��ʽ����Ϊδȷ�����ͣ�����ѡ��autosense
    	            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
    	            // �������еĿ��õĴ�ӡ����
    	            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
    	            // ��λĬ�ϵĴ�ӡ����
    	            PrintService defaultService = PrintServiceLookup
    	                    .lookupDefaultPrintService();
    	            // ��ʾ��ӡ�Ի���
    	            PrintService service = ServiceUI.printDialog(null, 200, 200,
    	                    printService, defaultService, flavor, pras);
    	            if (service != null) {
    	                try {
    	                    DocPrintJob job = service.createPrintJob(); // ������ӡ��ҵ
    	                    FileInputStream fis = new FileInputStream(file); // �������ӡ���ļ���
    	                    DocAttributeSet das = new HashDocAttributeSet();
    	                    Doc doc = new SimpleDoc(fis, flavor, das);
    	                    job.print(doc, pras);
    	                } catch (Exception e1) {
    	                    e1.printStackTrace();
    	                }
    	            }
    	        }
        	}
        }); 
        ////////////////////////////////////////////////// ��ݲ˵�����  
        popupmenu = new JPopupMenu();  
        String menuitemstr[] = { "����", "����", "ճ��" };  
        JMenuItem popmenuitem[] = new JMenuItem[menuitemstr.length];  
        for (int i = 0; i < popmenuitem.length; i++) {  
            popmenuitem[i] = new JMenuItem(menuitemstr[i]);// �˵���  
            popupmenu.add(popmenuitem[i]);// ��ݲ˵�����˵���  
        }  
        popmenuitem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,  
                InputEvent.CTRL_MASK));// ���ÿ�ݼ�Ctrl+X  
        popmenuitem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,  
                InputEvent.CTRL_MASK));// ���ÿ�ݼ�Ctrl+C  
        popmenuitem[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,  
                InputEvent.CTRL_MASK));// ���ÿ�ݼ�Ctrl+V  
  
        popmenuitem[0].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.cut(); //��ѡ���ı�������ϵͳ������  
            }  
        });  
        popmenuitem[1].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.copy(); //��ѡ���ı�������ϵͳ������  
            }  
        });  
        popmenuitem[2].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.paste();//��������ı�ճ���ڵ�ǰλ��  
            }  
        });  
        ta.add(popupmenu); // �ı�����ӿ�ݲ˵�  
    }  
  
    //  
    private class MessageJDialog extends JDialog {  
        private JLabel lable_tip;  
        private JPanel panel_next = new JPanel();  
        private JPanel panel_search = new JPanel();  
        private JPanel panel_tip = new JPanel();  
  
        public MessageJDialog() {  
            super(Texteditor.this, "����");  
            this.setSize(300, 170);  
            this.setLocation(Texteditor.this.getX() + 200,  
                    Texteditor.this.getY() + 200);  
            this.setLayout(new GridLayout(3, 1));  
            //  
            ImageIcon imageIcon = new ImageIcon("img/search.png");  
            lable_tip = new JLabel("��������Ҫ���ҵ��ַ�����", imageIcon, JLabel.LEFT);  
            panel_tip.add(lable_tip);  
            this.add(panel_tip);  
            tf_search = new JTextField(20);  
            panel_search.add(tf_search);  
            this.add(panel_search);  
            button_next = new JButton("������һ��");  
            panel_next.add(button_next);  
            this.add(panel_next);  
            this.setVisible(true);  
        }  
    }        
    public static void main(String args[]) {  
        new Texteditor("�ı��༭��v1.0");  
    }  
}  

