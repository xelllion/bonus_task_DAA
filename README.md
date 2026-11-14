
# Aho-Corasick Algorithm

## Project Description

This Java project implements the Aho-Corasick algorithm to search for multiple patterns in a text. It also implements KMP to compare their performance.

## Implementation

The project contains three Java files:

- `AhoCorasick.java` - builds a Trie, creates failure links, stores matched patterns in output lists, counts all occurrences, and prints match positions.
- `KMP.java` - searches for one pattern at a time using an LPS array.
- `Main.java` - runs a simple test, generates test data, compares both algorithms, and measures execution time.

### How Aho-Corasick Works

1. **Trie:** Stores all patterns together and shares common prefixes.
2. **Failure Links:** Help the algorithm continue searching after a mismatch.
3. **Output Lists:** Store patterns that end at each node, including matches from failure links.
4. **Search:** Reads the text character by character and finds all matches, including overlapping occurrences.

The `search()` method returns the total number of matches. The `printMatches()` method prints each matched pattern and its starting position.

### Example Test

Text: `ababa`

Patterns: `aba`, `ba`

```text
Example: ababa
aba at position 0
ba at position 1
aba at position 2
ba at position 3
```

Positions start from 0. The example shows that the algorithm finds overlapping matches.

## Experiments

The program tests:

- **Number of patterns:** 10, 100, 1000
- **Text lengths:** 1000, 10000, 100000 characters
- **Pattern length:** 8 characters
- **Alphabet:** a, b, c, d
- **Runs per test case:** 5

Random data is generated using seed `42`.

Execution time is measured using `System.nanoTime()`. Each experiment is repeated 5 times, and the average execution time is calculated in milliseconds.

A warm-up run is performed before measurement.

For Aho-Corasick, Trie and failure-link construction are completed before timing. For KMP, the measured time includes searching for every pattern separately and building the LPS arrays.

The example test is not included in the performance measurements.

## Experimental Results

| Patterns | Text Length | Aho-Corasick (ms) | KMP (ms) |
|---:|---:|---:|---:|
| 10 | 1000 | 1.259 | 0.971 |
| 10 | 10000 | 1.917 | 1.808 |
| 10 | 100000 | 2.335 | 7.934 |
| 100 | 1000 | 0.018 | 0.707 |
| 100 | 10000 | 0.216 | 7.176 |
| 100 | 100000 | 3.202 | 77.894 |
| 1000 | 1000 | 0.069 | 6.750 |
| 1000 | 10000 | 0.516 | 66.913 |
| 1000 | 100000 | 2.969 | 699.317 |

## Time and Space Complexity

Let:
- `n` = text length
- `m` = total length of all patterns
- `k` = number of patterns
- `z` = number of matches

### Aho-Corasick

For the standard algorithm with efficient transitions:

- Trie construction: **O(m)**
- Failure-link construction: **O(m)**
- Searching: **O(n + z)**
- Space: **O(m)**, excluding stored match output

The Java implementation uses `HashMap` and copies output lists from failure nodes. This can increase preprocessing time and memory usage compared with the standard algorithm.

### KMP

- LPS construction: **O(p)**, where p is the pattern length
- Searching one pattern: **O(n)**
- Searching k patterns separately: **O(k * n + m)**
- Extra space per search: **O(p)**

## Performance Comparison

Aho-Corasick was faster than KMP in 7 out of 9 experiments.

KMP was slightly faster in two tests with 10 patterns and text lengths of 1000 and 10000 characters.

When the number of patterns increased, Aho-Corasick showed much better performance.

For 1000 patterns and 100000 characters:

- Aho-Corasick: **2.969 ms**
- KMP: **699.317 ms**

Aho-Corasick was approximately **236 times faster** in this experiment.

Aho-Corasick is useful for searching many patterns because it stores them in one Trie and searches the text in one pass.

Its advantages are fast multi-pattern searching and shared prefixes. Its limitations are preprocessing time and additional memory usage.

KMP is simpler and works well for one pattern. However, it needs to search the text separately for each pattern.

Small differences in execution time can happen because of JVM warm-up and other system processes.

## Conclusion

Aho-Corasick is a good algorithm for searching many patterns in one text.

The experiments show that it is usually faster than running KMP separately for each pattern, especially when the number of patterns is large.

## How to Run

1. Open the project in IntelliJ IDEA.
2. Select JDK 17 or newer.
3. Run `Main.java`.
4. View the example matches and experimental results in the console.

No external libraries are required.
