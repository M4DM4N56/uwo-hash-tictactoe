import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {
	// initiating the hashtable, its size, and the number of records 
    private int size;  
    private LinkedList<Data>[] table;  
    private int numRecords;
    
    // 7643 is a large prime number, making it useful for preventing collisions
    private int x = 7643;
    
    
    // constructor method
    @SuppressWarnings("unchecked")
	public HashDictionary(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        
        // adding a new linked list for every part of the hashtable
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<Data>();
        } // for
    } // constructor

    
    
    @Override
    // put adds new records to the hashtable
    public int put(Data record) throws DictionaryException {
        int index = hash(record.getConfiguration(), x);
        
        // try to get the first element in the index
        try {table[index].getFirst();} 
        
        // if no first element, add it to the hashtable
        catch(Exception e) {
            table[index].add(record);
            numRecords++;    
            return 0;
        } // catch
        
        // searches through the hashtable and sees if theres a duplicate
    	for (int i = 0;i < table[index].size(); i++) {
    		if (table[index].get(i).getConfiguration().equals(record.getConfiguration())) {
    			throw new DictionaryException();
    		} // if
        } // for
    	
         // if no duplicates, add to hash
        table[index].add(record);
        numRecords++;
        return 0;
        
    } // put method

    	
    @Override
    // removes records from the hash
    public void remove(String config) throws DictionaryException {
    	// make sure what is being removed actually exists
        if(this.get(config) ==  -1) throw new DictionaryException();
        
        int key = hash(config, x);
        
        // find the exact location of the config at its index (key) and remove it 
        for(int i = 0; i < table[key].size(); i++) {
            if(table[key].get(i).getConfiguration().equals(config)) {
                table[key].remove(i);
                numRecords--;
                break;
            } // if
        } // for
    	
    } // remove method
    

    @Override
    // getter method
    public int get(String config) {
        int pos = hash(config, x);

        // loops through all elements of the index to find config
        for (int i = 0; i < table[pos].size();i++) {
            if (table[pos].get(i).getConfiguration().equals(config)) {
                return table[pos].get(i).getScore();
            } // if
        } // for

        // Configuration not found
        return -1;  
    } // get method
    
    
    @Override
    // getter method
    public int numRecords() {
        return numRecords;
    } // numrecords method
    
    
    // polynomial hash function
    // this took some trial and error
    private int hash(String key, int otherValue) {
    	int hashValue = key.length();
    	
    	for (int i = 0; i < key.length(); i++) {
    		hashValue = ( (hashValue * otherValue)  + key.charAt(i) ) % size;
    	} // for
    	
        return hashValue;
    } // method hash
    



} // clas HashDictionary
