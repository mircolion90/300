package it.secondary.Battle;
import it.console.console.Console;
import it.secondary.enumerators.VictoryMessage;
import it.secondary.warriorTemplate.Warrior;
import it.secondary.enumerators.Curse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static it.secondary.warriorTemplate.Warrior.random;


public class Battle {

    private List<Warrior> firstArmy;
    private List<Warrior> secondArmy;

    public Battle(List<Warrior> firstArmy, List<Warrior> secondArmy) {
        this.firstArmy = firstArmy;
        this.secondArmy = secondArmy;
    }

    public void start() throws InterruptedException {
        System.out.println("dimmi il nome del tuo esercito: ");
        String es1 = Console.readString();
        int survivors = Math.min(firstArmy.size(), secondArmy.size());
        int counter = 1;
        List<Warrior> survivorsFirst = new ArrayList<>();
        List<Warrior> survivorsSecond = new ArrayList<>();
        while (survivors != 0) {
            for (int i = 0; i < survivors; i++) {

                Thread.sleep(750);
                System.out.println(".");
                Thread.sleep(1400);
                System.out.println(". .");
                Thread.sleep(1950);
                System.out.println(". . .");
                Thread.sleep(2120);

                Warrior first = firstArmy.get(i);
                Warrior second = secondArmy.get(i);
                System.out.println("~ ~ ~ Comincia la battaglia round n°: " + counter + " ~ ~ ~");
                fight(first,second);
                counter++;

                survivorsFirst =  firstArmy.stream()
                        .filter(firstArmy -> firstArmy.getAlive())
                        .collect(Collectors.toList());
                System.out.println("I superstiti del tuo esercito: " + es1 + " sono " + survivorsFirst.size());
                firstArmy = survivorsFirst;


                survivorsSecond = secondArmy.stream()
                        .filter(Warrior::getAlive)
                        .collect(Collectors.toList());
                System.out.println("I superstiti dell'esercito avversario sono: " + survivorsSecond.size());
                System.out.println("");
                secondArmy = survivorsSecond;
                survivors = Math.min(survivorsFirst.size(), survivorsSecond.size());
            }




            if (survivorsFirst.size() == 0) {
                System.out.println("Sei morto ¯\\_(ツ)_/¯");
            } else if (survivorsSecond.size() == 0) {
                System.out.println(
                        "⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿\n" +
                                "⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿\n" +
                                "⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿\n" +
                                "⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿\n" +
                                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿\n" +
                                "⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿\n" +
                                "⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿\n" +
                                "⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿\n" +
                                "⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿\n" +
                                "⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿\n" +
                                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿\n" +
                                "\n"+
                                "HAI VINTO CAMPIONE! GRANDE!");
            }
        }


    }


    public void fight(Warrior first, Warrior second) {
        while (first.getAlive() && second.getAlive()) {
            double atk = first.getBasicDamage() * random.nextFloat() * first.getWeapon().getAtk();
            double def = second.getBasicDamage() * random.nextFloat() * second.getWeapon().getDef();

            if (atk > def) {
                second.setHealthPoints(second.getHealthPoints() - atk);
                if (second.getHealthPoints() > 0) {
                    System.out.println("Il tuo guerriero: " + first.getType() + " attacca con un danno di " + twoDecimal(atk) +
                            " lasciando all'avversario " + second.getType() + ": " + twoDecimal(second.getHealthPoints()) + " HP");
                } else {
                    System.out.println(getRandomVictory().getType() + " HAI UCCISO L'AVVERSARIO IN UN COLPO!");
                    System.out.println("");
                    second.setAlive(false);
                }
            } else {
                first.setHealthPoints(first.getHealthPoints() - def);
                if (first.getHealthPoints() > 0) {
                    System.out.println("L'avversario: " + second.getType() + " attacca con un danno di " + twoDecimal(def) +
                            " lasciando al tuo " + first.getType() + ": " + twoDecimal(first.getHealthPoints()) + " HP");
                } else {
                    System.out.println(getRandomCurse().getType() + " SEI STATO UCCISO IN UN COLPO!");
                    System.out.println("");
                    first.setAlive(false);
                }
            }
        }
    }

    public VictoryMessage getRandomVictory() {
        VictoryMessage[] listOfVictoryMessage = VictoryMessage.values();
        return listOfVictoryMessage[random.nextInt(listOfVictoryMessage.length)];
    }

    public Curse getRandomCurse(){
        Curse[] listCurses = Curse.values();
        return listCurses[random.nextInt(listCurses.length)];
    }

    public static double twoDecimal(double input){
        BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}

