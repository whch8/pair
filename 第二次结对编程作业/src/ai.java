import java.util.*;
public class ai
{
	static String[]u=new String[5];
	static int[][]a=new int[13][2],b=new int[13][2];
	static boolean d;
	static List<Integer>y[],z[];
	static int x;
	static String[]f(String s)
	{
		String t[]=s.split(" ");
		int i,j,k,l,g;
		for(i=0;i<13;i++)
		{
			b[i][0]=a[i][0]=t[i].charAt(0);
			b[i][1]=a[i][1]=g(t[i].substring(1));
		}
		Arrays.sort(a,new Comparator<int[]>()
		{
			public int compare(int[]i,int[]j)
			{
				if(i[1]==j[1])return i[0]-j[0];
				return i[1]-j[1];
			}
		});
		Arrays.sort(b,new Comparator<int[]>()
		{
			public int compare(int[]i,int[]j)
			{
				if(i[0]==j[0])return i[1]-j[1];
				return i[0]-j[0];
			}
		});
		for(i=0;i<13&&a[i][1]==i+2;i++);
		if(i==13)
		{
			for(j=a[0][0],i=1;i<13&&a[i][0]==j;i++);
			if(i==13)return h("至尊清龙");
			return h("一条龙");
		}
		for(i=1;i<13&&a[i][1]>10;i++);
		if(i==13)return h("十二皇族");
		for(i=0;i<13&&b[i][1]==b[0][1]+i&&b[i][0]==b[0][0];i++);
		for(j=i;j<13&&b[j][1]==b[i][1]+j-i&&b[j][0]==b[i][0];j++);
		if(j==13&&(i==3||i==5||i==8||i==10))return h("三同花顺");
		for(k=j;k<13&&b[k][1]==b[j][1]+k-j&&b[k][0]==b[j][0];k++);
		if(k==13&&(j==8&&(i==3||i==5)||j==10&&i==5))return h("三同花顺");
		for(i=0;i<13&&a[i][1]==a[0][1];i++);
		for(j=i;j<13&&a[j][1]==a[i][1];j++);
		for(k=j;k<13&&a[k][1]==a[j][1];k++);
		for(l=k;l<13&&a[l][1]==a[k][1];l++);
		if(l==13&&(k==12||k==9&&(j==8||j==5&&(i==4||i==1))))return h("三分天下");
		if(a[0][1]>7)return h("全大");
		if(a[12][1]<9)return h("全小");
		for(i=0;i<13&&b[i][0]==b[0][0];i++);
		for(j=i;j<13&&b[j][0]==b[i][0];j++);
		if(j==13&&(b[0][0]=='#'&&b[i][0]=='*'||b[0][0]=='$'&&b[i][0]=='&'))return h("凑一色");
		if(a(new int[]{1,2,2,2,3,3}))return h("双怪冲三");
		if(a(new int[]{1,3,3,3,3}))return h("四套三条");
		if(a(new int[]{2,2,2,2,2,3}))return h("五对三条");
		if(a(new int[]{1,2,2,2,2,2,2}))return h("六对半");
		List<Integer>c=new LinkedList<>();
		for(int[]d:a)c.add(d[1]);
		d=false;b(c,0);if(d)return h("三顺子");
		for(i=0;i<13&&b[i][0]==b[0][0];i++);
		for(j=i;j<13&&b[j][0]==b[i][0];j++);
		if(j==13&&(i==3||i==5||i==8||i==10))return h("三同花");
		for(k=j;k<13&&b[k][0]==b[j][0];k++);
		if(k==13&&(j==8&&(i==3||i==5)||j==10&&i==5))return h("三同花");
		y=new List[4];z=new List[15];
		for(i=0;i<4;i++)y[i]=new ArrayList<>();
		for(i=2;i<15;i++)z[i]=new ArrayList<>();
		for(i=a.length-1;i>=0;i--){k=c(a[i][0]);j=4*a[i][1]+k;y[k].add(j);z[a[i][1]].add(j);}
		u=new String[6];
		i:for(x=2;x>=0;x--)
		{
			if(x!=0)
			{
				for(i=j=0;i<4;i++)if(y[i].size()>4)for(l=0;l<y[i].size()-4;l++)
				{
					for(k=1;k<5&&y[i].get(l+k-1)-4==y[i].get(l+k);k++);
					if(k==5&&j<y[i].get(l))j=y[i].get(l);
				}
				if(j!=0)
				{
					d(new int[]{j,j-4,j-8,j-12,j-16},"同花顺");
					continue i;
				}
				for(i=14;i>1;i--)if(z[i].size()==4)
				{
					for(j=2;j<15&&z[j].size()!=1;j++);
					if(j==15)for(j=2;j<15&&z[j].size()!=2;j++);
					if(j==15)for(j=2;j<15&&z[j].size()!=3;j++);
					d(new int[]{z[i].get(3),z[i].get(2),z[i].get(1),z[i].get(0),z[j].get(z[j].size()-1)},"炸弹");
					continue i;
				}
				for(i=14;i>1;i--)if(z[i].size()==3)
				{
					//不可能有6张相同的牌，所以葫芦中的对子越小越好，小心!!!!!!!!!
					for(j=2;j<15;j++)if(z[j].size()==2)
					{
						d(new int[]{z[i].get(2),z[i].get(1),z[i].get(0),z[j].get(1),z[j].get(0)},"葫芦");
						continue i;
					}
					for(j=2;j<i;j++)if(z[j].size()==3)
					{
						d(new int[]{z[i].get(2),z[i].get(1),z[i].get(0),z[j].get(2),z[j].get(1)},"葫芦");
						continue i;
					}
				}
				for(i=k=0,j=-1;i<4;i++)if(y[i].size()>4)
				{
					l=(((y[i].get(0)/4*15+y[i].get(1)/4)*15+y[i].get(2)/4)*15+y[i].get(3)/4)*15+y[i].get(4)/4;
					if(k<l){k=l;j=i;}
					
				}
				if(j!=-1)
				{
					d(new int[]{y[j].get(0),y[j].get(1),y[j].get(2),y[j].get(3),y[j].get(4)},"同花");
					continue i;
				}
				for(i=14;i>5;i--)
				{
					for(j=i;j>i-5&&z[j].size()>0;j--);
					if(j==i-5)
					{
						d(new int[]{z[i].get(0),z[i-1].get(0),z[i-2].get(0),z[i-3].get(0),z[i-4].get(0)},"顺子");
						continue i;
					}
				}
			}
			for(i=14;i>1;i--)if(z[i].size()==3)
			{
				if(x!=0)
				{
					for(j=2;z[j].size()!=1;j++);
					for(k=j+1;z[k].size()!=1;k++);
					d(new int[]{z[i].get(2),z[i].get(1),z[i].get(0),z[k].get(0),z[j].get(0)},"三条");
				}
				else d(new int[]{z[i].get(2),z[i].get(1),z[i].get(0)},"三条");
				continue i;
			}
			if(x!=0)
			{
				for(i=14;i>2;i--)if(z[i].size()==2&&z[i-1].size()==2)
				{
					for(k=14;k>1&&z[k].size()!=1;k--);
					if(k==1)for(k=14;k>1&&z[k].size()!=2;k--);
					d(new int[]{z[i].get(1),z[i].get(0),z[i-1].get(1),z[i-1].get(0),z[k].get(0)},"连对");
					continue i;
				}
				for(i=14;i>3;i--)if(z[i].size()==2)
				{
					for(k=14;k>1&&z[k].size()!=1;k--);
					if(k==1)for(k=14;k>1&&z[k].size()!=2;k--);
					for(j=i-2;j>1;j--)if(z[j].size()==2){d(new int[]{z[i].get(1),z[i].get(0),z[j].get(1),z[j].get(0),z[k].get(0)},"二对");continue i;}
				}
			}
			for(i=14;i>1;i--)if(z[i].size()==2)
			{
				for(j=14;z[j].size()!=1;j--);
				if(x!=0)
				{
					for(k=j-1;z[k].size()!=1;k--);
					for(l=k-1;z[l].size()!=1;l--);
					d(new int[]{z[i].get(1),z[i].get(0),z[j].get(0),z[k].get(0),z[l].get(0)},"对子");
				}
				else d(new int[]{z[i].get(1),z[i].get(0),z[j].get(0)},"对子");
				continue i;
			}
			for(i=14;i>1;i--)if(z[i].size()==1)
			{
				for(j=i-1;z[j].size()!=1;j--);
				for(k=j-1;z[k].size()!=1;k--);
				if(x!=0)
				{
					for(l=k-1;z[l].size()!=1;l--);
					for(g=l-1;z[g].size()!=1;g--);
					d(new int[]{z[i].get(0),z[j].get(0),z[k].get(0),z[l].get(0),z[g].get(0)},"散牌");
				}
				else d(new int[]{z[i].get(0),z[j].get(0),z[k].get(0)},"散牌");
				continue i;
			}
		}
		return u;
	}
	static void d(int[]a,String s)
	{
		u[x+3]=s;s="";int j;Integer k;
		for(int i:a)
		{
			y[i%4].remove(k=i);z[i/4].remove(k);
			j=i%4;
			if(j==0)s+='#';
			else if(j==1)s+='$';
			else if(j==2)s+='&';
			else s+='*';
			j=i/4;
			if(j==11)s+='J';
			else if(j==12)s+='Q';
			else if(j==13)s+='K';
			else if(j==14)s+='A';
			else s+=j;
			s+=" ";
		}
		u[x]=s.substring(0,s.length()-1);
	}
	static int c(int i)
	{
		if(i=='#')return 0;
		if(i=='$')return 1;
		if(i=='&')return 2;
		return 3;
	}
	static void b(List<Integer>l,int j)
	{
		if(d)return;
		if(l.size()==0){d=true;return;}
		int i,k=l.get(0);
		for(i=0;i<j&&l.remove(Integer.valueOf(k+i));i++);
		if(i==j){b(new LinkedList<>(l),3);b(new LinkedList<>(l),5); }
	}
	static int g(String s)
	{
		if(s.equals("J"))return 11;
		if(s.equals("Q"))return 12;
		if(s.equals("K"))return 13;
		if(s.equals("A"))return 14;
		return Integer.valueOf(s);
	}
	static String[]h(String t)
	{
		String s;int i,j,k,l[]={3,8,13};
		for(i=k=0;k<3;k++)
		{
			for(s="";i<l[k];i++)
			{
				s+=(char)a[i][0];
				j=a[i][1];
				if(j==11) s+="J";
				else if(j==12) s+="Q";
				else if(j==13) s+="K";
				else if(j==14) s+="A";
				else s+=j;
				s+=" ";
			}
			u[k]=s.substring(0,s.length()-1);
		}
		for(s="",i=0;i<13;i++)
		{
			s+=(char)a[i][0];j=a[i][1];
			if(j==11)s+="J";
			else if(j==12)s+="Q";
			else if(j==13)s+="K";
			else if(j==14)s+="A";
			else s+=j;
			s+=" ";
		}
		u[4]=t;u[3]=s.substring(0,s.length()-1);return u;
	}
	static boolean a(int[]d)
	{
		List<Integer>c=new LinkedList<>();int i,j,k;
		for(int e:d)c.add(e);
		for(i=k=0;i<d.length;i++)
		{
			for(j=k;k<13&&a[k][1]==a[j][1];k++);
			c.remove(Integer.valueOf(k-j));
		}
		if(c.size()==0)return true;return false;
	}
	public static void main(String[]S)
	{
		int i;String[]a={"*5 *3 *2 *4 *6 *9 *K *10 *7 *J *8 *Q *A"
		,"*7 *8 *9 *10 *J *Q *2 $3 *4 *5 #6 &K &A"
		,"&Q *Q #K $K &K *2 &J *J #Q $Q *K #A $A"
		,"#7 $7 #8 $8 &8 $9 &9 $5 #6 $6 &10 &J &Q"
		,"*2 *9 #Q $Q &Q *Q #K $K &K *K #9 $9 &9"
		,"*Q #K $K &K *8 #8 $9 &9 *9 #Q $Q &Q *K"
		,"#2 *2 $5 &6 *6 $3 &3 *3 #4 #7 $8 &8 *8"
		,"#2 *2 #3 *3 #4 #6 *6 #7 *4 *5 #8 *8 *9"
		,"#2 *2 #8 &8 *8 #9 #3 $3 *3 *4 $9 &A *A"
		,"#2 #9 $9 $2 *2 $3 &3 *3 #8 &8 *8 &9 *A"
		,"#2 $2 $5 &8 *8 *2 $3 &3 #5 #9 $9 &A *A"
		,"#2 $2 *6 &8 *8 #9 $9 $3 &3 #5 $5 &A *A"
		,"*5 #6 $6 &10 #7 $7 #8 $8 &8 $9 &9 &J &Q"
		,"$4 $6 #7 $7 #8 #6 $8 &8 $9 &9 &10 &J &Q"
		,"*2 *3 #4 #5 #J $J #K $7 &8 *8 *9 $A &A"
		,"#J $J &J $8 *2 *3 &4 #5 #6 #7 *9 *Q *A"
		,"*5 #8 *A $3 *8 *9 &8 #A $A &A *10 *J *Q"
		,"*2 *3 #4 #5 $5 $J #K $A &8 *8 *9 #J &A"
		,"#8 $8 *8 #3 #5 $5 &5 &3 $2 *2 $Q &Q *Q"
		,"*5 #6 &6 $Q #A $A &A #8 $9 &10 *J *A $3"
		,"#3 *9 *10 *J *5 &9 $6 *6 #7 $7 &Q *8 *Q"
		,"$4 $5 #7 *2 *3 *6 *K *A &2 &3 &4 &10 &K"
		,"$2 $9 &K #10 $10 &10 $7 &7 &3 #4 #5 #6 *7"
		,"$2 $5 #10 *2 *4 *10 *Q *A &3 &5 &6 &J &A"
		,"$Q #K $8 #2 $A &2 $3 #7 &7 *3 *5 *A &4"
		,"*2 $J $Q &6 $7 *9 &K #J #K &A #7 #8 #A"
		,"$8 &10 #Q *8 *9 *10 #J &Q #4 $4 *4 #6 *6"}
		,j,d[]={{"*2 *3 *4","*5 *6 *7 *8 *9","*10 *J *Q *K *A","*2 *3 *4 *5 *6 *7 *8 *9 *10 *J *Q *K *A","至尊清龙"}
		,{"*2 $3 *4","*5 #6 *7 *8 *9","*10 *J *Q &K &A","*2 $3 *4 *5 #6 *7 *8 *9 *10 *J *Q &K &A","一条龙"}
		,{"*2 &J *J","#Q $Q &Q *Q #K","$K &K *K #A $A","*2 &J *J #Q $Q &Q *Q #K $K &K *K #A $A","十二皇族"}
		,{"$5 #6 $6","#7 $7 #8 $8 &8","$9 &9 &10 &J &Q","$5 #6 $6 #7 $7 #8 $8 &8 $9 &9 &10 &J &Q","三同花顺"}
		,{"*2 #9 $9","&9 *9 #Q $Q &Q","*Q #K $K &K *K","*2 #9 $9 &9 *9 #Q $Q &Q *Q #K $K &K *K","三分天下"}
		,{"#8 *8 $9","&9 *9 #Q $Q &Q","*Q #K $K &K *K","#8 *8 $9 &9 *9 #Q $Q &Q *Q #K $K &K *K","全大"}
		,{"#2 *2 $3","&3 *3 #4 $5 &6","*6 #7 $8 &8 *8","#2 *2 $3 &3 *3 #4 $5 &6 *6 #7 $8 &8 *8","全小"}
		,{"#2 *2 #3","*3 #4 *4 *5 #6","*6 #7 #8 *8 *9","#2 *2 #3 *3 #4 *4 *5 #6 *6 #7 #8 *8 *9","凑一色"}
		,{"#2 *2 #3","$3 *3 *4 #8 &8","*8 #9 $9 &A *A","#2 *2 #3 $3 *3 *4 #8 &8 *8 #9 $9 &A *A","双怪冲三"}
		,{"#2 $2 *2","$3 &3 *3 #8 &8","*8 #9 $9 &9 *A","#2 $2 *2 $3 &3 *3 #8 &8 *8 #9 $9 &9 *A","四套三条"}
		,{"#2 $2 *2","$3 &3 #5 $5 &8","*8 #9 $9 &A *A","#2 $2 *2 $3 &3 #5 $5 &8 *8 #9 $9 &A *A","五对三条"}
		,{"#2 $2 $3","&3 #5 $5 *6 &8","*8 #9 $9 &A *A","#2 $2 $3 &3 #5 $5 *6 &8 *8 #9 $9 &A *A","六对半"}
		,{"*5 #6 $6","#7 $7 #8 $8 &8","$9 &9 &10 &J &Q","*5 #6 $6 #7 $7 #8 $8 &8 $9 &9 &10 &J &Q","三顺子"}
		,{"$4 #6 $6","#7 $7 #8 $8 &8","$9 &9 &10 &J &Q","$4 #6 $6 #7 $7 #8 $8 &8 $9 &9 &10 &J &Q","三同花"}
		,{"#4 *3 *2","&8 *8 *9 $7 #5","$A &A #J $J #K","散牌","对子","二对"}
		,{"#J $J &J","$8 #7 #6 #5 &4","*A *Q *9 *3 *2","三条","顺子","同花"}
		,{"#8 &8 *5","#A $A &A *A $3","*Q *J *10 *9 *8","对子","炸弹","同花顺"}
		,{"#4 *3 *2","&8 *8 #5 $5 *9","$A &A #J $J #K","散牌","二对","二对"}
		,{"#5 $5 &5","#8 $8 *8 #3 &3","$Q &Q *Q $2 *2","三条","葫芦","葫芦"}
		,{"#6 &6 *5","$Q *J &10 $9 #8","#A $A &A *A $3","对子","顺子","炸弹"}
		,{"&9 *5 #3","#7 $7 $6 *6 &Q","*Q *J *10 *9 *8","散牌","连对","同花顺"}
		,{"#7 $5 $4","&K &10 &4 &3 &2","*A *K *6 *3 *2","散牌","同花","同花"}
		,{"&K $9 $2","*7 #6 #5 #4 &3","#10 $10 &10 $7 &7","散牌","顺子","葫芦"}
		,{"#10 $5 $2","&A &J &6 &5 &3","*A *Q *10 *4 *2","散牌","同花","同花"}
		,{"$8 *5 &4","$A *A #7 &7 $Q","$3 *3 #2 &2 #K","散牌","二对","连对"}
		,{"$7 &6 *2","&A &K $Q $J *9","#A #K #J #8 #7","散牌","散牌","同花"}
		,{"#Q &10 $8","&Q #J *10 *9 *8","#4 $4 *4 #6 *6","散牌","顺子","葫芦"}};
		for(i=0;i<d.length;i++)if(!Arrays.equals(d[i],j=f(a[i])))
		{
			System.out.println(i+1+"：\n正解：");
			for(String s:d[i])System.out.println(s);
			System.out.println("\n错解：");
			for(String s:j)System.out.println(s);
			System.out.println("\n\n");
		}
		System.out.println("测试结束，以上几个没通过测试");
	}
}
