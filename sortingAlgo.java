//PURPOSE OF PROGRAM: TO SORT AN ARRAY OF ELEMENTS USING THE QUICKSORT ALGORITHM WITH TWO APPROACHES TO PARTITIONING (HOARE AND LOMUTO);
//                        THIS PROGRAM COMPARES BOTH USING NO. OF SWAPS AND KEY COMPARISONS;

//PROGRAMMER:   OSABHIE EBENEZER FERGUSON;
//COURSE NUMBER: CSCI 7432
//PROGRAMMING ASSIGNMENT NO.: 1;
//DATE: JANUARY 30TH 2022;


package algo;


import java.util.Random;
import java.util.Arrays;

public class sortingAlgo {
    public static void main(String[] args) {
//        int[] arr = new int[]{ 3,5,4 };

        int[] arr = new int[100];

//      random integers between 100 and 1000 inclusive
        int min = 100;
        int max = 1000;

        for(int i = 0; i< arr.length; i++) {
            arr[i] = (int)(Math.random() * (max - min)) + min;
        }


        System.out.println("initialArray: " + Arrays.toString(arr));


//      Sorted Arrays
        randomizedQuicksort(arr, 0, arr.length-1);
        System.out.println("randomizedQuicksort array" + Arrays.toString(arr));

        randomizedHoareQuicksort(arr, 0, arr.length-1);
        System.out.println("randomized Hoare-Quicksort array" + Arrays.toString(arr));

//      Number of key comparisons and swaps

        System.out.println("ANALYSIS OF SWAPS AND KEY COMPARISONS");
        System.out.println("number Of Swaps for Randomized Quicksort is: " + numberOfSwapsRandomizedQuicksort);
        System.out.println("number of key Comparisons for Randomized-Quicksort is: " + keyComparisonsRandomizedQuicksort);


        System.out.println("number Of Swaps for Randomized-HoareQuicksort is: " + numberOfSwapsRandomizedHoareQuicksort);
        System.out.println("number of key Comparisons for Randomized-HoareQuicksort is: " + keyComparisonsRandomizedHoareQuicksort);
    }

    static int numberOfSwapsRandomizedQuicksort = 0;
    static int keyComparisonsRandomizedQuicksort = 0;

    static int numberOfSwapsRandomizedHoareQuicksort = 0;
    static int keyComparisonsRandomizedHoareQuicksort = 0;

    public static void randomizedQuicksort(int[] arr, int p, int r) {
        if (p < r) {
            int q = randomizedPartition( arr, p, r );
            randomizedQuicksort(arr, p, q-1);
            randomizedQuicksort(arr, q+1, r);

        }

//        else{
//            return;
//        }
    }

    public static void randomizedHoareQuicksort( int[] arr, int p, int r ){
        if (p < r) {
            int q = randomizedHoarePartition( arr, p, r );
            randomizedHoareQuicksort(arr, p, q-1);
            randomizedHoareQuicksort(arr, q+1, r);

        }
    }


    public static int randomizedPartition( int[] arr, int p, int r ){
        int i = random(p, r);
        int hold = arr[r];
        arr[r] = arr[i];
        arr[i] = hold;

        numberOfSwapsRandomizedQuicksort += 1;


//        System.out.println("this is random " + i);
//        System.out.println("this is hold " + hold);
//        System.out.println("this is arr[r] " + arr[r]);
//        System.out.println("this is arr[i] " + arr[i]);
//        System.out.println("swapped array" + Arrays.toString(arr));

        return  partition(arr, p, r);
//        return  hold;
    }

    public static int partition( int[] arr, int p, int r ) {
        int x = arr[r];
        int i = p-1;

        for(int j=p; j<=r-1; j++) {
            keyComparisonsRandomizedQuicksort += 1;
            if(arr[j] <= x) {
                i = i + 1;
                int hold = arr[i];
                arr[i] = arr[j];
                arr[j] = hold;
                numberOfSwapsRandomizedQuicksort += 1;
            }
        }
        int hold2 = arr[i+1];
        arr[i+1] = arr[r];
        arr[r] = hold2;
        numberOfSwapsRandomizedQuicksort += 1;

        return i+1;
    }

    public static int randomizedHoarePartition( int[] arr, int p, int r ){
        int f = random(p, r);
        int hold4 = arr[p];
        arr[p] = arr[f];
        arr[f] = hold4;

        numberOfSwapsRandomizedHoareQuicksort += 1;

        return  hoarePartition(arr, p, r);
    }

    public static int hoarePartition( int[] arr, int p, int r ){
        int x = arr[p];
        int i = p - 1;
        int j = r + 1;

        while(true){
            do{
                j= j - 1;
                keyComparisonsRandomizedHoareQuicksort += 1;
            }while(arr[j]>x);
            do{
                i = i+1;
                keyComparisonsRandomizedHoareQuicksort += 1;
            }while(arr[i]<x);

            if(i<j){
                int hold3 = arr[i];
                arr[i] = arr[j];
                arr[j] = hold3;
                numberOfSwapsRandomizedHoareQuicksort += 1;
            }
            else{
                return j;
            }
        }
    }

    public static int random(int a, int b ) {
        Random rand = new Random();
        int pivotPoint = a + rand.nextInt(b - a + 1);
//        System.out.println("this is the pivot point " + arr[pivotPoint]);
        return pivotPoint;

    }

}
