public class OneAway {

    public boolean isOneAway(String word1, String word2){
        // idea is to get the length of both words
        // have two pointers point to start of each word
        // keep increasing the pointers if both letters are equal
        // if letters are unequal:
            // if both words are same length, increase unequal count by 1 and increase both pointers
            // if one word is longer than other, increase pointer of longer word and increase the unequal count by 1
        int pointer1 = 0; int pointer2 = 0;
        int lengthWord1 = word1.length();
        int lengthWord2 = word2.length();
        int count = 0;

        if(Math.abs(lengthWord1 - lengthWord2) > 1) return false;

        char[] first = word1.toCharArray();
        char[] second = word2.toCharArray();
        while(pointer1 < lengthWord1 && pointer2 < lengthWord2){
            if(first[pointer1] == second[pointer2]){
                pointer1++; pointer2++;
            }else{
                if(lengthWord1 > lengthWord2){
                    pointer1++;
                }else if(lengthWord2 > lengthWord1){
                    pointer2++;
                }else{
                    pointer1++; pointer2++;
                }
                count++;
            }
        }

        return count <= 1;
    }

    public static void main(String[] args){
        OneAway oa = new OneAway();
        System.out.println(oa.isOneAway("palee", "ple")); // returns true
    }
}















