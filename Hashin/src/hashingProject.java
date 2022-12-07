import java.util.*;
public class hashingProject 
{
	public static Entry<Integer, String>[] hashTable = new Entry[101];
	public static Entry<Integer, Integer>[] hashTable2 = new Entry[101];
	public static int j = 0; //quadratic factor
	public static void main(String[] args) 
	{
		Integer[] numbers = {627, 378, 549, 432, 148, 121, 528, 402, 246, 599, 
							 85, 359, 195, 345, 212, 187, 525, 393, 632, 143};
		List<String> names = new ArrayList<String>();

		names.add("Sue");
		names.add("Harry");
		names.add("Nina");
		names.add("Susannah");
		names.add("Larry");
		names.add("Eve");
		names.add("Sarah");
		names.add("Adam");
		names.add("Tony");
		names.add("Katherine");
		names.add("Juliet");
		names.add("Romeo");
		
		/* for(String name : names)
		{
			int index = getHashIndex(name);
			if(hashTable[index] == null)
			{
				Integer key = index;
				Entry<Integer, String> item = new Entry<Integer, String>(key, name);
				hashTable[index] = item;
			}
			else
			{
				//deal with collision
				linearProbing(index, name);
				//quadraticProbing(index, name);
				//doubleHashing(index, name);
				
			}
			System.out.printf("%20s%20d \n",name, getHashIndex(name));  // shows the result of getHashIndex
			System.out.printf("%20s%20d \n",name, name.hashCode());    // shows the hashCode() method of String
			System.out.printf("%20s%20d \n\n",name, hashcode(name));     // shows that the hashcode method below returns the same as class method
		}
		System.out.println("******************************************************************");
		System.out.printf("%20s%20s\n\n", "Index", "Value");
		for(Entry<Integer, String> e : hashTable)
		{
			if(e != null)
				System.out.printf("%20d%20s\n", (int)e.getKey(), e.getValue());
		}

	}*/
		for(Integer num : numbers)
		{
			int index = getHashIndex(num);
			if(hashTable2[index] == null)
			{
				Integer key = index;
				Entry<Integer, Integer> item = new Entry<Integer, Integer>(key,num);
				hashTable2[index] = item;
			}
			else
			{
				//deal with collision
				//linearProbing(index, num);
				//quadraticProbing(index, num);
				doubleHashing(index, num);
				
			}
			System.out.printf("%20s%20d \n",num, getHashIndex(num));  // shows the result of getHashIndex
			System.out.printf("%20s%20d \n",num, num.hashCode());    // shows the hashCode() method of String
			//System.out.printf("%20s%20d \n\n",num, hashcode(num));     // shows that the hashcode method below returns the same as class method
	        System.out.println();	
		}
		System.out.println("******************************************************************");
		System.out.printf("%20s%20s\n\n", "Index", "Value");
		for(Entry<Integer, Integer> e : hashTable2)
		{
			if(e != null)
				System.out.printf("%20d%20s\n", (int)e.getKey(), e.getValue());
		}
	}
	/*
	 * Calculates hash code for a String
	 * @param s - value to be hashed
	 * @return hashcode of value
	 */
	public static int hashcode(String s)
	{
		int hash = 0;
		int n = s.length();
		for(int i = 0; i < n; i++)
			hash = 31 * hash + s.charAt(i);
		return hash;
	}
	/*
	 * Makes hashCode a viable index for an array
	 * @param key - value to hash
	 * @return index to store value
	 */
	private static int getHashIndex(String key)
	{
		int hashIndex = key.hashCode() % hashTable.length;
		if(hashIndex < 0)
			hashIndex = hashIndex + hashTable.length;
		return hashIndex;
	}
	private static int getHashIndex(Integer key)
	{
		int hashIndex = key.hashCode() % hashTable2.length;
		if(hashIndex < 0)
			hashIndex = hashIndex + hashTable2.length;
		return hashIndex;
	}
	/*
	 * Resolving hashing collision with linear probing
	 * increase index by 1 until open spot in array is found
	 * @param index - original hash index
	 * @param n - original value
	 */
	public static void linearProbing(int index, String n)
	{
		System.out.println("Solving Collision with Linear Probing");
		while(index < hashTable.length)
		{
			index++;
			if(index >= hashTable.length) //when index reaches end of array wrap around to beginning
				index = 0;
			
			if(hashTable[index] == null)
			{
				Integer key = index;
				Entry<Integer, String> item = new Entry<Integer, String>(key, n);
				hashTable[index] = item;
				return;
			}
			
			
		}
	}
	public static void linearProbing(int index, Integer n)
	{
		System.out.println("Solving Collision with Linear Probing");
		while(index < hashTable2.length)
		{
			index++;
			if(index >= hashTable2.length) //when index reaches end of array wrap around to beginning
				index = 0;
			
			if(hashTable2[index] == null)
			{
				Integer key = index;
				Entry<Integer,Integer> item = new Entry<Integer, Integer>(key, n);
				hashTable2[index] = item;
				return;
			}
			
			
		}
	}
	/*
	 * Resolves hashing collisions with quadratic probing
	 * adds j^2 to original hash index, for j >= 0
	 * @param index - original hash index
	 * @param n - original value
	 */
	public static void quadraticProbing(int index, String n)
	{
		System.out.println("Solving Collision with Quadratic Probing");
		
		while(index < hashTable.length)
		{
			
			index+= j * j;
			j++;
			
			if(index >= hashTable.length) //when index reaches end of array wrap around to beginning
				index = 0;
			
			if(hashTable[index] == null)
			{
				Integer key = index;
				Entry<Integer, String> item = new Entry<Integer, String>(key, n);
				hashTable[index] = item;
				return;
			}
		}
	}
	public static void quadraticProbing(int index, Integer n)
	{
		System.out.println("Solving Collision with Quadratic Probing");
		
		while(index < hashTable2.length)
		{
			
			index+= j * j;
			j++;
			
			if(index >= hashTable2.length) //when index reaches end of array wrap around to beginning
				index = 0;
			
			if(hashTable2[index] == null)
			{
				Integer key = index;
				Entry<Integer, Integer> item = new Entry<Integer, Integer>(key, n);
				hashTable2[index] = item;
				return;
			}
		}
	}
		/*
		 * Resolves hashing collisions by Double Hashing
		 * hashes value and does a second hash to determine length
		 * of span between indexes to check 
		 * 
		 * @param index - original hash index
		 * @param n - original value
		 */
		public static void doubleHashing(int index, String n)
		{
			System.out.println("Solving Collision with Double Hashing");
			int increment = 5 - index % 5;
			while(index < hashTable.length)
			{
				index+=increment;
				if(index >= hashTable.length) //when index reaches end of array wrap around to beginning
					index = 0;
				
				if(hashTable[index] == null)
				{
					Integer key = index;
					Entry<Integer, String> item = new Entry<Integer, String>(key, n);
					hashTable[index] = item;
					return;
				}
				
				
			}
		}	
		public static void doubleHashing(int index, Integer n)
		{
			System.out.println("Solving Collision with Double Hashing");
			int increment = 5 - index % 5;
			while(index < hashTable2.length)
			{
				index+=increment;
				if(index >= hashTable2.length) //when index reaches end of array wrap around to beginning
					index = 0;
				
				if(hashTable2[index] == null)
				{
					Integer key = index;
					Entry<Integer, Integer> item = new Entry<Integer, Integer>(key, n);
					hashTable2[index] = item;
					return;
				}
				
				
			}
		}	
	
	
	private static class Entry<S, T>
	{
		private S key;
		private T value;
		
