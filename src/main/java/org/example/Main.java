package org.example;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        //Task1
        int[] nums = {2, 2, 1, 4, 4, 6, 6};
        System.out.println("Single Number: " + singleNumber(nums));
        int[] nums1 = {2, 2, 1};
        int expected1 = 1;
        int result1 = singleNumber.singleNumber(nums1);
        System.out.println("Test Case 1: " + (expected1 == result1 ? "Passed" : "Failed"));

        int[] nums2 = {4, 1, 2, 1, 2};
        int expected2 = 4;
        int result2 = singleNumber.singleNumber(nums2);
        System.out.println("Test Case 2: " + (expected2 == result2 ? "Passed" : "Failed"));

        // Task 2:
        int amount = 37;
        System.out.println("Minimum Coin Split: " + minSplit(amount));

        // Task 3:
        String[] array = {"extract", "exhale", "excavate"};
        System.out.println("Longest Common Prefix: " + longestPrefix(array));

        // Task 4:
        String a = "1010";
        String b = "1011";
        System.out.println("Binary String Sum: " + binaryStringSum(a, b));

        // Task 5: Count variants for climbing stairs
        int stairsCount = 5;
        System.out.println("Count Variants: " + countVariants(stairsCount));


        //Task 6:
        O1Deletion<Integer> o1Deletion = new O1Deletion<>();
        o1Deletion.add(1);
        o1Deletion.add(2);
        o1Deletion.add(3);
        o1Deletion.add(4);

        System.out.println("Before deletion:");
        o1Deletion.printList();

        o1Deletion.delete(2);

        System.out.println("After deletion:");
        o1Deletion.printList();
    }

    //Task 1
    public int singleNumber(int[] nums) {
        int xor = 0;
        for (int i = 0; i < nums.length; i++){
            xor ^= nums[i];
        }
        return xor;
    }

    //Task2
    public class CoinExchange {
        public static int minCoins(int amount) {
            int[] coins = {50, 20, 10, 5, 1};
            int minCoins = 0;

            for (int coin : coins) {

                int numCoins = amount / coin;

                amount -= numCoins * coin;

                minCoins += numCoins;
            }

            return minCoins;
        }

        //Task3
        public static String longestPrefix(String[] array) {
            if (array == null || array.length == 0) {
                return "";
            }


            String result = array[0];

            for (int i = 0; i < array.length; i++) {
                while(array[i].indexOf(result) != 0){
                    result=result.substring(0, result.length() - 1);
                    if(result.isEmpty()){
                        return "";
                    }
                }
            }
            return result;
        }

        //Task 4
        public static String addBinary(String a, String b) {
            StringBuilder result = new StringBuilder();
            int carry = 0;
            int i = a.length() - 1, j = b.length() - 1;

            while (i >= 0 || j >= 0 || carry > 0) {
                carry += (i >= 0) ? a.charAt(i--) - '0' : 0;
                carry += (j >= 0) ? b.charAt(j--) - '0' : 0;
                result.insert(0, carry % 2);
                carry /= 2;
            }

            return result.toString();
        }

        //Task 5
        public static int countVariants(int stairsCount) {
            if (stairsCount < 0){

                return ("ERROR! Stairs count cannot be negative.");
            }

            if (stairsCount <= 1){
                return 1;
            }


            int prev1 = 1;
            int prev2 = 1;
            int current = 0;

            for (int i = 2; i <= stairsCount; i++) {
                current = prev1 + prev2;
                prev1 = prev2;
                prev2 = current;
            }

            return current;
        }


    }

    // Task 6
    class O1Deletion<T> {
        private final HashMap<T, Integer> map = new HashMap<>();
        private final ArrayList<T> list = new ArrayList<>();

        public void add(T item) {
            if (!map.containsKey(item)) {
                map.put(item, list.size());
                list.add(item);
            }
        }

        public void delete(T item) {
            Integer index = map.get(item);
            if (index != null) {
                int lastIndex = list.size() - 1;
                T lastItem = list.get(lastIndex);
                list.set(index, lastItem);
                map.put(lastItem, index);
                list.remove(lastIndex);
                map.remove(item);
            }
        }
        public void printList() {
            System.out.println(list);
        }


    }
