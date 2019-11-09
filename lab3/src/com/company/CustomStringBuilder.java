package com.company;

import java.util.Stack;

public class CustomStringBuilder {
    public CustomStringBuilder(String string)
    {
        this.stringBuilder = new StringBuilder(string);
        this.actions = new Stack<>();
    }
    public CustomStringBuilder()
    {
        this.stringBuilder = new StringBuilder();
        this.actions = new Stack<>();
    }
    public CustomStringBuilder(CharSequence seq)
    {
        this.stringBuilder = new StringBuilder(seq);
        this.actions = new Stack<>();
    }
    public CustomStringBuilder(int capacity)
    {
        this.stringBuilder = new StringBuilder(capacity);
        this.actions = new Stack<>();
    }
    public void undo()
    {
        if (!actions.empty()) actions.pop().undo();
    }
    public CustomStringBuilder append(boolean b)
    {
        String appended = String.valueOf(b);
        Action action = () -> stringBuilder.delete(stringBuilder.length() - appended.length(), stringBuilder.length());
        actions.push(action);
        stringBuilder.append(b);
        return this;
    }
    public CustomStringBuilder append(char c)
    {
        Action action = () -> stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        actions.push(action);
        stringBuilder.append(c);
        return this;
    }
    public CustomStringBuilder append(char[] str)
    {
        String appended = String.valueOf(str);
        Action action = () -> stringBuilder.delete(stringBuilder.length() - appended.length(), stringBuilder.length());
        actions.push(action);
        stringBuilder.append(str);
        return this;
    }
    public CustomStringBuilder append(char[] str, int offset, int len)
    {
        String appended = String.valueOf(str).substring(offset,offset + len);

        Action action = () -> stringBuilder.delete(stringBuilder.length() - appended.length(), stringBuilder.length());
        actions.push(action);
        stringBuilder.append(str, offset, len);
        return this;
    }
    public CustomStringBuilder append(CharSequence s)
    {
        Action action = () -> stringBuilder.delete(stringBuilder.length() - s.length(), stringBuilder.length());
        actions.push(action);
        stringBuilder.append(s);
        return this;
    }
    public CustomStringBuilder append(CharSequence s, int start, int end)
    {
        String appended = String.valueOf(s).substring(start, end);
        Action action = () -> stringBuilder.delete(stringBuilder.length() - appended.length(), stringBuilder.length());
        actions.push(action);
        stringBuilder.append(s, start, end);
        return this;
    }
    public CustomStringBuilder append(double d)
    {
        String appended = String.valueOf(d);
        Action action = () -> stringBuilder.delete(stringBuilder.length() - appended.length(), stringBuilder.length());
        actions.push(action);
        stringBuilder.append(d);
        return this;
    }
    public CustomStringBuilder append(int i)
    {
        String appended = String.valueOf(i);
        Action action = () -> stringBuilder.delete(stringBuilder.length() - appended.length(), stringBuilder.length());
        actions.push(action);
        stringBuilder.append(i);
        return this;
    }
    public CustomStringBuilder append(long lng)
    {
        String appended = String.valueOf(lng);
        Action action = () -> stringBuilder.delete(stringBuilder.length() - appended.length(), stringBuilder.length());
        actions.push(action);
        stringBuilder.append(lng);
        return this;
    }
    public CustomStringBuilder append(Object o)
    {
        String appended = String.valueOf(o);
        Action action = () -> stringBuilder.delete(stringBuilder.length() - appended.length(), stringBuilder.length());
        actions.push(action);
        stringBuilder.append(o);
        return this;
    }
    public CustomStringBuilder append(String str)
    {
        Action action = () -> stringBuilder.delete(
                stringBuilder.length() - str.length(),
                stringBuilder.length()
        );
        actions.push(action);
        stringBuilder.append(str);
        return this;
    }
    public CustomStringBuilder append(StringBuffer sb)
    {
        String appended = String.valueOf(sb);
        Action action = () -> stringBuilder.delete(stringBuilder.length() - appended.length(), stringBuilder.length());
        actions.push(action);
        stringBuilder.append(sb);
        return this;
    }
    public CustomStringBuilder appendCodePoint(int codePoint)
    {
        String appended = String.valueOf(codePoint);
        Action action = () -> stringBuilder.delete(stringBuilder.length() - appended.length(), stringBuilder.length());
        actions.push(action);
        stringBuilder.append(codePoint);
        return this;
    }
    public int capacity()
    {
        return stringBuilder.capacity();
    }
    public char charAt(int index)
    {
        return stringBuilder.charAt(index);
    }
    public int codePointAt(int index)
    {
        return stringBuilder.codePointAt(index);
    }
    public int codePointBefore(int index)
    {
        return stringBuilder.codePointBefore(index);
    }
    public int codePointCount(int beginIndex, int endIndex)
    {
        return stringBuilder.codePointCount(beginIndex, endIndex);
    }