		private Entry(S searchKey, T dataValue)
		{
			key = searchKey;
			value = dataValue;
		}
		private S getKey()
		{
			return key;
		}
		private T getValue()
		{
			return value;
		}
		private void setValue(T newValue)
		{
			value = newValue;
		}
	}
}
/*  Linear  Results 
 *                  627                  21 
                 627                 627 

                 378                  75 
                 378                 378 

                 549                  44 
                 549                 549 

                 432                  28 
                 432                 432 

                 148                  47 
                 148                 148 

                 121                  20 
                 121                 121 

                 528                  23 
                 528                 528 

                 402                  99 
                 402                 402 

Solving Collision with Linear Probing
                 246                  44 
                 246                 246 

                 599                  94 
                 599                 599 

                  85                  85 
                  85                  85 

                 359                  56 
                 359                 359 

Solving Collision with Linear Probing
                 195                  94 
                 195                 195 

                 345                  42 
                 345                 345 

                 212                  10 
                 212                 212 

                 187                  86 
                 187                 187 

Solving Collision with Linear Probing
                 525                  20 
                 525                 525 

                 393                  90 
                 393                 393 

                 632                  26 
                 632                 632 

Solving Collision with Linear Probing
                 143                  42 
                 143                 143 

******************************************************************
               Index               Value

                  10                 212
                  20                 121
                  21                 627
                  22                 525
                  23                 528
                  26                 632
                  28                 432
                  42                 345
                  43                 143
                  44                 549
                  45                 246
                  47                 148
                  56                 359
                  75                 378
                  85                  85
                  86                 187
                  90                 393
                  94                 599
                  95                 195
                  99                 402
 */
