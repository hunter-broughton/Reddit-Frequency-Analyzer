# Reddit Word Frequency Analyzer

**Author:** Hunter Broughton  
**Course:** CS231A  
**Date:** April 2023

## Project Overview

This project implements and compares three different data structures (Binary Search Tree, HashMap, and AVL Tree) to analyze word frequency patterns in Reddit comment data from 2008-2015. The implementation demonstrates the performance characteristics of different data structures when processing large datasets.

## Reddit Data Files

**Important**: The Reddit comment data files are not included in this repository due to GitHub's file size limitations (files are 200MB+ each).

### Required Data Files

You need to obtain the following files and place them in the project directory:

- `reddit_comments_2008.txt` - Reddit comments from 2008
- `reddit_comments_2009.txt` - Reddit comments from 2009
- `reddit_comments_2010.txt` - Reddit comments from 2010
- `reddit_comments_2011.txt` - Reddit comments from 2011 (**default**)
- `reddit_comments_2012.txt` - Reddit comments from 2012
- `reddit_comments_2013.txt` - Reddit comments from 2013
- `reddit_comments_2014.txt` - Reddit comments from 2014
- `reddit_comments_2015.txt` - Reddit comments from 2015

### Data Format

- Each file contains Reddit comments from the corresponding year
- Format: Plain text, one comment per line
- File sizes: ~200MB to 2GB each
- Total dataset: ~8-12GB for all years

**Note:** The program is currently configured to analyze 2011 data by default. To analyze a different year, modify **line 105** in `WordCounter.java`:

```java
// Change this line to use a different year's data
ArrayList<String> redditWords = myWordCounter.readWords("reddit_comments_2011.txt");
```

For example, to analyze 2014 data:

## Features

- **Three Map Data Structure Implementations:**

  - **BSTMap**: Binary Search Tree implementation with O(log n) average operations
  - **HashMap**: Hash table with separate chaining, O(1) average operations
  - **AVLTree**: Self-balancing binary search tree, guaranteed O(log n) operations

- **Word Frequency Analysis:**

  - Process large Reddit comment datasets
  - Track word counts and frequencies
  - Generate reports on most frequent words
  - Analyze political word trends over time

- **Performance Comparison:**
  - Runtime analysis between data structures
  - Memory usage comparison (max depth tracking)
  - Scalability testing with different dataset sizes

## Data Structures

### BSTMap

A binary search tree implementation of the MapSet interface. Stores key-value pairs in a sorted tree structure for efficient searching.

### HashMap

A hash table implementation using separate chaining for collision resolution. Features dynamic resizing and configurable load factors.

### AVLTree

A self-balancing binary search tree that maintains balance through rotations, ensuring optimal performance even with skewed data.

## Project Structure

```
├── README.md                    # This file
├── .gitignore                   # Git ignore rules
├── MapSet.java                  # Interface defining the Map contract
├── BSTMap.java                  # Binary Search Tree implementation
├── HashMap.java                 # Hash Table implementation
├── AVLTree.java                 # AVL Tree implementation
├── WordCounter.java             # Main analysis engine
├── *Test.java                   # Unit tests for each data structure
├── test.txt                     # Sample test data
├── test2.txt                    # Test output file
├── commonWords.txt              # Common English words to filter
├── dataStructureData.txt        # Performance comparison results
├── political_words.txt          # Political word frequency analysis
└── highest_frequency_words.txt  # Most frequent words report
```

**Note:** Reddit comment data files (`reddit_comments_YYYY.txt`) are not included due to size limitations.

## How to Run

### Prerequisites

1. **Obtain Reddit Data Files**: Place the required `reddit_comments_YYYY.txt` files in the project directory
2. **Java 8 or higher**: Ensure you have Java installed

### Quick Start

1. **Compile all Java files:**

   ```bash
   javac *.java
   ```

2. **Run the basic analysis (default: 2011 data):**
   ```bash
   java -Xmx4g WordCounter
   ```

### Analyzing Different Years

To analyze a different year's Reddit data:

1. **Open `WordCounter.java` in your editor**
2. **Navigate to line 108** (in the BASIC EXPLORATION MODE section)
3. **Change the filename** to your desired year:

   ```java
   // Current (line 108):
   ArrayList<String> redditWords = myWordCounter.readWords("reddit_comments_2011.txt");

   // Example: To analyze 2014 data, change to:
   ArrayList<String> redditWords = myWordCounter.readWords("reddit_comments_2014.txt");
   ```

4. **Recompile and run:**
   ```bash
   javac WordCounter.java
   java -Xmx4g WordCounter
   ```

### Available Data Files