    public CustomStringBuilder delete(int start, int end)
    {
        String substr = stringBuilder.substring(start, end);
        Action action = () -> stringBuilder.insert(start, substr);
        actions.push(action);
        stringBuilder.delete(start, end);
        return this;
    }
    public CustomStringBuilder deleteCharAt(int index)
    {
        char deletedChar = stringBuilder.charAt(index);
        Action action = () -> stringBuilder.insert(index, deletedChar);
        actions.push(action);
        stringBuilder.deleteCharAt(index);
        return this;
    }
    public void ensureCapacity(int minimumCapacity)
    {
        stringBuilder.ensureCapacity(minimumCapacity);
    }
    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
    {
        stringBuilder.getChars(srcBegin, srcEnd, dst, dstBegin);
    }
    public int indexOf(String str)
    {
        return stringBuilder.indexOf(str);
    }
    public int indexOf(String str, int fromIndex)
    {
        return stringBuilder.indexOf(str, fromIndex);
    }
    public CustomStringBuilder insert(int offset, boolean b)
    {
        String inserted = String.valueOf(b);
        Action action = () -> stringBuilder.delete(offset, inserted.length());
        actions.push(action);
        stringBuilder.insert(offset, b);
        return this;
    }
    public CustomStringBuilder insert(int offset, char c)
    {
        Action action = () -> stringBuilder.deleteCharAt(offset);
        actions.push(action);
        stringBuilder.insert(offset, c);
        return this;
    }
    public CustomStringBuilder insert(int offset, char[] str)
    {
        String inserted = String.valueOf(str);
        Action action = () -> stringBuilder.delete(offset, inserted.length());
        actions.push(action);
        stringBuilder.insert(offset, str);
        return this;
    }
    public CustomStringBuilder insert(int index, char[] str, int offset, int len)
    {
        String inserted = String.valueOf(str).substring(offset, offset + len);
        Action action = () -> stringBuilder.delete(offset, inserted.length());
        actions.push(action);
        stringBuilder.insert(index, str, offset, len);
        return this;
    }
    public CustomStringBuilder insert(int dstOffset, CharSequence s)
    {
        Action action = () -> stringBuilder.delete(dstOffset, s.length());
        actions.push(action);
        stringBuilder.insert(dstOffset, s);
        return this;
    }
    public CustomStringBuilder insert(int dstOffset, CharSequence s, int start, int end)
    {
        String inserted = String.valueOf(s).substring(start, end);
        Action action = () -> stringBuilder.delete(dstOffset, inserted.length());
        actions.push(action);
        stringBuilder.insert(dstOffset, s, start, end);
        return this;
    }
    public CustomStringBuilder insert(int offset, double d)
    {
        String inserted = String.valueOf(d);
        Action action = () -> stringBuilder.delete(offset, inserted.length());
        actions.push(action);
        stringBuilder.insert(offset, d);
        return this;
    }
    public CustomStringBuilder insert(int offset, float f)
    {
        String inserted = String.valueOf(f);
        Action action = () -> stringBuilder.delete(offset, inserted.length());
        actions.push(action);
        stringBuilder.insert(offset, f);
        return this;
    }
    public CustomStringBuilder insert(int offset, int i)
    {
        String inserted = String.valueOf(i);
        Action action = () -> stringBuilder.delete(offset, inserted.length());
        actions.push(action);
        stringBuilder.insert(offset, i);
        return this;
    }
    public CustomStringBuilder insert(int offset, long l)
    {
        String inserted = String.valueOf(l);
        Action action = () -> stringBuilder.delete(offset, inserted.length());
        actions.push(action);
        stringBuilder.insert(offset, l);
        return this;
    }
    public CustomStringBuilder insert(int offset, Object obj)
    {
        String inserted = String.valueOf(obj);
        Action action = () -> stringBuilder.delete(offset, inserted.length());
        actions.push(action);
        stringBuilder.insert(offset, obj);
        return this;
    }
    public CustomStringBuilder insert(int offset, String str)
    {
        Action action = () -> stringBuilder.delete(offset, offset + str.length());
        actions.push(action);
        stringBuilder.insert(offset, str);
        return this;
    }
    public int lastIndexOf(String str)
    {
        return stringBuilder.lastIndexOf(str);
    }
    public int lastIndexOf(String str, int fromIndex)
    {
        return stringBuilder.lastIndexOf(str, fromIndex);
    }
    public int length()
    {
        return stringBuilder.length();
    }
    public int offsetByCodePoints(int index, int codePointOffset)
    {
        return stringBuilder.offsetByCodePoints(index, codePointOffset);
    }
    public CustomStringBuilder replace(int start, int end, String str)
    {
        String replaced = stringBuilder.substring(start, end);
        Action action = () -> stringBuilder.replace(start, end, replaced);
        actions.push(action);
        stringBuilder.replace(start, end, replaced);
        return this;
    }
    public void setCharAt(int index, char ch)
    {
        char symbol = stringBuilder.charAt(index);
        Action action = () -> stringBuilder.setCharAt(index, symbol);
        actions.push(action);
        stringBuilder.setCharAt(index, ch);
    }
    public void setLength(int newLength)
    {
        int oldLength = stringBuilder.length();
        Action action = () -> stringBuilder.setLength(oldLength);
        actions.push(action);
        stringBuilder.setLength(newLength);
    }
    public CharSequence subSequence(int start, int end)
    {
        return stringBuilder.subSequence(start, end);
    }
    public String substring(int start)
    {
        return stringBuilder.substring(start);
    }
    public String substring(int start, int end)
    {
        return stringBuilder.substring(start, end);
    }
    public String toString()
    {
        return stringBuilder.toString();
    }
    public void trimToSize()
    {
        stringBuilder.trimToSize();
    }
    private StringBuilder stringBuilder;
    private interface Action
    {
        void undo();
    }
    private Stack<Action> actions;

}
