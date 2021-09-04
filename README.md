# My Game of Life

My Game of Life is a Java implementation for [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

I have implemented Conway's Game of Life using a two-dimensional lists. I found difficulties expanding the universe while using a two-dimensional lists.

In this implementation, each cell knows their position. Expanding the universe become easier because I only need to search cell with the smallest and biggest position.

## Run Tests

Use the Gradle Wrapper to run the tests.

```bash
./gradlew test
```

## Usage

Use the Gradle Wrapper to run the tests.

```bash
./gradlew run
```

## License
[MIT](https://choosealicense.com/licenses/mit/)
