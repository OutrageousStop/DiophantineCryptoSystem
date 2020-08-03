import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.lang.Exception;

public class Encrypt {
    public static int power(int x, int y, int p) 
    { 
        int res = 1;      

        x = x % p;  
  
       if (x == 0) return 0;
  
        while (y > 0) 
        { 
            if((y & 1)==1) 
                res = (res * x) % p; 

            y = y >> 1;  
            x = (x * x) % p;  
        } 
        return res; 
    } 
    public static void main(String args[]) throws Exception{
        long t1 = System.currentTimeMillis();
        String file = args[0];
        String encFile = file.substring(0, file.length()-4) + ".encrypt";
        FileInputStream fin = new FileInputStream(file);
        FileOutputStream fout = new FileOutputStream(encFile);
        int p, q, n, phi_n, e, R, alpha, exp1, exp2, ct1, ct2;
        int itr;
        p = 83;
        q = 3;
        R = 5;
        n = p*q;
        phi_n = (p-1) * (q-1);
        e = 5;
        alpha = (1 + R*e) % phi_n;
        exp1 = alpha % phi_n;
        exp2 = R % phi_n;
        while ((itr=fin.read())!= -1) {
            ct1 = power(itr, exp1, n);
            ct2 = power(itr, exp2, n);
            fout.write((char)ct1);
            fout.write((char)ct2);
        }
        System.out.println("Done");
        fin.close();
        fout.close();
        long t2 = System.currentTimeMillis();
        double res = (double)(t2 - t1) / (double)1000;
        System.out.println("Encryption Time: " + res + " s");
    }
}