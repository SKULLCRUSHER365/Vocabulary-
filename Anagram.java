import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Anagram {
	public static String[] array = new String[33151];


	public static void main(String[] args)throws FileNotFoundException,NotSpaceException
	{
		//storing the voculabary file 
		File file = new File("vocabulary.txt");
		
		Scanner sc = new Scanner(file);
        int vsize = sc.nextInt();
		for(int i=0;i<vsize;i++)
		{
            String s= sc.nextLine();
            store(s);
        }
        sc.close();


        File file2 = new File("input.txt");
		Scanner obj = new Scanner(file2);
		int isize = obj.nextInt();
		permute[] var= new permute[isize];
		for(int i =0;i<isize;i++)
		{
			String s = obj.nextLine();
			var[i]= new permute();
			
			var[i].permutations(s+" ","");
			var[i].setpermutations();

			for(int test=0;test<var[i].j;test++)
			{
				String temp = var[i].last[test];
				int start=0;
				boolean exist=true;

				for(int k=0;k<temp.length();k++)
				{
					if(temp.charAt(k)== ' '&&k!=temp.length())
					{
						String temp3 = temp.substring(start,k-1);
						if(myfind(temp3,hash(temp3),0)==false)
						{
							exist=false;
							start=k;
							break;
						}
					}
				}
				if(start==0){
					if(myfind(temp,hash(temp),0)==false){
						exist=false;
						
					}
				}
				if(exist==true){
					System.out.println(temp);
				}
			}

		}
		obj.close();
	
}



	public static void store(String s)throws NotSpaceException{
		int hash=hash(s)%33151;
		if(array[hash]==null){
			array[hash]=s;
		}
		else{
			probe(hash,s,1);
		}


	}

	public static void probe(int hash,String s,int i)throws NotSpaceException{
		if(i==(33154/2)){
			throw new NotSpaceException("probing failed");
		}
		if(array[(hash+(i*i))%33151]==null)
			array[(hash+(i*i))%33151]=s;
		else
			probe(hash,s,i+1);

	}
	public static  int hash(String s){
		long hash=0;
		long p=1;
		for(int i=0;i<s.length();i++){
			hash=hash + s.charAt(i)*p;
			p=(p*31)%33151;
		}
		int x= (int)(hash%33151);
		return x;
	}

	public static boolean myfind(String s,int hash,int i){
		if(array[hash+(i*i)]==null)
			return false;
		else if(array[(hash+(i*i))%33151]==s)
			return true;
		else
			return myfind(s,hash+(i*i),i+1);

	}




	
}

class NotSpaceException extends Exception{
	public NotSpaceException(String s){
		super(s);
	}



}



class permute{
	public int m=0;
	public String[] temp=new String[10000];
	public String[] last=new String[10000];
	public int j=0;
	public void permutations(String s,String ans){
		if (s.length()== 0) {
			
            temp[m]=ans;
            m++;
            return;
        }
        for (int i=0;i<s.length(); i++) {

            char ch = s.charAt(i);
            String temp = s.substring(0, i) + s.substring(i + 1);
            permutations(temp, ans + ch);
        }
	}


	public void setpermutations(){
		for(int i=0;i<m;i++){
			
			if(temp[i].charAt(0)==' '){
				String temp2=temp[i].substring(1);
				temp[i]=temp2;
			}
			if(temp[i].charAt(0)==' '){
				String temp2=temp[i].substring(1);
				temp[i]=temp2;
			}
			if(temp[i].charAt(temp[i].length()-1)==' '){
				String temp2=temp[i].substring(0,temp[i].length()-2);
				temp[i]=temp2;
			}
			if(temp[i].charAt(temp[i].length()-1)==' '){
				String temp2=temp[i].substring(0,temp[i].length()-2);
				temp[i]=temp2;
			}
			for(int lol=0;lol<temp[i].length();lol++)
          	{
               if(temp[i].charAt(lol)==' ')
               {
                    if(temp[i].charAt(lol+1)==' ')
                    {
                         temp[i]=temp[i].substring(0,lol)+temp[i].substring(lol+2);
                    }
                    break;
               }
          	}
		}
		
		for(int i=1;i<14*14;i++){
			if(j==0){
				last[j]=temp[0];
				j++;
			}
			else{
				boolean s=true;
				for(int k=0;k<j;k++){
					if(temp[i]==last[j]){
						s=false;
					}
				}
				if(s==true){
					last[j]=temp[i];
					j++;
				}

			}
		}
	}
}


	

