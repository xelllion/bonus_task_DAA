# Aho–Corasick Algorithm

## Project Description

This Java project implements the Aho–Corasick algorithm to search for multiple patterns in a text. It also implements KMP to compare their search performance.

## Implementation

The project contains three Java files:

- `AhoCorasick.java` — builds a trie, creates failure links, stores matched patterns in output lists, and counts all pattern occurrences in the text.
- `KMP.java` — searches for one pattern at a time using an LPS array.
- `Main.java` — generates test data, runs both algorithms, checks that their match counts are equal, and measures execution time.

### How Aho–Corasick Works

1. **Trie:** stores all patterns together and shares common prefixes.
2. **Failure links:** tell the algorithm where to continue after a mismatch without restarting from the beginning.
3. **Output lists:** identify patterns that end at the current node, including patterns reached through failure links.
4. **Search:** scans the text and counts all matches, including overlapping occurrences.

The implementation reports the total number of matches rather than printing every match position.

## Experiments

The program tests:

- **Number of patterns:** 10, 100, and 1,000.
- **Text lengths:** 1,000, 10,000, and 100,000 characters.
- **Pattern length:** 8 characters.
- **Alphabet:** `a`, `b`, `c`, `d`.
- **Runs per test case:** 5.

Random data is generated using seed `42`. Execution time is measured with `System.nanoTime()` and reported as the average of five runs in milliseconds. A warm-up run is performed before measurement.

For Aho–Corasick, trie and failure-link construction happen before timing; the measured time covers searching only. For KMP, the measured time covers searching all patterns separately, including LPS construction inside each search call.

## Experimental Results

| Patterns | Text Length | Aho–Corasick (ms) | KMP (ms) |
|---:|---:|---:|---:|
| 10 | 1,000 | 0.193 | 0.992 |
| 10 | 10,000 | 0.999 | 1.628 |
| 10 | 100,000 | 2.382 | 7.487 |
| 100 | 1,000 | 0.020 | 0.708 |
| 100 | 10,000 | 0.229 | 6.732 |
| 100 | 100,000 | 2.662 | 64.733 |
| 1,000 | 1,000 | 0.051 | 5.677 |
| 1,000 | 10,000 | 0.389 | 65.698 |
| 1,000 | 100,000 | 3.001 | 627.207 |

## Time and Space Complexity

Let `n` be the text length, `m` the total length of all patterns, `k` the number of patterns, and `z` the number of matches.

**Aho–Corasick (standard algorithm with efficient transitions):**

- Trie construction: **O(m)**.
- Failure-link construction: **O(m)** for a fixed alphabet and efficient transitions.
- Searching: **O(n + z)**.
- Space: **O(m)** for the trie and failure links, excluding stored match output.

**Note about this implementation:** It uses `HashMap` transitions and copies failure-node output lists into child nodes. Therefore, its actual preprocessing and memory usage may be higher than the standard bounds, and search time also depends on failure-link traversals.

**KMP:**

- LPS construction for one pattern of length `p`: **O(p)**.
- Search for one pattern: **O(n)**.
- Search for all `k` patterns separately: **O(k × n + m)**.
- Extra space per search: **O(p)**.

## Performance Comparison

Aho–Corasick was faster than KMP in all nine measured cases. The difference became larger when the number of patterns increased.

For 1,000 patterns and a text length of 100,000 characters:

- Aho–Corasick: **3.001 ms**.
- KMP: **627.207 ms**.
- Aho–Corasick was approximately **209 times faster** in this experiment.

Aho–Corasick is useful when many patterns must be searched together. Its main advantages are shared prefixes and searching all patterns during one scan. Its limitations are additional preprocessing and memory usage.

KMP is simpler and works well for one pattern. However, when searching many patterns, it must process the text separately for each one.

Small timing differences between test cases can be caused by JVM warm-up and other runtime effects.

## How to Run

1. Open the project in IntelliJ IDEA.
2. Select JDK 17 or newer.
3. Run `Main.java`.
4. View the average execution times in the console.

No external libraries are required.
