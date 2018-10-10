import java.io.BufferedReader;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CalGC {
    public static final int codons = 64;


    public static void main(String[] args){
        Hashtable codonPair = new Hashtable();
        int seq = 0;
        int[] codonNum = new int[codons];
        double[] rscuVal = new double[codons];

        for (int m = 0; m < codons; m++){
            codonNum[m] = 0;
            rscuVal[m] = 0;
        }

        String[] codonStr = new String[]{
                "TTT","TCT","TAT","TGT",
                "TTC","TCC","TAC","TGC",
                "TTA","TCA","TAA","TGA",
                "TTG","TCG","TAG","TGG",

                "CTT","CCT","CAT","CGT",
                "CTC","CCC","CAC","CGC",
                "CUA","CCA","CAA","CGA",
                "CTG","CCG","CAG","CGG",

                "ATT","ACT","AAT","AGT",
                "ATC","ACC","AAC","AGC",
                "ATA","ACA","AAA","AGA",
                "ATG","ACG","AAG","AGG",

                "GTT","GCT","GAT","GGT",
                "GTC","GCC","GAC","GGC",
                "GTA","GCA","GAA","GGA",
                "GTG","GCG","GAG","GGG"
        };

        for (int i = 0; i < codons; i++){
            codonPair.put(codonStr[i],i);
        }


        try {
            BufferedReader linebr = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/shenzenan/Desktop/HWB/HWB.CDS.fixed.filt.fasta")),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = linebr.readLine()) != null) {
                if (lineTxt.startsWith(">")){

                }
                else {
                    for (int i = 0; i <= lineTxt.length()-3;i+=3) {
                        if (codonPair.get(lineTxt.substring(i, i+3)) != null){
                            seq = (int) codonPair.get(lineTxt.substring(i, i+3));
                            codonNum[seq] += 1;
                        }
                    }
                }
            }
        }
        catch (Exception e){
            System.err.println("read errors :" + e);
        }


        for (int i = 0; i < codons; i++){
            System.out.println(codonStr[i]+" "+String.format("%.2f",rscuVal[i]));
        }
    }
}
