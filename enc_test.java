import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.lang.Exception;

public class enc_test {
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
        int p, q, n, phi_n, e, R, alpha, exp1, exp2, ct1, ct2, exp3;
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
        System.out.println("Cipher Info \np: 83\t q: 3\t R: 5\t");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Message: ");
        int num = sc.nextInt();
        long t1 = System.currentTimeMillis();
        ct1 = power(num, exp1, n); 
        ct2 = power(num, exp2, n);
        System.out.println("Cipher Text: " + ct1 + " " + ct2);
        exp3 = -e + phi_n;
        int dec = (ct1 * power(ct2, exp3, n)) % n;
        System.out.println("Decrypted Message: " + dec);
        System.out.println("Done");
        long t2 = System.currentTimeMillis();
        double res = (double)(t2 - t1) / (double)1000;
        System.out.println("Encryption Time: " + res + " s");
    }
}