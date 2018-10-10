import java.io.BufferedReader;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CalFreqCodon {
    public static final int aminos = 21;
    public static final int codons = 64;


    public static void main(String[] args){
        /*try {
            BufferedReader linebr = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/shenzenan/Desktop/data/data/HWB/HWB.CDS.fixed.filt.fasta")),
                    "UTF-8"));
            String lineTxt = null;

            while ((lineTxt = linebr.readLine()) != null) {
                if (lineTxt.startsWith(">")){

                }
                else {
                    System.out.println(lineTxt.length());
                }
            }
        }
        catch (Exception e){
            System.err.println("read errors :" + e);
        }*/

        Hashtable codonPair = new Hashtable();
        int seq = 0;
        int[] codonNum = new int[codons];
        double[] rscuVal = new double[codons];
        String totaltxt = "";
        for (int m = 0; m < codons; m++){
            codonNum[m] = 0;
            rscuVal[m] = 0;
        }

        String[] codonStr = new String[]{
                "GCT","GCC","GCA","GCG",
                "GGT","GGC","GGA","GGG",
                "CCT","CCC","CCA","CCG",
                "ACT","ACC","ACA","ACG",
                "GTT","GTC","GTA","GTG",
                "TTT","TTC","AAT","AAC",
                "AAA","AAG","GAT","GAC",
                "GAA","GAG","CAT","CAC",
                "CAA","CAG","TAT","TAC",
                "TGT","TGC",
                "TCT","TCC","TCA","TCG","AGT","AGC",
                "CGT","CGC","CGA","CGG","AGA","AGG",
                "CTT","CTC","CTA","CTG","TTA","TTG",
                "ATT","ATC","ATA","ATG","TGG",
                "TGA","TAA","TAG"
        };

        for (int i = 0; i < codons; i++){
            codonPair.put(codonStr[i],i);
        }


        int[] codonPos = {0,4,8,12,16,20,22,24,26,28,30,32,34,36,38,44,50,56,59,60,61};
        int[] internals = {4,4,4,4,4,2,2,2,2,2,2,2,2,2,6,6,6,3,1,1,3};

        try {
            BufferedReader linebr = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/shenzenan/Desktop/data/XJC/XJC.CDS.filt.fasta")),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = linebr.readLine()) != null) {
                if (lineTxt.startsWith(">")){
                    if (totaltxt != "") {
                        for (int i = 0; i <= totaltxt.length() - 3; i += 3) {
                            if (codonPair.get(totaltxt.substring(i, i + 3)) != null) {
                                seq = (int) codonPair.get(totaltxt.substring(i, i + 3));
                                codonNum[seq] += 1;
                            }
                        }
                        totaltxt="";
                    }
                }
                else {
                    totaltxt += lineTxt;
                }
            }
        }
        catch (Exception e){
            System.err.println("read errors :" + e);
        }

        for (int i = 0; i < aminos; i++){
            int sum = 0;
            double rscu = 0;
            for (int j = 0; j < internals[i]; j++){
                sum += codonNum[codonPos[i]+j];
            }

            for (int j = 0; j < internals[i]; j++) {
                rscu = (double)codonNum[codonPos[i] + j] * internals[i] / sum;
                rscuVal[codonPos[i] + j] = rscu;
                if (rscu >= 1.5 || 5 * codonNum[codonPos[i] + j] > 3 * sum) {
                    System.out.println(codonStr[codonPos[i] + j]+" "+ rscu);
                }
            }
        }

    }
}
