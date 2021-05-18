//Dette er et program som estimerer dødstidspunktet til et menneske.
import java.util.Random;
public class BodyTemperature {

    public static void main(String[] args) {

        double[] array = cooldownSamples(27, 100000);
        double[] counts = countsFromArray(array, 20);
        String[][] array2d = array2dFromCounts(counts);
        printReport(array2d, minFromArray(array), maxFromArray(array));
    }

    //Kalkulerer hvor lang tid det tar for kroppens temperatur aa gaa ned til en bestemt temperatur
    public static double cooldown(double temperature) {

        Random random = new Random();

        double bodyTemperature = 37;
        bodyTemperature += random.nextGaussian();

        double cooldownTime = Math.log(bodyTemperature / temperature);
        cooldownTime *= 1 / bodyTemperature;
        cooldownTime *= 255 + random.nextGaussian();

        return cooldownTime;
    }

    //Lagrer resultatene fra cooldownTime i en tabell og returnerer den
    public static double[] cooldownSamples(int temperature, int numSamples) {

        double[] array = new double[numSamples];

        for (int i = 0; i<array.length; i++) {
            array [i] = cooldown(temperature);
        }
        return array;
    }

    //Returnerer minste verdi fra tabellen
    public static double minFromArray(double[] array) {

        double min = array[0];

        for (int i = 1; i<array.length; i++) {
            if (array[i]<min) {
                min = array[i];
            }
        }
        return min;
    }

    //Returnerer max verdi fra tabellen
    public static double maxFromArray(double[] array) {

        double max = array[0];

        for (int i = 1; i<array.length; i++) {
            if (array[i]>max) {
                max = array[i];
            }
        }
        return max;
    }

    //Returnerer en tabell som viser antall verdier innen et intervall
    public static double[] countsFromArray(double[] array, int numRanges) {

        double[] counts = new double[numRanges];
        double rangeSize = (maxFromArray(array)-minFromArray(array))/(numRanges-1);
        double minNumber = minFromArray(array);

        //Gaar gjennom alle tallene i tabellen og sjekker hvert tall for hvilken range den ligger i
        for (double value:array) {
          for (int i = 0; i<counts.length; i++) {
                if (rangeSize*i <= (value-minNumber) && (value-minNumber) < rangeSize*(i+1)) {
                    counts[i] +=  1;
                }
            }
        }
        return counts;
    }

    //Skirver ut en 2d-tabell
    public static void printArray2d(String[][] array2d) {

        for(String[] row:array2d) {
            for(String column:row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
    }

    //Lager en ny tabell med hjelp av verdier fra countsFromArray
    public static String[][] array2dFromCounts(double[] counts) {

        final int PRINT_WIDTH = 50;
        String[][] array2d = new String[counts.length][PRINT_WIDTH];
        int maxNumber = (int) maxFromArray(counts);

        //Gaar gjennom alle posisjoner i array2d og gir de riktig verdi
        for (int row = 0; row<array2d.length; row++) {
            for (int column = 0; column<array2d[row].length; column++) {
                int hashT = ((int)counts[row]) * PRINT_WIDTH / maxNumber;
                if (column < hashT) {
                    array2d[row][column] = "#";
                }else{
                    array2d[row][column] = " ";
                }
            }
        }
        return array2d;
    }

    //Printer ut hele rapporten
   public static void printReport(String[][] array2d, double arrayMin, double arrayMax) {

       double rangeSize = (arrayMax-arrayMin)/(array2d.length-1);
       System.out.printf("Time since death probability distribution\n"
               + "- Each line corresponds to %.2f hours.\n"
               + "============================================== \n"
               + "%.2f \n\n\n\n\n", rangeSize, arrayMin);
       printArray2d(array2d);
       System.out.printf("\n\n\n\n\n%.2f\n==============================================", arrayMax);
   }
}


