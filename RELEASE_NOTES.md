# Release v1.0 - Reddit Word Frequency Analyzer

## 🎯 Major Improvements: Better Data Distribution

This release removes the Git LFS dependency and provides multiple download options for the Reddit data files, making the project more accessible to users.

## 📦 Data Packages Available

**Choose the package that fits your needs:**

- **reddit-data-small.zip** (37MB) - 2008 data only, perfect for testing and getting started
- **reddit-data-medium.zip** (336MB) - 2008-2011 data, good for most analyses  
- **reddit-data-complete.zip** (700MB) - All years 2008-2015, complete dataset

**Individual files are also available for download below.**

## 🚀 Quick Start

1. Clone the repository: `git clone https://github.com/hunter-broughton/reddit-word-frequency-analyzer.git`
2. Download a data package from this release (start with the small package for testing)
3. Extract the data files in your project directory
4. Compile: `javac *.java`  
5. Run: `java -Xmx2g WordCounter` (for 2008 data) or `java -Xmx4g WordCounter` (for 2011 data)

## 📊 What's Included

### Data Structures
- **BSTMap**: Binary Search Tree implementation
- **HashMap**: Hash table with separate chaining
- **AVLTree**: Self-balancing binary search tree

### Analysis Features
- Word frequency counting
- Performance comparison between data structures
- Political word tracking over time
- Memory usage analysis

### Reddit Data (2008-2015)
- ~500K to 2.4M comments per year
- Plain text format, one comment per line
- Preprocessed for privacy (no personal information)

## 🔧 System Requirements

- Java 8 or higher
- 2-8GB RAM (depending on dataset size)
- Sufficient disk space for data files

## 📈 Performance Results

| Data Structure | 2008 Data | Memory Efficiency |
|----------------|-----------|-------------------|
| HashMap        | ~4 seconds | Best             |
| AVLTree        | ~12 seconds | Better           |
| BSTMap         | ~16 seconds | Good             |

## 🎓 Educational Focus

Perfect for learning:
- Data structure implementation from scratch
- Performance analysis and Big-O complexity
- Large dataset processing
- File I/O operations
- Object-oriented design

## 💡 Getting Help

See the comprehensive README.md for:
- Detailed setup instructions
- Memory requirements for different datasets
- How to change data files
- Troubleshooting common issues
- Complete API documentation

## 🏗️ For Maintainers

Use the included `package_data.sh` script to recreate data packages when needed.

---

**Created for CS231A - Data Structures and Algorithms**  
**Colby College - April 2023**
