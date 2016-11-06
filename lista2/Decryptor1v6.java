import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.BadPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.apache.commons.codec.binary.Base64;
public class Decryptor1v6 {

     public static byte[] toByteArray(String s) {
	    return DatatypeConverter.parseHexBinary(s);
	}
     public static String decrypt(String Sufkey, String initVector, String encrypted){
        try {
             IvParameterSpec iv = new IvParameterSpec(toByteArray(initVector));
             Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
             SecretKeySpec skeySpec = new SecretKeySpec(toByteArray(Sufkey), "AES");
             cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
             byte[] original=cipher.doFinal(Base64.decodeBase64(encrypted));
             return new String(original, "UTF-8");                
         }
       catch (Exception ex) {
            ex.printStackTrace();
        }
     return null;
   }

    public static void main(String[] args) throws FileNotFoundException {
        int argument1,argument2;
        String key="";
        String sufkey="";
        String iv="";
        String kryptogram=""; 
         char[] znaki={' ','"','0','1','2','3','4','5','6','7','8','9','A','a','ą','¹','B','b','C','c','Ć','ć','Æ','æ','D','d','E','e','ę','Ê','ê','F','f','G','g','H','h',
                                  'I','i','J','j','K','k','L','l','£','Ł','ł','³','M','m','N','n','ń','Ñ','ñ','O','o','P','p','R','r','S','s','Ś','ś','Œ','œ',
                                  'T','t','U','u','Ó','ó','Q','q','V','v','W','w','X','x','Y','y','Z','z','Ż','ż','Ź','ź','¯','¿','','Ÿ','(',')','[',']','{','}',
                                  ',','.','-',':','~',';','?','/','%','\'','\0','\n',(char)14};
        argument1=Integer.parseInt(args[0]);
        argument2=Integer.parseInt(args[1]);
        File file1=new File("key.txt");
        File file2=new File("iv.txt");
        File file3=new File("kryptogram.txt");
        Scanner odczyt1= new Scanner(file1);
        while (odczyt1.hasNextLine()) 
        {
         sufkey=sufkey+odczyt1.nextLine();
        }
        odczyt1.close();
        Scanner odczyt2= new Scanner(file2);
        while (odczyt2.hasNextLine()) 
        {
         iv=iv+odczyt2.nextLine();
        }
        odczyt2.close();
        Scanner odczyt3= new Scanner(file3);
        while (odczyt3.hasNextLine()) 
        {
         kryptogram=kryptogram+odczyt3.nextLine();
        }
        odczyt3.close();
        String[] znakikl={"f","e","d","c","b","a","9","8","7","6","5","4","3","2","1","0"};
             int c1,c2,c3,c4,c5,c6,c7,c8;
             int licznik=0;
             int zrobione=0;
             c1=argument1;
             while(c1<=argument2)
             {
              c2=0;
              while(c2<=15)
              {
               licznik++;
               c3=0;
               while(c3<=15)
               {
                c4=0;
                while(c4<=15)
                {
                 c5=0;
                 while(c5<=15)
                 {
                  c6=0;
                  while(c6<=15)
                  {
                   c7=0;
                   while(c7<=15)
                   {
                    c8=0;
                    while(c8<=15)
                    {
                     key=znakikl[c1]+znakikl[c2]+znakikl[c3]+znakikl[c4]+znakikl[c5]+znakikl[c6]+znakikl[c7]+znakikl[c8]+sufkey;
                     String dec=decrypt(key,iv,kryptogram);
                     char[] test=dec.toCharArray();
                    int fail=0;
                    int success=0;
                    int i=0;
                    while((i<test.length-2)&&(fail==0))
                    {
                      fail=1;
                      success=0;
                      int j=0;
                      while((j<znaki.length)&&(success==0))
                      {
                        if(test[i]==znaki[j])
                          success=1;
                        else
                          j++;
                      }
                      if(success==1)
                        fail=0;
                      i++;
    		   }
                     if(fail==0)
                       System.out.println(dec);	                       
                      c8++;
                   }
                      c7++;
                  }
                     c6++;
                 }
                    c5++;
                }
                   c4++;
               }
                  c3++;
              }
                  c2++;
                 zrobione=(licznik/64)*100;
                 System.out.println("ZROBIONE: "+licznik+" %"); 
             }
                 c1++;
            }
   }

}
