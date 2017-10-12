import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 
public class ELEARNING {  
	public static JTextField t1;
	public static JTextArea l2;
public static void main(String[] args) {  
JFrame f=new JFrame();//creating instance of JFrame  
Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
JPanel jp = new JPanel();

f.setSize(800,600);         
f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2); 

JButton b=new JButton("Generate");//creating instance of JButton  
b.setBounds(f.getSize().width - 125,f.getSize().height/2-50-200,150, 40);//x axis, y axis, width, height
b.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e){
		try{
			String input_arg = t1.getText();
			//input_arg = "\"Hello world\"";
			Runtime r = Runtime.getRuntime();
			Process p = r.exec("python create_question_file.py "+input_arg);
			p.waitFor();
			BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			String whole = "";
			while ((line = b.readLine()) != null) {
			  whole = whole +" \n"+ line;
			}
			l2.setText(whole);
			b.close();
		}catch(Exception ioe){
			System.out.println(ioe);
		}
			
	}
});

JLabel l1=new JLabel("Folder Name : ");
l1.setBounds(75,f.getSize().height/2-50-200, 200,30);
l1.setFont(new Font("Serif", Font.BOLD, 18));

t1=new JTextField("");  
t1.setBounds(300,f.getSize().height/2-50-200, 350,30);  

l2=new JTextArea();
l2.setLineWrap(true);
l2.setWrapStyleWord(true);
l2.setEditable(false);
l2.setFocusable(false);
l2.setBounds(75,f.getSize().height/2 - 150, f.getSize().width + 900,f.getSize().height + 200);
l2.setFont(new Font("Serif", Font.BOLD, 15));

JScrollPane scrollPane = new JScrollPane(l2);
scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//scrollPane.setBounds(75,f.getSize().height/2 - 150, f.getSize().width - 100,f.getSize().height - 300);
//f.getContentPane().add(scrollPane);
//scrollPane.setViewportView(l2);

f.add(scrollPane);
f.add(l2);
f.add(t1);
f.add(l1);
f.add(b);//adding button in JFrame  
f.setLayout(null);//using no layout managers  
f.setVisible(true);//making the frame visible  
f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	}  
} 
