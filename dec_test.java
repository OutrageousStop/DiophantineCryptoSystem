import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.lang.Exception;

public class Decrypt {
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
    public static void main(String args[]) throws Exception {
        long t1 = System.currentTimeMillis();
        String file = args[0];
        String encFile = "dec_" + file.substring(0, file.length()-8) + ".txt";
        FileInputStream fin = new FileInputStream(file);
        FileOutputStream fout = new FileOutputStream(encFile);
        int p, q, n, phi_n, e, R, alpha, exp3, res;
        int ct1, ct2;
        p = 83;
        q = 3;
        R = 5;
        n = p*q;
        phi_n = (p-1) * (q-1); //‭249‬
        e = 5;
        alpha = (1 + R*e) % phi_n;
        exp3 = -e + phi_n;
        while((ct1=fin.read()) != -1) {
            ct2 = fin.read();
            fout.write((ct1 * power(ct2, exp3, n)) % n);
        }
        fout.close();
        fin.close();
        long t2 = System.currentTimeMillis();
        double time = (double)(t2 - t1) / (double)1000;
        System.out.println("Encryption Time: " + time + " s");
    }    
}