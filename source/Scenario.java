


public class Scenario {

    private final String[] C_NAMES = { "AlphaTech", "BetaSecure", "GammaCorp", "DeltaNet", "EpsilonData",
            "ZetaGuard", "ThetaInfo", "IotaShield", "KappaSafe", "LambdaSecure",
            "OmicronTech", "SigmaNet", "TauData", "UpsilonGuard", "PhiInfo",
            "ChiShield", "PsiSafe", "OmegaSecure", "NovaTech", "PioneerNet"};
    public int budget;
    public int workforceSize;
    private final String[] S_LEVELS = { "isolated", "privacy-focused", "informal", "public" }; // isolated means DMZ, VPN,
                                                                                             // DHCP. privacy focused
                                                                                             // means DMZ and DHCP.
                                                                                             // informal means DHCP.
                                                                                             // public means nothing
                                                                                             // perse.
    public String currName;
    public String securityString;

    public Scenario() {
        // Constructor to initialize the scenario generation
        this.currName = C_NAMES[((int) (Math.random() * C_NAMES.length))];

        this.workforceSize = (int) (Math.random() * 6) + 1;
        this.budget = (int) (Math.random() * 14000) + (2200 * workforceSize);
        this.securityString = S_LEVELS[(int) (Math.random() * S_LEVELS.length)];
    }

    // Method to generate a random scenario
    public String getScenario() {
        return String.format(
                "<html>Hello, engineer! I'm a representative from %s and I need your help!<br>We need YOU to build %s %s network to support our workforce of %d people.<br>We are willing to give you a maximum, non-negotiable budget of $%d!<br>I hope to see this network working soon!</html>",
                currName, isVowel(securityString) ? "an" : "a", securityString, workforceSize, budget);
    }

    public int getBudget() {
        return budget;
    }

    public int getWorkforceSize() {
        return workforceSize;
    }

    public String getLevelString() {
        return securityString;
    }

    public String getCompanyName() {
        return currName;
    }

    private boolean isVowel(String str) {
        char ch = str.charAt(0);
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }
}
