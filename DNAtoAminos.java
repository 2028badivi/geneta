import java.util.*;

public class DNAtoAminos {
    private static ArrayList<String> codonseq = new ArrayList<>();

    public static ArrayList<String> getAmino(){
        return codonseq;
    }
    public static void amingen(String dna) {
        codonseq.clear();
        String mrna = "";

        for (int i = 0; i < dna.length(); i++) {
            if (dna.substring(i, i + 1).equals("A")) {
                mrna += "U";
            }
            else if (dna.substring(i, i + 1).equals("T")) {
                mrna += "A";
            }
            else if (dna.substring(i, i + 1).equals("C")) {
                mrna += "G";
            }
            else if (dna.substring(i, i + 1).equals("G")) {
                mrna += "C";
            }
            else
                mrna += "-";
        }
        System.out.println("Segment Length: " + dna.length() + " base pairs\n");
        System.out.print("DNA:    " + dna + "\n        ");
        for (int i = 0; i < dna.length()/3 * 3; i++)
            System.out.print("|");
        System.out.println("\nmRNA:   " + mrna);

        genCodon(mrna);
        System.out.println("\nCodon sequence:  " + codonseq);
        if(!codonseq.isEmpty() && !codonseq.get(0).equals("Met"))
            System.out.println("                  ^^^\nError!    Invalid start codon!\n            (Met expected)");
    }

    public static void genCodon(String mrna) {
        if (mrna.length() < 3)
            return;
        else {
            for (int i = 0; i < mrna.length() - 2; i += 3) {
                String codon = mrna.substring(i, i + 3);
                if (codon.equals("UUU") || codon.equals("UUC"))
                    codonseq.add("Phe");
                else if (codon.equals("UUA") || codon.equals("UUG") || codon.equals("CUU") || codon.equals("CUC") || codon.equals("CUA") || codon.equals("CUG"))
                    codonseq.add("Leu");
                else if (codon.equals("AGU") || codon.equals("AGC") || codon.equals("UCA") || codon.equals("UCG") || codon.equals("UCU") || codon.equals("UCU"))
                    codonseq.add("Ser");
                else if (codon.equals("UAU") || codon.equals("UAC"))
                    codonseq.add("Tyr");
                else if (codon.equals("UAA") || codon.equals("UAG") || codon.equals("UGA"))
                    codonseq.add("STOP");
                else if (codon.equals("UGU") || codon.equals("UGC"))
                    codonseq.add("Cys");
                else if (codon.equals("UGG"))
                    codonseq.add("Trp");
                else if (codon.equals("CCU") || codon.equals("CCC") || codon.equals("CCA") || codon.equals("CCG"))
                    codonseq.add("Pro");
                else if (codon.equals("CAU") || codon.equals("CAC"))
                    codonseq.add("His");
                else if (codon.equals("CAA") || codon.equals("CAG"))
                    codonseq.add("Gln");
                else if (codon.equals("CGU") || codon.equals("CGC") || codon.equals("CGA") || codon.equals("CGG") || codon.equals("AGA") || codon.equals("AGG"))
                    codonseq.add("Arg");
                else if (codon.equals("AUU") || codon.equals("AUC") || codon.equals("AUA"))
                    codonseq.add("Ile");
                else if (codon.equals("ACU") || codon.equals("ACC") || codon.equals("ACA") || codon.equals("ACG"))
                    codonseq.add("Thr");
                else if (codon.equals("AAU") || codon.equals("AAC"))
                    codonseq.add("Asn");
                else if (codon.equals("AAA") || codon.equals("AAG"))
                    codonseq.add("Lys");
                else if (codon.equals("GUU") || codon.equals("GUC") || codon.equals("GUA") || codon.equals("GUG"))
                    codonseq.add("Val");
                else if (codon.equals("GCU") || codon.equals("GCC") || codon.equals("GCA") || codon.equals("GCG"))
                    codonseq.add("Ala");
                else if (codon.equals("GAU") || codon.equals("GAC"))
                    codonseq.add("Asp");
                else if (codon.equals("GAA") || codon.equals("GAG"))
                    codonseq.add("Glu");
                else if (codon.equals("GGU") || codon.equals("GGC") || codon.equals("GGA") || codon.equals("GGG"))
                    codonseq.add("Gly");
                else if (codon.equals("AUG"))
                    codonseq.add("Met");
                else
                    codonseq.add("invalid");
            }
        }
    }

    public static void alphaHelix(ArrayList<String> aminos) {
        int numOfAlphaHelices = aminos.size()/4;
        System.out.println("Number of Alpha Helices: " + numOfAlphaHelices);
    }
}