The following Reddit comment datasets are included:

- `reddit_comments_2008.txt` through `reddit_comments_2015.txt`
- Each file contains thousands of Reddit comments from that year
- File sizes range from hundreds of MB to several GB

### Running Tests

```bash
# Test individual data structures
java -ea BSTMapTest
java -ea HashMapTest
java -ea AVLTreeTest
java -ea WordCounterTest
```

### Advanced Usage

**Performance Analysis** (WARNING: Very resource intensive):

1. Uncomment the performance benchmarking block in `WordCounter.java` main method
2. Run with maximum memory:
   ```bash
   java -Xmx8g WordCounter
   ```

**Political Word Tracking**:

1. Uncomment the political word tracking block in `WordCounter.java` main method
2. Run with sufficient memory:
   ```bash
   java -Xmx6g WordCounter
   ```

## Usage Examples

### Basic Usage

```java
// Create a word counter with specified data structure
WordCounter counter = new WordCounter("HashMap"); // or "bst" or "avl"

// Read words from file
ArrayList<String> words = counter.readWords("reddit_comments_2011.txt");

// Build the frequency map
double buildTime = counter.buildMap(words);

// Query word frequencies
int obamaCount = counter.getCount("Obama");
double obamaFreq = counter.getFrequency("Obama");

// Export results
counter.writeWordCount("output.txt");
```

### Data Structure Selection

- **HashMap**: Best for large datasets requiring fast lookups
- **BSTMap**: Good for ordered data and when memory is limited
- **AVLTree**: Optimal when guaranteed O(log n) performance is required

## Data Requirements and Memory Usage

### Reddit Comment Data Files

- **Size**: Each Reddit comment file ranges from ~500MB to 2GB
- **Content**: Raw Reddit comments from corresponding years (2008-2015)
- **Format**: Plain text, one comment per line
- **Total Dataset**: Approximately 8-12GB for all years combined

### Memory Requirements

Different analysis modes require different memory allocations:

| Mode                         | Memory Required | Command                   |
| ---------------------------- | --------------- | ------------------------- |
| Basic Analysis (single year) | 4GB             | `java -Xmx4g WordCounter` |
| Political Word Tracking      | 6GB             | `java -Xmx6g WordCounter` |
| Performance Benchmarking     | 8GB             | `java -Xmx8g WordCounter` |

**Important Notes:**

- Insufficient memory will result in `OutOfMemoryError`
- Processing larger years (2008-2012) requires more memory than later years
- HashMap generally uses less memory than BST for the same dataset

## Performance Results

Based on analysis of Reddit comment data (see `dataStructureData.txt`):

| Data Structure | 2008 Runtime (ms) | Max Depth | 2014 Runtime (ms) | Max Depth |
| -------------- | ----------------- | --------- | ----------------- | --------- |
| HashMap        | 3,939             | 7         | 14,280            | 8         |
| BST            | 15,527            | 225       | 65,528            | 909       |

**Key Findings:**

- HashMap consistently outperforms BST for large datasets
- BST performance degrades significantly with unbalanced data
- AVL Tree provides balanced performance with guaranteed bounds

## Analysis Features

### Political Word Tracking

The project includes analysis of political terms over time (2008-2015):

- Obama, Biden, Trump, Clinton mentions
- Iraq, ISIS references
- Economic terms like "Depression"

### Common Word Filtering

Uses a curated list of common English words to filter out noise and focus on meaningful content words.

### Frequency Reports

Generates detailed reports of:

- Most frequent words overall
- Specific word frequency over time
- Comparative analysis between years

## Implementation Notes

- All data structures implement the `MapSet<K,V>` interface for consistency
- Dynamic memory management in HashMap with configurable load factors
- AVL Tree includes full rotation logic for self-balancing
- Comprehensive error handling and input validation
- Thread-safe implementations where appropriate

## Memory Requirements

For processing large Reddit comment files:

- Minimum: 2GB RAM (`-Xmx2g`)
- Recommended: 4GB RAM (`-Xmx4g`)
- Performance testing: 8GB RAM (`-Xmx8g`)

## Educational Goals

This project demonstrates:

- Data structure implementation from scratch
- Performance analysis and big-O complexity
- Real-world application of theoretical concepts
- File I/O and data processing techniques
- Object-oriented design principles

## Future Enhancements

Potential improvements include:

- Multi-threading for large dataset processing
- Database integration for persistent storage
- Web interface for interactive analysis
- Machine learning integration for sentiment analysis
- Support for additional file formats

---

_Created for CS231A - Data Structures and Algorithms_  
_All implementations created by Hunter Broughton, April 2023_
