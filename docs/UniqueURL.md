# Unique URL

**Auto-Generated ID + Base58**

- Combines uppercase letters, lowercase letters, and 58 digits.
- Easy for humans to read.
- Allows generation of diverse URLs (e.g., 6 characters can create 38 billion URLs).
- Uses auto-generated database keys.

### 1. Base58

Uses 58 combinations of uppercase letters, lowercase letters, and digits (excluding 0, O, l, I).

- Advantages:
    - Prevents confusion: Easy for humans to read, reducing errors (e.g., avoiding 0/O/l/I confusion).
    - Shorter URLs: More efficient than Base62.
- Disadvantages:
    - Smaller character set: Fewer combinations than Base62.
    - Limited special characters.

### 2. Base62

Uses 62 combinations of uppercase letters, lowercase letters, and digits.

- Advantages:
    - Larger combinations: Utilizes all 62 characters.
    - Short URLs: Efficient and widely compatible.
    - Excludes special characters: Suitable for various systems.
- Disadvantages:
    - Similar characters may cause confusion (e.g., 0/O/l/I).

### 3. Hash

- Advantages:
    - Guarantees consistent output length.
    - Low collision probability.
    - Produces the same result for identical inputs.
- Disadvantages:
    - Potential collisions.
    - Long URLs may require trimming hash values.

### 4. UUID

- Advantages:
    - High uniqueness.
    - Extremely low collision probability.
- Disadvantages:
    - Long URLs.
    - Hard to read.