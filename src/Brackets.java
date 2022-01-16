import java.util.HashMap;
import java.util.Stack;

public class Brackets {
    Stack<Character> stack;
    HashMap<Character, Character> map;

    public Brackets(){
        stack = new Stack<>();
        map = new HashMap<>();

        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }

    public boolean match(String brackets){
        for(int i = 0; i < brackets.length(); i++){
            char curr = brackets.charAt(i);
            if(map.containsKey(curr)){
                stack.push(curr);
            }else{
                if(stack.isEmpty()) return false;
                if(curr != map.get(stack.pop())) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        Brackets brackets = new Brackets();
        System.out.println(brackets.match("[{})[]"));
    }
}
