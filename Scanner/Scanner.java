import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Predicate;

public class Scanner implements AutoCloseable {
    private final Reader in;

    private boolean closed;

    public boolean lineEnded;

    private boolean streamEnded;

    private final boolean allowLettersInInts;

    private char prev;

    private String cache;

    private boolean cached;

    private final Predicate<Character> isBound;

    public Scanner(InputStream in, boolean allowLettersInInts, boolean useNonStandardBounds) {
        this.in = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        this.allowLettersInInts = allowLettersInInts;
        if (useNonStandardBounds) {
            this.isBound = this::isNotWordPart;
        } else {
            this.isBound = this::isSpace;
        }
    }

    public Scanner(File in, boolean allowLettersInInts, boolean useNonStandardBounds) throws IOException {
        this(new FileInputStream(in), allowLettersInInts, useNonStandardBounds);
    }

    public void close() throws IOException {
        closed = true;
        in.close();
    }

    private void isClosed() {
        if (closed) {
            throw new IllegalStateException("Scanner is closed");
        }
    }

    public boolean streamNotEnded() {
        return !streamEnded;
    }

    public boolean lineNotEnded() {return !lineEnded;}

    public void toNextLine() {
        lineEnded = false;
        cached = false;
    }

    private String next(boolean considerLineEnds) throws IOException {
        isClosed();

        StringBuilder token = new StringBuilder();
        int c = in.read();

        while (c != -1) {
            char sym = (char) c;
            if (isLineSeparator(sym, prev)) {
                lineEnded = true;
            }
            prev = sym;

            if (!isBound.test(sym)) {
                token.append((sym));
            }

            if (lineEnded && considerLineEnds || isBound.test(sym) && token.length() > 0) {
                break;
            }

            c = in.read();
        }

        if (c == -1) {
            streamEnded = true;
            lineEnded = true;
        }

        return token.toString();
    }

    private boolean hasAbstractNext(Predicate<String> isType, boolean considerLineEnds) throws IOException {
        cache = next(considerLineEnds);
        cached = true;
        return isType.test(cache);
    }

    public boolean hasNextInt(boolean considerLineEnds) throws IOException {
        return hasAbstractNext(this::isInt, considerLineEnds);
    }

    public boolean hasNext(boolean considerLineEnds) throws IOException {
        return hasAbstractNext(this::isNotEmpty, considerLineEnds);
    }

    public Integer nextInt(boolean considerLineEnds) throws IOException {
        if (cached && isInt(cache) || hasNextInt(considerLineEnds)) {
            cached = false;
            return toInt(cache);
        }
        cached = false;
        return null;
    }

    public String nextToken(boolean considerLineEnds) throws IOException {
        if (cached) {
            cached = false;
            return cache;
        }
        return next(considerLineEnds);
    }

    private boolean isLineSeparator(char c, char prev) {
        switch (System.lineSeparator()) {
            case "\r\n":
                return prev == '\r' && c == '\n';
            case "\n":
                return c == '\n';
            case "\r":
                return c == '\r';
        }
        return false;
    }

    private boolean isSpace(char c) {
        return isSpaceSeparator(c) || Character.isWhitespace(c);
    }

    private boolean isSpaceSeparator(char c) {
        return Character.getType(c) == Character.SPACE_SEPARATOR;
    }

    private boolean isDash(char c) {
        return Character.getType(c) == Character.DASH_PUNCTUATION;
    }

    private boolean isNotWordPart(char c) {
        return !(isDash(c) || Character.isLetter(c) || c == '\'');
    }

    private boolean isInt(String s) {
        if (s.length() == 0) {
            return false;
        }
        try {
            toInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private String lettersToDigits(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'j') {
                sb.append((char)(c - 49));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private int toInt(String s) throws NumberFormatException {
        int idx = Math.max(s.indexOf("x"), s.indexOf("X"));
        if (idx != -1) {
            return Integer.parseUnsignedInt(s.substring(idx + 1), 16);
        }
        if (allowLettersInInts) {
            return Integer.parseInt(lettersToDigits(s));
        }
        return Integer.parseInt(s);
    }

    private boolean isNotEmpty(String s) {
        return s.length() > 0;
    }
}