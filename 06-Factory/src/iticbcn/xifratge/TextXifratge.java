package iticbcn.xifratge;
public class TextXifratge {
    public static void main(String[] args) {
        AlgorismeFactory[] aFactory = {
            new AlgorismeAES(), new AlgorismeMonoalfabetic(),
            new AlgorismePolialfabetic(), new AlgorismeRotX()
        };

        String[] aNames = {
            "AES", "Monoalfabètic", "Polialfabètic", "RotX"
        };

        String[] msgs = {
            "Test 01: Àlgid, Ülrich, Vàlid",
            "Test 02: Caràcters especials ¡¿?*-123[]{}@#"
        };

        String[][] claus = {
            {"Clau Secreta 01"},
            {"ErrorClau", null},
            {"ErrorClau2", "123456"},
            {"-1", "13", "1000", "Errorclau"}
        };

        for(int i = 0; i <aFactory.length; i++) {
            AlgorismeFactory alg = aFactory[i];


        }
    }
}
