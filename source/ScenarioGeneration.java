
public class ScenarioGeneration {

    public static String[] companyNames = {"AlphaTech", "BetaSecure", "GammaCorp", "DeltaNet", "EpsilonData", 
"ZetaGuard", "ThetaInfo", "IotaShield", "KappaSafe", "LambdaSecure", 
"OmicronTech", "SigmaNet", "TauData", "UpsilonGuard", "PhiInfo", 
"ChiShield", "PsiSafe", "OmegaSecure", "NovaTech", "PioneerNet"};
    public static int budget; 
    public static int workforceSize; 
    public static String[] securityLevel = {"isolated", "privacy-focused", "informal", "public"};
    private static String currName;
    private static String securityString;
    // Method to generate a random scenario
    public static String generate() {
         currName = companyNames[(int)(Math.random() * 20)];
        budget = (int)(Math.random() * 20000) + 2000;
        workforceSize = (int)(Math.random() * 100) + 10;
         securityString = securityLevel[(int)(Math.random() * 4)];
        return String.format("Hello, engineer! I'm a representative from %s and I need your help! %s needs YOU to build a network to our specifications! Our company needs a %s for our workforce of %d. Our company is willing to give you a maximum, non-negotiable budget of %d ! I hope to see this network working soon!", currName, currName, securityString, workforceSize, budget);
    }
    public static int getBudget() {
        return budget;
    }
    public static int getWorkforceSize() {
        return workforceSize;
    }
    public static String getSecurityLevel() {
        return securityString;
    }
    public static String getCompanyName() {
        return currName;
    }
}