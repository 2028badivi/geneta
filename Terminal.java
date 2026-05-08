import java.util.*;

public class Terminal {
    private Map<String, String> dnaMap;
    public static String version = "2.2";
    public Terminal() {
        dnaMap = new HashMap<>();
    }

    public static void main(String[] args) {
        printBanner();
        Terminal terminal = new Terminal();
        terminal.run();
    }

    private static void printBanner() {
        System.out.println("   ______                     __              ______  __       ______ ");
        System.out.println("  / ____/___  ____  ___  / /_____ _      / ____/ / /      /  _/   ");
        System.out.println(" / / __/ _ \\/ __ \\/ _ \\/ __/ __ `/     / /   / /       / /     ");
        System.out.println("/ /_/ /  __/ / / /  __/ /_/ /_/ /     / /___/ /___   _/ /      ");
        System.out.println("\\____/\\___/_/ /_/\\___/\\__/\\__,_/      \\____/_____/  /___/      ");
        System.out.println("\n Geneta CLI v" + version);
        System.out.println(" Credits: Bhavesh Adivi 2026\n");
    }

    public void run() {
        boolean envIsDynamic = false;
        Scanner keyboard = new Scanner(System.in);
        String argument;
        boolean lynxDynamic;
        
        System.out.println("Type 'gen install pangolin' to enter the DNA processing environment or enter commands directly.");
        
        while(true){
            System.out.print("geneta> ");
            if (!keyboard.hasNextLine()) break;
            argument = keyboard.nextLine().trim();
            
            if(argument.equals("gen install pangolin")){
                double midtemp = Math.random();
                int sessionID = (int) (Math.random() * 300 + 1);
                System.out.println("ms[c:" + midtemp + "]\nSessionID[" + sessionID + "]\n\n");
                envIsDynamic = true;
                while (envIsDynamic) {
                    System.out.print("<#true_parse> ");
                    if (!keyboard.hasNextLine()) {
                        envIsDynamic = false;
                        break;
                    }
                    argument = keyboard.nextLine().trim();

                    if (argument.equals("end()")) {
                        envIsDynamic = false;
                    } else if(argument.equals("gen install deeplynx")){
                        lynxDynamic = true;
                        while (lynxDynamic) {
                            System.out.print(">>> ");
                            if (!keyboard.hasNextLine()) {
                                lynxDynamic = false;
                                envIsDynamic = false;
                                break;
                            }
                            argument = keyboard.nextLine().trim();

                            if (argument.equals("end")) {
                                lynxDynamic = false;
                            } else if (!argument.isEmpty()) {
                                System.out.println("Insert code here for evaluation (put in logic for deeplynx!!!!)");
                            }
                        }
                    } else if (!argument.isEmpty()) {
                        evaluate(argument);
                    }
                }
            } else if (argument.equals("exit") || argument.equals("quit")) {
                break;
            } else if (!argument.isEmpty()) {
                evaluate(argument);
            }
        }
    }

    public void evaluate(String input) {
        if (input.contains("=")) {
            String[] parts = input.split("=", 2);
            String varName = parts[0].trim();
            String valuePart = parts[1].trim();

            if (valuePart.startsWith("dna(") && valuePart.endsWith(")")) {
                long start = System.nanoTime();
                String sequence = valuePart.substring(4, valuePart.length() - 1);
                dnaMap.put(varName, sequence);
                long end = System.nanoTime();
                System.out.println("ms[c:" + "\"" + varName + "\"" + ":\"" + sequence + "\":" + (double) (end - start) / 1_000_000.0 + "]");
            } else {
                System.out.println("error[invalid_assignment: \"" + input + "\"]");
            }
        } else if (input.startsWith("amino(") && input.endsWith(")")) {
            String varName = input.substring(6, input.length() - 1);
            String dna = dnaMap.get(varName);
            if (dna != null) {
                long start = System.nanoTime();
                DNAtoAminos.amingen(dna);
                long end = System.nanoTime();
                System.out.println("\nms[c:" + (double) (end - start) / 1_000_000.0 + "]");
            } else {
                System.out.println("error[unknown_variable: \"" + varName + "\"]");
            }
        } else if (input.startsWith("helix(") && input.endsWith(")")) {
            String varName = input.substring(6, input.length() - 1);
            if (!dnaMap.containsKey(varName)) {
                System.out.println("error[unknown_variable: \"" + varName + "\"]");
                return;
            }
            if (DNAtoAminos.getAmino().isEmpty()) {
                System.out.println("error(Codon_Sequence_null)");
            } else {
                DNAtoAminos.alphaHelix(DNAtoAminos.getAmino());
            }
        } else if (input.startsWith("size(") && input.endsWith(")")) {
            String varName = input.substring(5, input.length() - 1);
            if (dnaMap.containsKey(varName)) {
                System.out.println(dnaMap.get(varName).length());
            } else {
                System.out.println("error[unknown_variable: \"" + varName + "\"]");
            }
        } else if (input.startsWith("shape(") && input.endsWith(")")) {
            String varName = input.substring(6, input.length() - 1);
            if (dnaMap.containsKey(varName)) {
                System.out.println(dnaMap.get(varName));
            } else {
                System.out.println("error[unknown_variable: \"" + varName + "\"]");
            }
        } else if (input.startsWith("valid(") && input.endsWith(")")) {
            String varName = input.substring(6, input.length() - 1);
            if (dnaMap.containsKey(varName)) {
                String dna = dnaMap.get(varName);
                System.out.print("t:");
                System.out.println(dna.length() % 3 == 0 ? "t" : "f");
            } else {
                System.out.println("f:f");
            }
        } else if (input.equals("geneta.version")) {
            System.out.println(version);
        } else {
            System.out.println("error[failed_to_parse: \"" + input + "\"]");
        }
    }
}
