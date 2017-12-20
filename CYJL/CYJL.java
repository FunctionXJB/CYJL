import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.InputStream;

public class CYJL{
	public static void main(String[] args){
		System.out.print("��������");
		Scanner in=new Scanner(System.in);
		while(in.hasNext()){
			String input=in.nextLine();
			String inPY=new readTXT().readInTXT(input);
			ArrayList<String> output=new readTXT().readoutTXT(inPY);
			
			for(int i=0;i<output.size()&&i<10;i++)
				System.out.println(output.get(i));
			setSysClipboardText(output.get((int)(Math.random()*output.size())));
			
			System.out.print("��������");
		}
		in.close();
	}
	
    public static void setSysClipboardText(String writeMe) {  
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();  
        Transferable tText = new StringSelection(writeMe);  
        clip.setContents(tText, null);  
    }
}

class readTXT{
	public String readInTXT(String input){
		String output="";
		try { // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw  

            /* ����TXT�ļ� */  
			InputStream is=this.getClass().getResourceAsStream("/CY_database.txt");   
	        BufferedReader br=new BufferedReader(new InputStreamReader(is));
	        String line = "";  
            line = br.readLine();
            while (line != null) {  
                line = br.readLine(); // һ�ζ���һ������  
                String[] str=line.split(";");
                if(input.equals(str[0])){
                	String[] tmp=str[1].split("  ");
                	output=tmp[tmp.length-1];
                	break;
                }
                output="δ�ҵ�������";
            }
            br.close();
        } catch (Exception e) {  
            output="���ݿ��ȡ����";  
        }

		return output;
	}
	
	public ArrayList<String> readoutTXT(String inPY){
		ArrayList<String> output=new ArrayList<>();
		int flag=0;
		try { // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw  

            /* ����TXT�ļ� */  
			InputStream is=this.getClass().getResourceAsStream("/CY_database.txt");   
	        BufferedReader br=new BufferedReader(new InputStreamReader(is)); 
	        String line = "";  
            line = br.readLine();
            while (line != null) {  
                line = br.readLine(); // һ�ζ���һ������  
                String[] str=line.split(";");
                String[] tmp=str[1].split("  ");
                if(tmp[0].equals(inPY)){
                	flag=1;
                	output.add(str[0]);
                }
                else if(flag==1)break;
            }
            if(output.size()==0)
            	output.add("δ�ҵ����ʳ��");
            br.close();
        } catch (Exception e) { 
        	if(output.size()==0)
        		output.add("���ݿ��ȡ����");  
        }

		return output;
	}
}