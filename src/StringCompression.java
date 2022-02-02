public class StringCompression {

    public String compress(String word){
        if(word == null) return null;
        if(word.length() == 0) return "";

        char[] characters = word.toCharArray();

        char curr = characters[0];
        int count = 0;
        StringBuilder compressedWord = new StringBuilder();

        for(int i = 0; i < characters.length; i++){
            if(characters[i] == curr){
                count++;
            }else{
                compressedWord.append(curr + "" + count);
                curr = characters[i];
                count = 1;
            }
        }
        compressedWord.append(curr + "" + count);
        return compressedWord.toString();
    }

    public static void main(String[] args){
        StringCompression sc = new StringCompression();
        String compressedWord = sc.compress("aaabbccab");
        System.out.println(compressedWord);
    }
}


// a a a b b b c a b