/*   with Qaudhashing          
 * 	             627                  21 
                 627                 627 

                 378                  75 
                 378                 378 

                 549                  44 
                 549                 549 

                 432                  28 
                 432                 432 

                 148                  47 
                 148                 148 

                 121                  20 
                 121                 121 

                 528                  23 
                 528                 528 

                 402                  99 
                 402                 402 

Solving Collision with Quadratic Probing
                 246                  44 
                 246                 246 

                 599                  94 
                 599                 599 

                  85                  85 
                  85                  85 

                 359                  56 
                 359                 359 

Solving Collision with Quadratic Probing
                 195                  94 
                 195                 195 

                 345                  42 
                 345                 345 

                 212                  10 
                 212                 212 

                 187                  86 
                 187                 187 

Solving Collision with Quadratic Probing
                 525                  20 
                 525                 525 

                 393                  90 
                 393                 393 

                 632                  26 
                 632                 632 

Solving Collision with Quadratic Probing
                 143                  42 
                 143                 143 

******************************************************************
               Index               Value

                  10                 212
                  20                 121
                  21                 627
                  23                 528
                  26                 632
                  28                 432
                  29                 525
                  42                 345
                  44                 549
                  45                 246
                  47                 148
                  56                 359
                  58                 143
                  75                 378
                  85                  85
                  86                 187
                  90                 393
                  94                 599
                  98                 195
                  99                 402*/
/*double hashing
 *                  627                  21 
                 627                 627 

                 378                  75 
                 378                 378 

                 549                  44 
                 549                 549 

                 432                  28 
                 432                 432 

                 148                  47 
                 148                 148 

                 121                  20 
                 121                 121 

                 528                  23 
                 528                 528 

                 402                  99 
                 402                 402 

Solving Collision with Double Hashing
                 246                  44 
                 246                 246 

                 599                  94 
                 599                 599 

                  85                  85 
                  85                  85 

                 359                  56 
                 359                 359 

Solving Collision with Double Hashing
                 195                  94 
                 195                 195 

                 345                  42 
                 345                 345 

                 212                  10 
                 212                 212 

                 187                  86 
                 187                 187 

Solving Collision with Double Hashing
                 525                  20 
                 525                 525 

                 393                  90 
                 393                 393 

                 632                  26 
                 632                 632 

Solving Collision with Double Hashing
                 143                  42 
                 143                 143 

******************************************************************
               Index               Value

                  10                 212
                  20                 121
                  21                 627
                  23                 528
                  25                 525
                  26                 632
                  28                 432
                  42                 345
                  44                 549
                  45                 246
                  47                 148
                  48                 143
                  56                 359
                  75                 378
                  85                  85
                  86                 187
                  90                 393
                  94                 599
                  95                 195
                  99                 402

 */

