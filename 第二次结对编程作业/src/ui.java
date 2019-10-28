//排行榜不是实时更新的，而是过一段时间才会变一次，小心!!!!!!!!
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;
public class ui
{
	static String i,t,a;static int h;static JPanel p3;static int j;static JFrame f3;
	static JLabel l2,l3;static JButton b3;static boolean x;static Thread y;
	public static void main(String[]S)
	{
		h=Toolkit.getDefaultToolkit().getScreenSize().height;
		JFrame f=new JFrame("登录注册");
		f.setBounds(0,h/2-125,250,250);
		//必须把默认的布局管理器去掉，否则只会显示最后一个加进来的组件，小心!!!!!!!!
		f.setLayout(null);
		//设置关闭窗口时结束程序，这样可以防止运行程序后删不掉程序，记方法!!!!!!!!
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel l=new JLabel("账号：");f.add(l);l.setBounds(0,0,50,50);l.setHorizontalAlignment(JTextField.RIGHT);
		l=new JLabel("密码：");f.add(l);l.setBounds(0,50,50,50);l.setHorizontalAlignment(JTextField.RIGHT);
		JTextField b=new JTextField();b.setBounds(50,0,200,50);f.add(b);
		JPasswordField p=new JPasswordField();p.setBounds(50,50,200,50);f.add(p);
		JButton b2=new JButton("登录");b2.setBounds(0,100,250,50);f.add(b2);
		b2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//System.out.println(u2s("http://api.revth.com/auth/login","{\"username\":\"ccx\",\"password\":\"ccx\"}"));
				Matcher m=Pattern.compile("user_id\":(.*?),\"token\":\"(.*)?\"")
						.matcher(u2s(a="http://api.revth.com/auth/login","{\"username\":\""+b.getText()+"\",\"password\":\""+new String(p.getPassword())+"\"}"));
						//这个每次都会变化，小心!!!!!!!!
						//.matcher("{\"status\":0,\"data\":{\"user_id\":16,\"token\":\"0eada629-53ce-4fc5-acd2-678dd2cd2355\"}}");
						//.matcher("{\"status\":0,\"data\":{\"user_id\":16,\"token\":\"f1f3fdf9-21d7-46c6-8d18-8c4a5dd98fcf\"}}");
				if(!m.find())JOptionPane.showMessageDialog(null,"账号或密码错误！",null,0);
				else
				{
					i=m.group(1);t=m.group(2);
					f3=new JFrame();
					f3.setBounds(250,h/2-350,400,700);
					f3.setLayout(null);
					p3=new JPanel();
					p3.setLayout(new GridLayout(0,1));
					JScrollPane s=new JScrollPane(p3);
					s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					s.setBounds(0,0,380,550);
					f3.add(s);f(0);
					JTextField t=new JTextField();f3.add(t);t.setBounds(0,550,200,50);t.setText("0");
					JButton b=new JButton("跳到该页");f3.add(b);b.setBounds(200,550,200,50);
					b.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							if(!t.getText().matches("[0-9]+"))JOptionPane.showMessageDialog(null,"请输入有效的数字！",null,0);
							else f(Integer.valueOf(t.getText()));
						}
					});
					b=new JButton("上一页");f3.add(b);b.setBounds(0,600,200,50);
					b.addActionListener(new ActionListener()
					{public void actionPerformed(ActionEvent e){f(j-1);}});
					b=new JButton("下一页");f3.add(b);b.setBounds(200,600,200,50);
					b.addActionListener(new ActionListener()
					{public void actionPerformed(ActionEvent e){f(j+1);}});
					f3.setVisible(true);
					JFrame f=new JFrame("排行榜");
					f.setBounds(650,h/2-300,250,600);
					JPanel p=new JPanel();p.setLayout(new GridLayout(0,1));
					s=new JScrollPane(p);
					s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					f.getContentPane().add(s);
					p.add(l2=new JLabel());g();f.setVisible(true);
					f=new JFrame("开启战局");
					f.setBounds(900,h/2-125,300,250);
					f.setLayout(null);
					b=new JButton("开启战局");b.setBounds(0,0,300,50);f.add(b);
					b.addActionListener(new ActionListener()
					{public void actionPerformed(ActionEvent e){h();f(0);g();}});
					String[]u={"自动开启战局（只刷新部分UI）","停止并刷新全部UI"};
					b3=new JButton(u[0]);
					b3.setBounds(0,50,300,50);f.add(b3);
					l3=new JLabel();f.add(l3);l3.setBounds(0,100,300,100);
					b3.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							if(!x)
							{
								x=true;b3.setText(u[1]);
								y=new Thread(new Runnable()
								{
									public void run()
									{
										for(;x;)
										{
											h();
										}
									}
								});
								y.start();
							}
							else
							{
								x=false;b3.setText(u[0]);f(0);g();
							}
						}
					});
					f.setVisible(true);
				}
			}
		});
		b2=new JButton("注册");b2.setBounds(0,150,250,50);f.add(b2);
		b2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame f=new JFrame("注册");
				f.setBounds(0,h/2+125,250,300);
				//必须把默认的布局管理器去掉，否则只会显示最后一个加进来的组件，小心!!!!!!!!
				f.setLayout(null);
				JLabel l=new JLabel("账号：");f.add(l);l.setBounds(0,0,80,50);l.setHorizontalAlignment(JTextField.RIGHT);
				l=new JLabel("密码：");f.add(l);l.setBounds(0,50,80,50);l.setHorizontalAlignment(JTextField.RIGHT);
				l=new JLabel("学号：");f.add(l);l.setBounds(0,100,80,50);l.setHorizontalAlignment(JTextField.RIGHT);
				l=new JLabel("教务处密码：");f.add(l);l.setBounds(0,150,80,50);l.setHorizontalAlignment(JTextField.RIGHT);
				JTextField b=new JTextField();b.setBounds(80,0,170,50);f.add(b);
				JPasswordField p=new JPasswordField();p.setBounds(80,50,170,50);f.add(p);
				JTextField b2=new JTextField();b2.setBounds(80,100,170,50);f.add(b2);
				JPasswordField p2=new JPasswordField();p2.setBounds(80,150,170,50);f.add(p2);
				JButton b3=new JButton("注册");b3.setBounds(0,200,250,50);f.add(b3);
				b3.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//System.out.println(u2s("http://api.revth.com/auth/register2","{\"username\":\""+b.getText()+"\",\"password\":\""+new String(p.getPassword())+"\""+",\"student_number\":\""+b2.getText()+"\",\"student_password\":\""+new String(p2.getPassword())+"\"}"));
						if(u2s("http://api.revth.com/auth/register2","{\"username\":\""+b.getText()+"\",\"password\":\""+new String(p.getPassword())+"\""
								+",\"student_number\":\""+b2.getText()+"\",\"student_password\":\""+new String(p2.getPassword())+"\"}").matches("\\{\"status\":0.*"))
									JOptionPane.showMessageDialog(null,"注册成功！",null,1);
						else JOptionPane.showMessageDialog(null
								,"用户名已被使用或学号已绑定或学号错误或教务处密码错误！",null,0);
					}
				});
				f.setVisible(true);
			}
		});
		//显示窗口必须放在最后面，不然前面的组件就不会显示，小心!!!!!!!!
		f.setVisible(true);
	}
	static void h()
	{try{
		String s=u2s2("http://api.revth.com/game/open"),u[];
		//System.out.println(s);
		//{"status":0,"data":{"id":1727,"card":"*Q &8 $4 #K $K *J &6 &K #3 $3 *A &J &9"}}
		//id":..,"card":".."
		Matcher m=Pattern.compile("id\":(.*?),\"card\":\"(.*?)\"").matcher(s);
		if(m.find())
		{
			u=ai.f(m.group(2));
			//不出牌会扣分，小心!!!!!!!!!
			s=u2s("http://api.revth.com/game/submit","{\"id\": "+m.group(1)+",\"card\":[\""+u[0]+"\",\""+u[1]+"\",\""+u[2]+"\"]}");
			//System.out.println(u[0]+"\n"+u[1]+"\n"+u[2]+"\n"+s);
			l3.setText(s="<html>"+u[0]+"<br>"+u[1]+"<br>"+u[2]+"<br>"+s+"</html>");
			if(!s.matches(".*status\":0.*"))System.out.println(s);
		}
		else if(s.matches(".*status\":2001.*"))
		{
			//System.out.println(s);
			l3.setText("<html>"+s+"<br>未结束战局过多，所以等下再自动开始</html>");
			Thread.sleep(2000);
		}
		else
		{
			m=Pattern.compile("token\":\"(.*)?\"").matcher(u2s(a));
			//这个每次都会变化，小心!!!!!!!!
			//.matcher("{\"status\":0,\"data\":{\"user_id\":16,\"token\":\"0eada629-53ce-4fc5-acd2-678dd2cd2355\"}}");
			//.matcher("{\"status\":0,\"data\":{\"user_id\":16,\"token\":\"f1f3fdf9-21d7-46c6-8d18-8c4a5dd98fcf\"}}");
			t=m.group(1);
		}
	}catch(Exception e){e.printStackTrace();}}
	static void g()
	{
		//System.out.println(u2s("http://api.revth.com/rank"));
		//player_id":..,"score":..,"name":".."
		Matcher m=Pattern.compile("player_id\":(.*?),\"score\":(.*?),\"name\":\"(.*?)\"")
			.matcher(u2s("http://api.revth.com/rank"));
		//.matcher("[{\"player_id\":18,\"score\":2470,\"name\":\"SheepHuan\"},{\"player_id\":3,\"score\":359,\"name\":\"test2\"},{\"player_id\":2,\"score\":161,\"name\":\"test1\"},{\"player_id\":7,\"score\":102,\"name\":\"EasonXu\"},{\"player_id\":1,\"score\":91,\"name\":\"test\"},{\"player_id\":22,\"score\":15,\"name\":\"tjl\"},{\"player_id\":15,\"score\":3,\"name\":\"lww\"},{\"player_id\":6,\"score\":0,\"name\":\"cth\"},{\"player_id\":9,\"score\":0,\"name\":\"rain\"},{\"player_id\":16,\"score\":0,\"name\":\"ccx\"},{\"player_id\":24,\"score\":0,\"name\":\"crj\"},{\"player_id\":28,\"score\":0,\"name\":\"Monster\"},{\"player_id\":17,\"score\":0,\"name\":\"cjj\"},{\"player_id\":29,\"score\":0,\"name\":\"bbr\"},{\"player_id\":19,\"score\":0,\"name\":\"yanlin\"},{\"player_id\":12,\"score\":0,\"name\":\"AAA\"},{\"player_id\":13,\"score\":0,\"name\":\"Richer230\"},{\"player_id\":14,\"score\":0,\"name\":\"qingke1314\"},{\"player_id\":26,\"score\":0,\"name\":\"wersat\"},{\"player_id\":21,\"score\":0,\"name\":\"lakuji\"},{\"player_id\":25,\"score\":0,\"name\":\"sbrg\"},{\"player_id\":10,\"score\":0,\"name\":\"六六六花\"},{\"player_id\":30,\"score\":0,\"name\":\"ShenHX\"}]");
		int j=1;String s="",u,v="";
		for(;m.find();)
		{
			s+=u="第"+j+++"名：<br>玩家ID："+m.group(1)+"<br>分数："+m.group(2)+"<br>玩家名："+m.group(3)+"<pre>\n</pre>";
			//第60名：<br>玩家ID：16<br>分数：0<br>玩家名：ccx<pre>
			//</pre>
			if(u.matches("(?s).*玩家ID："+i+"<br>.*"))v=u;
		}
		//System.out.println(s);
		l2.setText("<html>我的信息：<br>"+v+s+"</html>");
	}
	static void f(int k)
	{
		j=k;f3.setTitle("往期对战结果：第"+j+"页：");
		p3.removeAll();
		//System.out.println(u2s("http://api.revth.com/history?player_id="+i+"&limit=100000000&page=0"));
		//id":..,"card":[".."],"score":..,"timestamp":..}
		Matcher m=Pattern.compile("id\":(.*?),\"card\":\\[\"(.*?)\"\\],\"score\":(.*?),\"timestamp\":(.*?)}")
			//每页最多只能20个，小心!!!!!!
			.matcher(u2s("http://api.revth.com/history?player_id="+i+"&limit=10&page="+j));
		//.matcher("{\"status\":0,\"data\":[{\"id\":1723,\"card\":[\"$3 #2 *4 *9 #K *6 *J $10 &Q $6 &10 *Q *A\"],\"score\":0,\"timestamp\":1571721890},{\"id\":1715,\"card\":[\"&J #9 *7 *Q #4 #2 &7 &10 $9 &4 #A *A &3\"],\"score\":-18,\"timestamp\":1571721810},{\"id\":1714,\"card\":[\"&Q $7 *10 &A #3 &7 *4 &5 $4 #5 &9 *A #4\"],\"score\":-12,\"timestamp\":1571721660},{\"id\":1713,\"card\":[\"*8 $8 &A *K *6 $6 *10 $A *A &9 &K *J *5\"],\"score\":-20,\"timestamp\":1571721600},{\"id\":1712,\"card\":[\"$10 #Q &K #6 &A *6 $J *7 #8 #3 #4 *J #5\"],\"score\":-12,\"timestamp\":1571721430},{\"id\":1711,\"card\":[\"$K #5 *K *A #10 $Q *J *5 #3 #9 &5 $5 *4\"],\"score\":-12,\"timestamp\":1571721380},{\"id\":1710,\"card\":[\"*K *5 $2 &A &9 *10 $7 *3 #5 #3 &3 *A &4\"],\"score\":-18,\"timestamp\":1571721280},{\"id\":685,\"card\":[\"#A &8 $10 #6 &3 $6 #8 *A #9 #Q $8 $J #3\"],\"score\":-18,\"timestamp\":1571660860},{\"id\":627,\"card\":[\"#9 #J $4 $6 #4 #10 $5 $2 &Q #5 $8 *9 #Q\"],\"score\":-18,\"timestamp\":1571660360},{\"id\":626,\"card\":[\"&5 $4 #8 $J #9 *J #6 &J *10 $9 &7 &3 *3\"],\"score\":-18,\"timestamp\":1571660350},{\"id\":629,\"card\":[\"*4 #J #10 #Q $5 $J *6 #7 $10 #3 $6 $Q &6\"],\"score\":-18,\"timestamp\":1571660230},{\"id\":625,\"card\":[\"$4 $J &J *9 &6 $6 #6 &5 $7 &7 $10 *4 *2\"],\"score\":-18,\"timestamp\":1571660210},{\"id\":637,\"card\":[\"*3 $10 $8 &6 *6 #J $7 $K *K $5 $6 &Q *9\"],\"score\":-12,\"timestamp\":1571660190},{\"id\":617,\"card\":[\"&2 $5 $6 *2 *5 &9 $10 *6 &5 $A &Q *10 $8\"],\"score\":-18,\"timestamp\":1571659850},{\"id\":581,\"card\":[\"&3 *Q &Q *J &5 $5 #10 #3 &2 $2 #6 $6 #5\"],\"score\":-18,\"timestamp\":1571659780},{\"id\":585,\"card\":[\"&4 *4 #A *6 *J #5 $Q $9 $4 &K #K #4 &10\"],\"score\":-18,\"timestamp\":1571659770},{\"id\":583,\"card\":[\"$9 &7 *J $4 $5 &J #Q #8 &10 #A &8 &4 #3\"],\"score\":-18,\"timestamp\":1571659750},{\"id\":541,\"card\":[\"#2 #K *8 *Q $8 $10 $7 *5 #A &4 &8 #6 *3\"],\"score\":-12,\"timestamp\":1571657920},{\"id\":540,\"card\":[\"$8 *4 *7 $Q &2 &4 *2 #4 *10 *3 $9 &J #2\"],\"score\":-18,\"timestamp\":1571657910},{\"id\":539,\"card\":[\"$3 #K *Q &A #2 $J #5 &J &K $7 $A $9 $2\"],\"score\":-18,\"timestamp\":1571657780}]}");
		JButton b;
		for(;m.find();)
		{
			String i=m.group(1);
			//System.out.println(i);
			b=new JButton("<html>战局ID："+i+"<br>出牌情况："+m.group(2)+"<br>分数变化："+m.group(3)+"<br>结算时间："+m.group(4)+"</html>");p3.add(b);
			b.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JFrame f=new JFrame("第"+i+"场对战的详细结果");
					f.setBounds(1150,h/2-300,400,600);
					String s=u2s("http://api.revth.com/history/"+i),t;
					//String s="{\"status\":0,\"data\":{\"id\":1723,\"timestamp\":1571722210,\"detail\":[{\"player_id\":16,\"name\":\"ccx\",\"score\":-12,\"card\":[\"$3 #2 *4 *9 #K *6 *J $10 &Q $6 &10 *Q *A\"]},{\"player_id\":1,\"name\":\"test\",\"score\":13,\"card\":[\"&K &A #A\",\"$5 *8 $8 &9 #9\",\"$4 &4 #4 &7 *7\"]},{\"player_id\":3,\"name\":\"test2\",\"score\":11,\"card\":[\"*2 &6 $A\",\"*10 #10 &J #J *K\",\"*3 &3 #3 &5 #8\"]},{\"player_id\":29,\"name\":\"bbr\",\"score\":-12,\"card\":[\"&2 #6 $2 $7 &8 $K *5 #5 $J $Q #Q #7 $9\"]}]}}",t;
					//System.out.println(s);
					//timestamp":..,
					Matcher m=Pattern.compile("timestamp\":(.*?),").matcher(s);
					if(m.find())
					{
						t="<html>战局ID："+i+"<br>结算时间："+m.group(1);
						//player_id":..,"name":"..","score":..,"card":["..","..",".."
						m=Pattern.compile("player_id\":(.*?),\"name\":\"(.*?)\",\"score\":(.*?),\"card\":\\[\"(.{38,}?)\"").matcher(s);
						for(;m.find();)t+="<br>玩家ID："+m.group(1)+"<br>玩家名："+m.group(2)+"<br>分数变化："+m.group(3)+"<br>出牌情况："+m.group(4);
						JLabel l=new JLabel(t+"</html>");
						f.add(l);
						f.setVisible(true);
					}
					else JOptionPane.showMessageDialog(null,"这个战局还没有结束！",null,0);
				}
			});
		}
		p3.updateUI();
	}
	static String u2s(String u,String v)
	{String s="";try{
		HttpURLConnection c=(HttpURLConnection)new URL(u).openConnection();
		c.setDoOutput(true);c.setRequestProperty("Content-Type","application/json");
		c.setRequestProperty("X-Auth-Token",t);
		PrintStream o=new PrintStream(c.getOutputStream());o.print(v);o.close();
		InputStream i;if(c.getResponseCode()==200)i=c.getInputStream();else i=c.getErrorStream();
		byte[]b=new byte[8192];int l;
		for(;(l=i.read(b))!=-1;)s+=new String(b,0,l,"utf8");
		i.close();
	}catch (IOException e){e.printStackTrace(); }return s;}
	static String u2s(String u)
	{String s="";try{
		HttpURLConnection c=(HttpURLConnection)new URL(u).openConnection();
		c.setRequestProperty("X-Auth-Token",t);
		InputStream i;if(c.getResponseCode()==200)i=c.getInputStream();else i=c.getErrorStream();
		byte[]b=new byte[8192];int l;
		//必须加"utf8"，否则转成exe后会乱码，小心!!!!!!!!
		for(;(l=i.read(b))!=-1;)s+=new String(b,0,l,"utf8");
		i.close();
	}catch(Exception e){e.printStackTrace();}return s;}
	static String u2s2(String u){return u2s(u,"");}
}
