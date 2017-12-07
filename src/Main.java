import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException {


      generateMap("corpus3");

      //  cleanText("corpus","corpus3");

    }



    public static void cleanText(String inputFile, String outputFile) throws  IOException{

        String text;
        BufferedReader in = (new BufferedReader(new FileReader(inputFile)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));


        while((text = in.readLine())!=null){

            text = text.replaceAll("(?U)[^\\p{Alnum}]\\d+"," ").toLowerCase();
            text = text.trim().replaceAll(" +", " ");

            writer.write(text+" ");
        }
        writer.close();


    }


    public static Map<String, double[]> generateMap(String corpusFileName) throws FileNotFoundException, IOException{

        Map<String, double[]> ret = initializeMap();
        FileReader fr=new FileReader(corpusFileName);
        BufferedReader br=new BufferedReader(fr);



        Character firstChar = (char) br.read();
        Character secondChar = (char) br.read();
        Character thirdChar = (char) br.read();
        int nextChar = br.read();
        while(nextChar >=97&&nextChar<=122||nextChar==' ') {

            String triplet = firstChar.toString() + secondChar.toString() + thirdChar.toString();

            firstChar = secondChar;
            secondChar = thirdChar;
            thirdChar = (char)nextChar;
            if (nextChar == ' ') {
                nextChar = 0;
            }else{
                nextChar = nextChar - 96;
            }


            System.out.println("triplet: " + triplet);
            double[] array = ret.get(triplet);
            double newValue = array[nextChar] + 1;
            array[nextChar] = newValue;

            System.out.print("array: " + "[");
            for(int i= 0; i<27; i++){
                System.out.print( array[i] + ",");
            }
            System.out.println("]");
            ret.put(triplet, array);

            nextChar = br.read();

        }

        return ret;
    }


    public static Map<String, double[]> initializeMap(){

        ArrayList<String> triplets = generateTriplets();
        Map<String, double[]> ret = new HashMap<String, double[]>();

        for(String triplet: triplets){
            double[] arr = new double[27];
            ret.put(triplet, arr);
        }

        return ret;
    }



    public static ArrayList<String> generateTriplets() {

        char first = 'a';
        char second = 'a';
        char third = 'a';

        ArrayList<String> ret = new ArrayList<String>();


        for(int i = 0; i<27; i++){
            for(int j = 0; j<27; j++){
                for(int k= 0; k<27; k++){


                    Character firstChar = (char) (first+i);
                    Character secondChar = (char) (second+j);
                    Character thirdChar = (char) (third+k);


                    if(i==26){

                        firstChar = ' ';
                    }
                    if(j==26){

                        secondChar = ' ';
                    }
                    if(k==26){

                        thirdChar = ' ';
                    }

                    String prefix = firstChar.toString() + secondChar.toString() + thirdChar.toString();
                   // System.out.println(prefix+" ");
                    ret.add(prefix);
                }

            }
        }


        return ret;

    }
}