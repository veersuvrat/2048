import java.util.HashSet;


public class BlockFor2048 {
	
    private int myValue;

    //Generates a block for 2048
    public BlockFor2048(int valueToSet) {
    	HashSet<Integer> Possible = new HashSet<Integer>();
    	for (int i = 2; i <= 4096; i *= 2){
    		Possible.add(i);
    	}
    	if(!(Possible.contains(valueToSet))){
    		throw new IllegalArgumentException("Yo, please enter a valid integer. That number is not supported.");
    	}
    	this.myValue = valueToSet;
    }
    
    /**Returns the blocks value */
    public int getValue() {
    	return this.myValue;
    }
    
    /**Returns whether or not this block is the same value as otherBlock. */
    public boolean isSameVal(BlockFor2048 otherBlock){
    	return (this.myValue == otherBlock.myValue);
    }
    
    @Override
    public String toString(){
    	return String.valueOf(this.myValue);
    }
    
    public void doubleValue(){
    	this.myValue = this.myValue * 2;
    }
    
    @Override
    public boolean equals(Object o){
    	if (o == null || !(o instanceof BlockFor2048) ){
    		return false;
    	}
    	return this.isSameVal((BlockFor2048) o);
    }
}
