# map-reduce-word-frequency

Assignment for Big Data course

A Map/Reduce system composed of a Mapper class and a Reducer class. The system counts the frequency of each word in the workshop.txt file. The result is then saved in a txt file containing a list of token:count.

## Flow Chart

![alt text](https://github.com/KimjiP/map-reduce-word-frequency/blob/main/flow%20chart.png)

The figure above shows a simplified flow chart of the java program. Each process will be explained in the succeeding sections.

The Map class is responsible for tokenizing each word while the Reduce class counts the frequency of each word, sort by value, and saves in a text file. The Reduce class shall contain the main method that drives the workflow of the program.

## Loading and Reading a Text File

A java FileReader class is used to read data from a txt file using the command FileReader f = new FileReader(fileLocation).

Then a Scanner class is used to scan the lines of text obtained from the FileReader. Scanner class has a hasNextLine() method that we can use to read the document line by line. 

## Tokenizing String

The hasNextLine() method returns true if there is another line in the input of the scanner. We can put this condition inside a while loop that splits each line into words using the command
String[] words = line.split("\\s").
This command simply separates each word in a string when it encounters a white space, then saves each word in a String array.

Now that we have an array of words, we then need to “clean” each word so that the program can correctly identify same words. This can be done by first converting all capital letters into lower case, and then removing common punctuation marks. We do this inside a for loop that processes each word in the array. The following commands are used:
if (!word.isEmpty()){
word = word.toLowerCase();
word = word.replace("//any symbol here//",""); }
The symbols removed in this project are:  , . ; ” ? ( ) : ’s. More can be added but these are the common ones found in documents.

After processing each word, they can then be added to the ArrayList<String> using the add() method. The Mapper’s job is done after it has saved all words in the ArrayList.

## Counting Word Frequency

First step is to load the ArrayList<String> collected from the Map class, then passing this data to a void method called reduce. Inside this method is a for loop that checks each word inside ArrayList<String> one by one.
  
An if statement is used to save the word and its corresponding frequency value on a Hashtable, which is a class that stores key/value pairs. A Hashtable is perfect for word count problems because we can simply store the word as key and its corresponding count as value.

Each time we iterate to a word in the ArrayList, the if statement checks if the Hashtable already has this word by using the command containsKey(). If this is true, we add a 1 to the key’s corresponding value. Otherwise, if a word is encountered for the first time, we save this new word to the Hashtable using the put() method. This process repeats until all words in the ArrayList have been checked.

## Sorting Key:Value Pairs in Descending Order

An extra step added in this report is the sorting of keys according to its value to know which words are most frequently used. This is rather tricky because in a Hashtable, data is not sorted based on keys or value. The solution is to save the Hashtable into a Map.Entry class, which has plenty of methods we can use for sorting purposes.

The steps are as follows:
1.	Get the sets of key-value pairs from the Hashtable by turning it into a stream
map.entrySet().stream()
2.	Available in Java 8, comparingByValue() is a method that sorts by values. But by default, this method sorts in ascending order, so we put it inside Collections.reverseOrder() comparator .
.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
3.	Use the collect() method to collect and return a LinkedHashMap class, which preserves the insertion order
.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new))

## Saving Result to a Text File

Finally, a void method is called to save the sorted Map.Entry data onto a text file. This is achieved by using a FileWriter class. A simple for loop is implemented to access into the data one by one. Methods getKey() and getValue() are used to retrieve keys and values, respectively.
 
There are much more words that are only used once in the text file. Note that the program does not have the ability to recognize words in plural and singular form, therefore they are counted separately.

## Conclusion

In this report, Mapper and Reducer classes were illustrated that counts the frequency of each word in a text document. The Mapper’s job is to tokenize words in a line of String and save them in an ArrayList. The Reducer then counts the frequency of each unique word.

