//Dette er et program som simulerer en pirat som gaar på en oey

public class PirateSurvivalSimulator {
    public static void main(String[] args) {

        //Definerer variabler som brukes i programmet
        int steps = 0;
        final int ITERATIONS = 100000;

        //For-loekke som kjoerer 100000 ganger
        for (int i = 0; i < ITERATIONS; i++) {

            steps += simulate();
        }

        //Regner ut gjennomsnittet
        double average = (double) steps / (double) ITERATIONS;

        //Printer ut resultatet
        System.out.println("Jack was eaten by sharks after " + average + " steps (averaged over 100000 runs)");
    }

    //Metode som simulerer skritt piraten tar
    public static int simulate()  {

        //Definerer variabler som brukes i metoden
        int x = 5;
        int y = 5;
        int direction;
        int steps = 0;

        //While-loekke som kjoerer til Jack gaar ut i sjoeen
        while (x > 0 && x <= 10 && y > 0 && y <= 10) {

            direction = (int) (Math.random() * 4);

            steps++;

            //Switch-setning som bestemmer retningen Jack skal gaa i
            switch (direction) {
                case 0:
                    x--;
                    break;
                case 1:
                    x++;
                    break;
                case 2:
                    y--;
                    break;
                case 3:
                    y++;
                    break;
            }
        }

        return steps;
    }
}
