/*
 * Steven Clubb
 * 9/29/2017
 * CS2050-001
 */
package hw4;

import java.util.Scanner;
import java.net.URL;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/*
This program will find the file the user types in, and put each word inside an array.
Clone the array, then time sort each array. A timer will being going on while
the arrays are being sorted, showing which sort is faster.
*/
public class HW4 {
     
    String [] bubbleArray;
    long totalWallClockBubble;
    long totalWallClockJava;
    long totalCPUTimeBubble;
    long totalCPUTimeJava;
    
    public static void main(String[] args) {
        
        HW4 hw = new HW4();
        String nameOfFile = hw.userInputFile();
        hw.chooseFile(nameOfFile);
        //Clone the array
        String [] javaArray = hw.bubbleArray.clone();
        
        sortAndTimeBubbleSort(hw);
        
        sortAndTimeJavaSort(hw, javaArray);
        
        hw.printToUser(nameOfFile);

        
        
    }
    //Time the Bubble sort
    public static void sortAndTimeBubbleSort(HW4 hw) {
        long startWallClockBubble = System.currentTimeMillis();
        long startCPUTimeBubble = System.nanoTime();
        hw.bubbleSort(hw.bubbleArray);
        long endWallClockBubble = System.currentTimeMillis();
        long endCPUTimeBubble = System.nanoTime();
        hw.totalWallClockBubble = endWallClockBubble - startWallClockBubble;
        hw.totalCPUTimeBubble = endCPUTimeBubble - startCPUTimeBubble;
    }

    //Time the internal java sort
    public static void sortAndTimeJavaSort(HW4 hw, String[] javaArray) {
        long startWallClockJava = System.currentTimeMillis();
        long startCPUTimeJava = System.nanoTime();
        hw.javaSort(javaArray);
        long endWallClockJava = System.currentTimeMillis();
        long endCPUTimeJava = System.nanoTime();
        hw.totalCPUTimeJava = endCPUTimeJava - startCPUTimeJava;
        hw.totalWallClockJava = endWallClockJava - startWallClockJava;
    }
    
    //Get name of file from user.
    public String userInputFile(){
        
        Scanner kb = new Scanner(System.in);
        System.out.println("Please enter the .txt file name.");
        String fileName = kb.next();
        
        return fileName;
    }
    
    //Getting the file and assigning words to array.
    public void chooseFile(String fileName){

        try{
            URL url = getClass().getResource(fileName);
            File file = new File(url.getPath());
            Scanner f = new Scanner(file);
            int totalCount = 0;
            
            while (f.hasNext()){
                //Count the number of words, to find how big array needs to be
                String word = f.next().replaceAll("[^a-zA-Z0-9]+", "").trim();
                if(word.length() != 0){
                    totalCount++;
                }   
            }
            //Make the array with size previously given
            bubbleArray = new String[totalCount];
            int count = 0;
            //Go through file again and assign words to elements
            f = new Scanner(file);
            while (f.hasNext()){
                
                String word = f.next().replaceAll("[^a-zA-Z0-9]+", "").trim();
                if(word.length() != 0){
                    bubbleArray[count++] = word;
                }
            }
        }
        catch(FileNotFoundException e){
        }
    }

    //Javas internal sort
    public String [] javaSort(String[] javaSortArr){
        
        Arrays.sort(javaSortArr);
        return javaSortArr;
    }

    //Bubble sort method.
    public String [] bubbleSort(String [] unsortedArray){
        
        String temp;
        int n = unsortedArray.length;
        for(int i = 0; i < n; i++){
            for(int j = 1; j<(n-i);j++){
                if(unsortedArray[j-1].compareTo(unsortedArray[j]) > 0){
                    temp = unsortedArray[j-1];
                    unsortedArray[j-1] = unsortedArray[j];
                    unsortedArray[j] = temp;
                }
            }
        }
        return unsortedArray;
    }
    
    //Used to print data to user.
    public void printToUser(String nameOfFile){
        
        System.out.println("\nFilename: " + nameOfFile);
        System.out.println("Number of Words: " + bubbleArray.length);
        System.out.println("Wall Clock: ");
        System.out.printf("%15s%12d\n", "Bubble sort: ", totalWallClockBubble );
        System.out.printf("%17s%7d\n", "Internal sort: ", totalWallClockJava);
        System.out.println("\nCPU Time: ");
        System.out.printf("%15s%17d\n","Bubble sort: ", totalCPUTimeBubble);
        System.out.printf("%17s%12d\n", "Internal sort: ", totalCPUTimeJava);
    }
}
