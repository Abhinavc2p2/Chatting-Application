// package chatting.application;
import javax.swing.*;
// import javax.swing.border.Border;
import javax.swing.border.*;
// import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.util.*;
import java.text.*;
// import java.net.*;
import java.io.*;


public class Member  implements ActionListener{
    JTextField text;
     static JPanel a1;
    static Box verticle =Box.createVerticalBox();
     static DataOutputStream dout;
     static JFrame f=new JFrame();
    Member(){
        f.setLayout(null);
        JPanel p1=new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,60);
        p1.setLayout(null);
        f.add(p1);
        ImageIcon i20 = new  ImageIcon(ClassLoader.getSystemResource("3.png"));
        Image i21=i20.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        ImageIcon i3 =new ImageIcon(i21);
        JLabel back = new JLabel( i3);
        back.setBounds(5,20,25,25);
           p1.add(back);
           back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
           });

           ImageIcon i4 = new  ImageIcon(ClassLoader.getSystemResource("2.png"));
           Image i5=i4.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
           ImageIcon i6 =new ImageIcon(i5);
           JLabel profile = new JLabel( i6);
           profile.setBounds(40,10,50,50);
              p1.add(profile);


           ImageIcon i7 = new  ImageIcon(ClassLoader.getSystemResource("video.png"));
           Image i8=i7.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
           ImageIcon i9 =new ImageIcon(i8);
           JLabel video = new JLabel( i9);
          video.setBounds(300,20,30,30);
              p1.add(video);


              ImageIcon i10 = new  ImageIcon(ClassLoader.getSystemResource("phone.png"));
              Image i11=i10.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
              ImageIcon i12 =new ImageIcon(i11);
              JLabel phone= new JLabel( i12);
             phone.setBounds(360,20,30,30);
                 p1.add(phone);




                 ImageIcon i13 = new  ImageIcon(ClassLoader.getSystemResource("dot.png"));
                 Image i14=i13.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
                 ImageIcon i15 =new ImageIcon(i14);
                 JLabel dd= new JLabel( i15);
                dd.setBounds(400,20,10,30);
                    p1.add(dd);


                    JLabel name =new  JLabel("Abhinav2");
                    name.setBounds(110,15,100,18);
                    name.setForeground(Color.white);
                    name.setFont(new  Font( "SAN_SERIF" ,Font.BOLD, 20));
                    p1.add(name);

 a1=new JPanel();
a1.setBounds(5,75,440,550);
f.add(a1);

 text =new JTextField();
text.setBounds(5,630,310,40);
text.setFont(new Font("SANS_SERIF",Font.PLAIN,16));
f.add(text);


 JButton send=new JButton("send");
 send.setBounds(320,630,150,40);
 send.setBackground(new Color(7,94,84));
 text.setFont(new Font("SANS_SERIF",Font.PLAIN,16));
 send.addActionListener(this);
 send.setForeground(Color.white);
 f.add(send);







       f. setSize(450,690 );
       f. setLocation(800,0);
       f. setUndecorated(true);
       f. getContentPane().setBackground(Color.white);
       
       f. setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try{
String out=text.getText();
// JLabel  output=new JLabel(out);
// JPanel p2=new JPanel();
JPanel p2=formatLabel(out);
// p2.add(output);

// System.out.println(out);
a1.setLayout(new BorderLayout());
JPanel right =new JPanel(new BorderLayout());
right.add(p2,BorderLayout.LINE_END);
verticle.add(right);
verticle.add(Box.createVerticalStrut(15));
a1.add(verticle,BorderLayout.PAGE_START);

dout.writeUTF(out);
text.setText("");
f.repaint();
f.invalidate();
f.validate();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
public static JPanel formatLabel(String out) {
    JPanel panel=new JPanel();
    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
    JLabel output =new JLabel("<html> <p style=\"width:150px\">"+ out +" </p></html>");
    output.setFont(new Font("Tahoma", Font.PLAIN,16));
    output.setBackground(new Color(37,211,102));
    panel.add(output);
    output.setOpaque(true);
    output.setBorder(new EmptyBorder(15, 15, 15, 50));


    Calendar cal= Calendar.getInstance();
    SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
    JLabel time=new JLabel();
    time.setText(sdf.format(cal.getTime()));
    panel.add(time);
    return panel;
    
}

    public static void main(String[] args){
        new Member();
        try{
Socket s =new Socket("127.0.0.1",6001);
DataInputStream din=new DataInputStream(s.getInputStream());
dout =new DataOutputStream(s.getOutputStream());

while(true){
    a1.setLayout(new BorderLayout() );
    String msg=din.readUTF();
    JPanel panel=formatLabel(msg);
    JPanel right =new JPanel(new BorderLayout());
    right.add(panel, BorderLayout.LINE_START);
    verticle.add(right);
    verticle.add(Box.createVerticalStrut(15));
    a1.add(verticle , BorderLayout.PAGE_START);
    f.validate();
    s.close();
}

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}