
public class Scenario {

    public String[] companyNames = { "AlphaTech", "BetaSecure", "GammaCorp", "DeltaNet", "EpsilonData",
            "ZetaGuard", "ThetaInfo", "IotaShield", "KappaSafe", "LambdaSecure",
            "OmicronTech", "SigmaNet", "TauData", "UpsilonGuard", "PhiInfo",
            "ChiShield", "PsiSafe", "OmegaSecure", "NovaTech", "PioneerNet" };
    public int budget;
    public int workforceSize;
    public String[] securityLevel = { "isolated", "privacy-focused", "informal", "public" };
    public String currName;
    public String securityString;

    public Scenario() {
        // Constructor to initialize the scenario generation
        this.currName = companyNames[((int) (Math.random() * companyNames.length))];
        this.budget = (int) (Math.random() * 20000) + 2000;
        this.workforceSize = (int) (Math.random() * 100) + 10;
        this.securityString = securityLevel[(int) (Math.random() * securityLevel.length)];
    }

    // Method to generate a random scenario
    public String getScenario() {
        return String.format(
                "<html>Hello, engineer! I'm a representative from %s and I need your help!<br>We need YOU to build a %s network to support our workforce of %d people.<br>We are willing to give you a maximum, non-negotiable budget of $%d!<br>I hope to see this network working soon!</html>",
                currName, securityString, workforceSize, budget);
    }

    public int getBudget() {
        return budget;
    }

    public int getWorkforceSize() {
        return workforceSize;
    }

    public String getSecurityLevel() {
        return securityString;
    }

    public String getCompanyName() {
        return currName;
    }
}